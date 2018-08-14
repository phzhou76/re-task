package com.github.phzhou76.retask.model.statement.conditionalstatement

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.github.phzhou76.retask.model.operation.equalityoperation.EqualityOperation
import com.github.phzhou76.retask.model.statement.StatementBlock

/**
 * A Statement that executes the input StatementBlock if the condition is true.
 * If the condition is false, it will execute the attached ElseIfStatement if
 * available.
 *
 * @constructor Creates an IfStatement with the input condition, StatementBlock,
 *      and an optional ElseIfStatement for the else block.
 */
open class IfStatement(trueCondition: EqualityOperation?, trueBlock: StatementBlock, elseIfStatement: ElseIfStatement?)
    : ConditionalStatement(trueCondition, trueBlock)
{
    /* ElseIfStatement that will be executed if the condition returns false. */
    var mElseIfStatement: ElseIfStatement? = elseIfStatement

    constructor(parcel: Parcel) : this(
            parcel.readParcelable(EqualityOperation::class.java.classLoader)
                    ?: throw NullPointerException("Parcel Error: IfStatement (mTrueCondition)"),    /* mTrueCondition */
            parcel.readParcelable(StatementBlock::class.java.classLoader)
                    ?: throw NullPointerException("Parcel Error: IfStatement (mTrueBlock)"),        /* mTrueBlock */
            parcel.readParcelable(IfStatement::class.java.classLoader)                              /* mElseIfStatement */
    )

    /**
     * Executes the input StatementBlock if the condition is true. If not, it will
     * attempt to execute an else statement if one exists.
     */
    override fun execute()
    {
        mTrueCondition?.let {
            if (it.evaluateBooleanOperation().mBooleanValue)
            {
                mTrueBlock.execute()
            }
            else
            {
                mElseIfStatement?.execute()
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
        return "If $mTrueCondition:"
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

    companion object CREATOR : Parcelable.Creator<IfStatement>
    {
        private val TAG: String = IfStatement::class.java.simpleName

        override fun createFromParcel(parcel: Parcel): IfStatement
        {
            return IfStatement(parcel)
        }

        override fun newArray(size: Int): Array<IfStatement?>
        {
            return arrayOfNulls(size)
        }
    }
}