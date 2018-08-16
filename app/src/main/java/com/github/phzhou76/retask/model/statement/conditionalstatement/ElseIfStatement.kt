package com.github.phzhou76.retask.model.statement.conditionalstatement

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.github.phzhou76.retask.model.operation.equalityoperation.EqualityOperation
import com.github.phzhou76.retask.model.statement.StatementBlock

/**
 * A Statement that should only be linked to an IfStatement. If the condition is
 * true, the StatementBlock will be executed. If not, then the next ElseIfStatement
 * will be executed if available.
 *
 * @constructor Creates an ElseIfStatement with the input condition, StatementBlock,
 *      and an optional ElseIfStatement for the else block.
 */
open class ElseIfStatement(trueCondition: EqualityOperation?, trueBlock: StatementBlock, elseIfStatement: ElseIfStatement?)
    : IfStatement(trueCondition, trueBlock, elseIfStatement)
{
    constructor(parcel: Parcel) : this(
            parcel.readParcelable(EqualityOperation::class.java.classLoader)
                    ?: throw NullPointerException("Parcel Error: $TAG (mTrueCondition)"),    /* mTrueCondition */
            parcel.readParcelable(StatementBlock::class.java.classLoader)
                    ?: throw NullPointerException("Parcel Error: $TAG (mTrueBlock)"),        /* mTrueBlock */
            parcel.readParcelable(IfStatement::class.java.classLoader)                       /* mElseIfStatement */
    )

    override fun printDebugLog()
    {
        Log.d(TAG, this.toString())
        mTrueBlock.printDebugLog()
    }

    override fun toString(): String
    {
        return "Else if $mTrueCondition:"
    }

    /** Parcelable implementation. */
    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeParcelable(mTrueCondition, flags)
        parcel.writeParcelable(mTrueBlock, flags)
        parcel.writeParcelable(mElseIfStatement, flags)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ElseIfStatement>
    {
        private val TAG: String = ElseIfStatement::class.java.simpleName

        override fun createFromParcel(parcel: Parcel): ElseIfStatement
        {
            return ElseIfStatement(parcel)
        }

        override fun newArray(size: Int): Array<ElseIfStatement?>
        {
            return arrayOfNulls(size)
        }
    }
}