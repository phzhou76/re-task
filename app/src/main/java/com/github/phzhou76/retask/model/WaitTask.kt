package com.github.phzhou76.retask.model

import android.os.Parcel
import android.os.Parcelable

/**
 * A task that pauses the automation for the given number of milliseconds.
 *
 * @constructor Creates a WaitTask with the given title and wait time
 *      (in milliseconds).
 */
class WaitTask(taskTitle: String, waitTime: Long): ITask
{
    override var mTaskTitle: String = taskTitle
    var mWaitTime: Long = waitTime

    /**
     * Secondary constructor for the WaitTask Parcel.
     */
    constructor(parcel: Parcel) : this(
            parcel.readString() /* mTaskTitle */,
            parcel.readLong()   /* mWaitTime */
    )

    /**
     * Causes the service to wait for the assigned number of milliseconds.
     */
    override fun execute()
    {
        Thread.sleep(mWaitTime)
    }


    /* Parcel methods. */
    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeString(mTaskTitle)
        parcel.writeLong(mWaitTime)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WaitTask>
    {
        override fun createFromParcel(parcel: Parcel): WaitTask
        {
            return WaitTask(parcel)
        }

        override fun newArray(size: Int): Array<WaitTask?>
        {
            return arrayOfNulls(size)
        }
    }
}