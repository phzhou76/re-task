package com.github.phzhou76.retask.model.statement

import android.os.Parcel
import android.os.Parcelable
import android.util.Log

/**
 * A Statement that holds other Statements for organizational purposes.
 */
class StatementBlock() : Statement()
{
    override var mStatementLabel: String = "Statement Block"

    var mStatementList: MutableList<Statement> = mutableListOf()

    /**
     * Required constructor for a Parcelable implementation.
     *
     * @constructor Takes the information from the Parcel to recreate the object.
     */
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

            Log.d("StatementBlock", mStatementList[i].mStatementLabel)

            mStatementList[i].execute()
        }
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