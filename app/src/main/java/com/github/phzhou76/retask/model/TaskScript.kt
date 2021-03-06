package com.github.phzhou76.retask.model

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.github.phzhou76.retask.model.statement.StatementBlock
import com.github.phzhou76.retask.model.value.Value

/**
 * Holds the variables and the script that relies on it for execution. It also
 * takes care of starting and stopping the execution of the script.
 */
class TaskScript() : Parcelable
{
    /* Hash map of variables' names to their respective objects. */
    var mVariables: HashMap<String, Value> = HashMap()

    /* Task script contents. */
    var mTaskScript: StatementBlock = StatementBlock()

    constructor(parcel: Parcel) : this()
    {
        readScriptFromParcel(parcel)
    }

    /**
     * Stops execution of the script.
     */
    fun stopExecution()
    {
        Log.d(TAG, "stopExecution")
        mTaskScript.stopExecution()
    }

    /**
     * Executes the task script.
     */
    fun execute()
    {
        Log.d(TAG, "execute")
        mTaskScript.execute()
    }

    /**
     * Logs the value of each Variable and Statement in the task script.
     */
    fun printDebugLog()
    {
        Log.d(TAG, "printDebugLog")

        Log.d(TAG, "Variables Section:")
        mVariables.iterator().forEach {
            it.value.printDebugLog()
        }

        Log.d(TAG, "Statements Section:")
    }

    /**
     * Recreates the TaskScript object using data from the Parcel.
     *
     * @param parcel The Parcel object that encapsulates the TaskScript data.
     *
     * @throws NullPointerException Thrown if a Parcel read error occurred.
     */
    private fun readScriptFromParcel(parcel: Parcel)
    {
        /* Read Variable hash map and recreate it. */
        val variableCount: Int = parcel.readInt()

        for (i in 0 until variableCount)
        {
            val variableName: String = parcel.readString()
                    ?: throw NullPointerException("Parcel Error: TaskScript")
            val variable: Value = parcel.readParcelable(Value::class.java.classLoader)
                    ?: throw NullPointerException("Parcel Error: TaskScript")
            mVariables[variableName] = variable
        }

        /* Read script data and recreate it. */
        mTaskScript = parcel.readParcelable(StatementBlock::class.java.classLoader)
                ?: throw NullPointerException("Parcel Error: TaskScript")
    }

    /**
     * Writes the TaskScript's data to a Parcel.
     *
     * @param parcel The Parcel object to write the TaskScript's data to.
     * @param flags Integer flags that are required for writing Parcelable objects.
     */
    private fun writeScriptToParcel(parcel: Parcel, flags: Int)
    {
        /* Write Variable hash map to Parcel. */
        parcel.writeInt(mVariables.size)
        mVariables.iterator().forEach {
            parcel.writeString(it.key)
            parcel.writeParcelable(it.value, flags)
        }

        /* Write script to Parcel. */
        parcel.writeParcelable(mTaskScript, flags)
    }

    /** Parcelable implementation. */
    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        writeScriptToParcel(parcel, flags)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TaskScript>
    {
        private val TAG: String = TaskScript::class.java.simpleName

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