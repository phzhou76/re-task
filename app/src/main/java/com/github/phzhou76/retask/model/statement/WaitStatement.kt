package com.github.phzhou76.retask.model.statement

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.github.phzhou76.retask.model.value.numericvalue.LongValue
import java.util.concurrent.ThreadLocalRandom

/**
 * A Statement that puts the script to sleep for a given amount of time.
 *
 * @constructor Creates a WaitStatement that pauses the script for the input
 *      amount of time, with some variance.
 */
class WaitStatement(waitTime: LongValue, waitTimeVariance: LongValue) : Statement()
{
    /* Wait time in milliseconds. */
    var mWaitTime: LongValue = waitTime

    /* Variation in wait time. Must be less than or equal to mWaitTime. */
    var mWaitTimeVariance: LongValue = waitTimeVariance

    constructor(parcel: Parcel) : this(
            parcel.readParcelable(LongValue::class.java.classLoader)
                    ?: throw NullPointerException("Parcel Error: $TAG (mWaitTime)"),       /* mWaitTime */
            parcel.readParcelable(LongValue::class.java.classLoader)
                    ?: throw NullPointerException("Parcel Error: $TAG (mWaitTimeVariance") /* mWaitTimeVariance */
    )

    /**
     * Puts the script to sleep for the input wait time (in milliseconds). The
     * script will be paused in 1000 ms intervals to check if the user manually
     * stopped the script.
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
        return "Wait for: $mWaitTime ms ${(177).toChar()} $mWaitTimeVariance ms"
    }

    /**
     * Generates a random wait time based on the wait time plus-minus the variance.
     *
     * @return A randomized wait time.
     */
    private fun generateWaitTime(): Long
    {
        return (mWaitTime.mLongValue - mWaitTimeVariance.mLongValue) +
                ThreadLocalRandom.current().nextLong(0,
                        2 * mWaitTimeVariance.mLongValue + 1)
    }

    /** Parcelable implementation. */
    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeParcelable(mWaitTime, flags)
        parcel.writeParcelable(mWaitTimeVariance, flags)
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