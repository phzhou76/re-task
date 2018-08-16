package com.github.phzhou76.retask.model.statement.conditionalstatement

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.github.phzhou76.retask.model.statement.StatementBlock

/**
 * A Statement that should only be linked to an IfStatement. If all other
 * IfStatements and ElseIfStatements have been executed, this ElseStatement can
 * be executed.
 *
 * @constructor Creates an ElseStatement with the input StatementBlock.
 */
class ElseStatement(statementBlock: StatementBlock)
    : ElseIfStatement(null, statementBlock, null)
{
    constructor(parcel: Parcel) : this(
            parcel.readParcelable<StatementBlock>(StatementBlock::class.java.classLoader)
                    ?: throw NullPointerException("Parcel Error: $TAG (mTrueBlock)")    /* mTrueBlock */
    )

    override fun execute()
    {
        mTrueBlock.execute()
    }

    override fun printDebugLog()
    {
        Log.d(TAG, this.toString())
        mTrueBlock.printDebugLog()
    }

    override fun toString(): String
    {
        return "Else:"
    }

    /** Parcelable implementation. */
    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeParcelable(mTrueBlock, flags)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ElseStatement>
    {
        private val TAG: String = ElseStatement::class.java.simpleName

        override fun createFromParcel(parcel: Parcel): ElseStatement
        {
            return ElseStatement(parcel)
        }

        override fun newArray(size: Int): Array<ElseStatement?>
        {
            return arrayOfNulls(size)
        }
    }
}