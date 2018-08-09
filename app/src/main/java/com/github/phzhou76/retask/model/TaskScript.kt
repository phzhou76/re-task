package com.github.phzhou76.retask.model

import android.os.Parcel
import android.os.Parcelable
import com.github.phzhou76.retask.model.statement.StatementBlock

class TaskScript() : Parcelable
{
    /* Hash map of variables' names to their Variable object. */

    /* Task script contents. */
    var mTaskScript: StatementBlock? = StatementBlock()

    constructor(parcel: Parcel) : this()
    {
        mTaskScript = parcel.readParcelable(StatementBlock::class.java.classLoader)
    }

    /**
     * Stops execution of the script.
     */
    fun stopExecution()
    {
        mTaskScript?.stopExecution()
    }

    /**
     * Executes the task script.
     */
    fun execute()
    {
        mTaskScript?.execute()
    }


    /* Parcelable implementation. */
    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeParcelable(mTaskScript, flags)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TaskScript>
    {
        override fun createFromParcel(parcel: Parcel): TaskScript
        {
            return TaskScript(parcel)
        }

        override fun newArray(size: Int): Array<TaskScript?>
        {
            return arrayOfNulls(size)
        }
    }
}