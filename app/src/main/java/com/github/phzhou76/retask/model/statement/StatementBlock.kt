package com.github.phzhou76.retask.model.statement

import android.os.Parcel
import android.os.Parcelable
import android.util.Log

/**
 * A Statement that holds other Statements for organizational purposes.
 */
class StatementBlock() : Statement()
{
    /* List of Statements that this StatementBlock will execute. */
    var mStatementList: MutableList<Statement> = mutableListOf()

    constructor(parcel: Parcel) : this()
    {
        parcel.readList(mStatementList, StatementBlock::class.java.classLoader)
    }

    /**
     * Stops the execution of the contents of the StatementBlock, and signals
     * any child Statements to stop execution as well.
     */
    override fun stopExecution()
    {
        super.stopExecution()

        mStatementList.iterator().forEach {
            it.stopExecution()
        }
    }

    /**
     * Executes contents of the StatementBlock.
     */
    override fun execute()
    {
        for (i in 0 until mStatementList.size)
        {
            if (mStopExecution)
            {
                break
            }

            Log.d(TAG, mStatementList[i].toString())

            mStatementList[i].execute()
        }
    }

    override fun printDebugLog()
    {
        Log.d(TAG, this.toString())

        mStatementList.iterator().forEach {
            it.printDebugLog()
        }
    }

    override fun toString(): String
    {
        return "Statement Block"
    }


    /* Parcelable implementation. */
    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeList(mStatementList)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StatementBlock>
    {
        private val TAG: String = StatementBlock::class.java.simpleName

        override fun createFromParcel(parcel: Parcel): StatementBlock
        {
            return StatementBlock(parcel)
        }

        override fun newArray(size: Int): Array<StatementBlock?>
        {
            return arrayOfNulls(size)
        }
    }
}