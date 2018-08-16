package com.github.phzhou76.retask.model.operation.arithmeticoperation

import android.os.Parcel
import android.os.Parcelable
import com.github.phzhou76.retask.model.value.numericvalue.FloatValue
import com.github.phzhou76.retask.model.value.numericvalue.IntValue
import com.github.phzhou76.retask.model.value.numericvalue.LongValue
import com.github.phzhou76.retask.model.value.numericvalue.NumericValue

/**
 * Divides the left value by the right value.
 *
 * @constructor Creates a DivideOperation that divides the left value by the right
 *      value and returns the result.
 */
class DivideOperation(leftValue: NumericValue?, rightValue: NumericValue?)
    : ArithmeticOperation(leftValue, rightValue)
{
    constructor(parcel: Parcel) : this(
            parcel.readParcelable(NumericValue::class.java.classLoader),   /* mLeftValue */
            parcel.readParcelable(NumericValue::class.java.classLoader)    /* mRightValue */
    )

    override fun evaluateFloatOperation(leftValue: NumericValue, rightValue: NumericValue): FloatValue
    {
        if (rightValue.toFloatValue().mFloatValue == 0.0f)
        {
            throw IllegalArgumentException("Divisor input is 0.0f: $TAG")
        }

        return FloatValue(leftValue.toFloatValue().mFloatValue / rightValue.toFloatValue().mFloatValue)
    }

    override fun evaluateLongOperation(leftValue: NumericValue, rightValue: NumericValue): LongValue
    {
        if (rightValue.toLongValue().mLongValue == 0L)
        {
            throw IllegalArgumentException("Divisor input is 0L: $TAG")
        }

        return LongValue(leftValue.toLongValue().mLongValue / rightValue.toLongValue().mLongValue)
    }

    override fun evaluateIntOperation(leftValue: NumericValue, rightValue: NumericValue): IntValue
    {
        if (rightValue.toIntValue().mIntValue == 0)
        {
            throw IllegalArgumentException("Divisor input is 0: $TAG")
        }

        return IntValue(leftValue.toIntValue().mIntValue / rightValue.toIntValue().mIntValue)
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

    companion object CREATOR : Parcelable.Creator<DivideOperation>
    {
        private val TAG: String = DivideOperation::class.java.simpleName

        override fun createFromParcel(parcel: Parcel): DivideOperation
        {
            return DivideOperation(parcel)
        }

        override fun newArray(size: Int): Array<DivideOperation?>
        {
            return arrayOfNulls(size)
        }
    }

}