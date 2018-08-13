package com.github.phzhou76.retask.model.statement.conditionalstatement

import android.os.Parcel
import android.os.Parcelable
import com.github.phzhou76.retask.model.operation.equalityoperation.EmptyEqualityOperation
import com.github.phzhou76.retask.model.operation.equalityoperation.EqualityOperation
import com.github.phzhou76.retask.model.statement.StatementBlock

class WhileStatement(trueCondition: EqualityOperation, trueBlock: StatementBlock)
    : ConditionalStatement(trueCondition, trueBlock)
{
    constructor(parcel: Parcel) : this(
            parcel.readParcelable(EqualityOperation::class.java.classLoader)
                ?: EmptyEqualityOperation(),
            parcel.readParcelable(StatementBlock::class.java.classLoader)
    )

    override fun execute()
    {
        while(mTrueCondition.evaluateBooleanOperation().mBooleanValue && !mStopExecution)
        {
            mTrueBlock.execute()
        }
    }

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