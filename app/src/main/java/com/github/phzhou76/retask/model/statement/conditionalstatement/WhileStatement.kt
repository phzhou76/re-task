package com.github.phzhou76.retask.model.statement.conditionalstatement

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.github.phzhou76.retask.model.operation.equalityoperation.EqualityOperation
import com.github.phzhou76.retask.model.statement.StatementBlock

/**
 * A Statement that continually loops through a StatementBlock until the given
 * condition is false.
 *
 * @constructor Creates a WhileStatement that will repeat execution of the input
 *      StatementBlock until the input condition returns false or until the script
 *      is manually stopped.
 */
class WhileStatement(trueCondition: EqualityOperation?, trueBlock: StatementBlock)
    : ConditionalStatement(trueCondition, trueBlock)
{
    constructor(parcel: Parcel) : this(
            parcel.readParcelable(EqualityOperation::class.java.classLoader)
                    ?: throw NullPointerException("Parcel Error: WhileStatement (mTrueCondition)"),     /* mTrueCondition */
            parcel.readParcelable(StatementBlock::class.java.classLoader)
                    ?: throw NullPointerException("Parcel Error: WhileStatement (mTrueBlock)")          /* mTrueBlock */
    )

    /**
     * Continues to execute the input StatementBlock until either the condition
     * returns false or until the script is manually stopped.
     */
    override fun execute()
    {
        mTrueCondition?.let {
            while (it.evaluateBooleanOperation().mBooleanValue && !mStopExecution)
            {
                mTrueBlock.execute()
            }
        }
    }

    override fun printDebugLog()
    {
        Log.d(TAG, this.toString())
        mTrueBlock.printDebugLog()
    }

    override fun toString(): String
    {
        return "While: $mTrueCondition:"
    }

    /** Parcelable implementation. */
    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeParcelable(mTrueCondition, flags)
        parcel.writeParcelable(mTrueBlock, flags)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WhileStatement>
    {
        private val TAG: String = WhileStatement::class.java.simpleName

        override fun createFromParcel(parcel: Parcel): WhileStatement
        {
            return WhileStatement(parcel)
        }

        override fun newArray(size: Int): Array<WhileStatement?>
        {
            return arrayOfNulls(size)
        }
    }
}