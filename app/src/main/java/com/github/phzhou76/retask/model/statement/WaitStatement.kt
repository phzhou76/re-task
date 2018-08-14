package com.github.phzhou76.retask.model.statement

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import java.util.concurrent.ThreadLocalRandom

/**
 * A Statement that pauses the script for the input number of milliseconds.
 *
 * @constructor Creates a WaitStatement that pauses the script for the given
 *      time in milliseconds, with a randomized increase or decrease in time.
 */
class WaitStatement(waitTime: Long, waitTimeVariance: Long) : Statement()
{
    /* Wait time in milliseconds. */
    var mWaitTime: Long = waitTime

    /* Variation in wait time. Must be less than or equal to mWaitTime. */
    var mWaitTimeVariance: Long = waitTimeVariance

    constructor(parcel: Parcel) : this(
            parcel.readLong(),   /* mWaitTime */
            parcel.readLong()   /* mWaitTimeVariance */
    )

    /**
     * Puts the script to sleep for the inputted wait time in milliseconds.
     */
    override fun execute()
    {
        var timeLeftToWait: Long = generateWaitTime()

        /* Check every 1000 ms, unless there's less than 1000 ms remaining. */
        while (timeLeftToWait > 0 && !mStopExecution)
        {
            if ((timeLeftToWait / 1000) > 0)
            {
                Thread.sleep(1000)
                timeLeftToWait -= 1000
            }
            else
            {
                Thread.sleep(timeLeftToWait)
                timeLeftToWait = 0
            }
        }
    }

    override fun printDebugLog()
    {
        Log.d(TAG, this.toString())
    }

    override fun toString(): String
    {
        return "Wait for: " + mWaitTime.toString() + " " + (177).toChar() +
                " " + mWaitTimeVariance.toString() + " ms"
    }

    /**
     * Generates a random wait time based on the wait time plus-minus the variance.
     *
     * @return A randomized wait time.
     */
    private fun generateWaitTime(): Long
    {
        return (mWaitTime - mWaitTimeVariance) +
                ThreadLocalRandom.current().nextLong(0, 2 * mWaitTimeVariance + 1)
    }


    /* Parcelable implementation. */
    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeLong(mWaitTime)
        parcel.writeLong(mWaitTimeVariance)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WaitStatement>
    {
        private val TAG: String = WaitStatement::class.java.simpleName

        override fun createFromParcel(parcel: Parcel): WaitStatement
        {
            return WaitStatement(parcel)
        }

        override fun newArray(size: Int): Array<WaitStatement?>
        {
            return arrayOfNulls(size)
        }
    }
}