package com.github.phzhou76.retask.model.operation.arithmeticoperation

import android.os.Parcel
import android.os.Parcelable
import com.github.phzhou76.retask.model.value.numericvalue.FloatValue
import com.github.phzhou76.retask.model.value.numericvalue.IntValue
import com.github.phzhou76.retask.model.value.numericvalue.LongValue
import com.github.phzhou76.retask.model.value.numericvalue.NumericValue

/**
 * Multiplies two values.
 *
 * @constructor Creates a MultiplyOperation that multiplies two values together
 *      and returns the result.
 */
class MultiplyOperation(leftValue: NumericValue?, rightValue: NumericValue?)
    : ArithmeticOperation(leftValue, rightValue)
{
    constructor(parcel: Parcel) : this(
            parcel.readParcelable(NumericValue::class.java.classLoader),   /* mLeftValue */
            parcel.readParcelable(NumericValue::class.java.classLoader)    /* mRightValue */
    )

    override fun evaluateFloatOperation(leftValue: NumericValue, rightValue: NumericValue): FloatValue
    {
        return FloatValue(leftValue.toFloatValue().mFloatValue * rightValue.toFloatValue().mFloatValue)
    }

    override fun evaluateLongOperation(leftValue: NumericValue, rightValue: NumericValue): LongValue
    {
        return LongValue(leftValue.toLongValue().mLongValue * rightValue.toLongValue().mLongValue)
    }

    override fun evaluateIntOperation(leftValue: NumericValue, rightValue: NumericValue): IntValue
    {
        return IntValue(leftValue.toIntValue().mIntValue * rightValue.toIntValue().mIntValue)
    }

    /** Parcelable implementation. */
    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeParcelable(mLeftValue, flags)
        parcel.writeParcelable(mRightValue, flags)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MultiplyOperation>
    {
        override fun createFromParcel(parcel: Parcel): MultiplyOperation
        {
            return MultiplyOperation(parcel)
        }

        override fun newArray(size: Int): Array<MultiplyOperation?>
        {
            return arrayOfNulls(size)
        }
    }
}