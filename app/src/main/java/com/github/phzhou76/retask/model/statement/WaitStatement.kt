package com.github.phzhou76.retask.model.statement

import android.os.Parcel
import android.os.Parcelable
import java.util.concurrent.ThreadLocalRandom

/**
 * A Statement that pauses the script for the input number of milliseconds.
 *
 * @constructor Creates a WaitStatement that pauses the script for the given
 *      time in milliseconds, with a randomized increase or decrease in time.
 */
class WaitStatement(waitTime: Long, waitTimeVariance: Long) : Statement()
{
    override var mStatementLabel: String = updateLabel(waitTime, waitTimeVariance)

    /* Wait time in milliseconds. */
    var mWaitTime: Long = waitTime
        set(value)
        {
            field = value
            mStatementLabel = updateLabel(value, mWaitTimeVariance)
        }

    /* Variation in wait time. Must be less than or equal to mWaitTime. */
    var mWaitTimeVariance: Long = waitTimeVariance
        set(value)
        {
            field = value
            mStatementLabel = updateLabel(mWaitTime, value)
        }

    /**
     * Required constructor for a Parcelable implementation.
     *
     * @constructor Takes the information from the Parcel to recreate the object.
     */
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

    /**
     * Generates the updated label for the WaitStatement. The method needs to be
     * called whenever mWaitTime is updated.
     *
     * @param newWaitTime The new wait time of WaitStatement.
     * @param newWaitTimeVariance The new wait time variance of WaitStatement.
     *
     * @return The updated label for the WaitStatement.
     */
    private fun updateLabel(newWaitTime: Long, newWaitTimeVariance: Long): String
    {
        return "Wait for: " + newWaitTime.toString() + " " + (177).toChar() +
                " " + newWaitTimeVariance.toString() + " ms"
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