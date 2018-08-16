package com.github.phzhou76.retask.model.operation.arithmeticoperation

import android.os.Parcel
import android.os.Parcelable
import com.github.phzhou76.retask.model.value.numericvalue.FloatValue
import com.github.phzhou76.retask.model.value.numericvalue.IntValue
import com.github.phzhou76.retask.model.value.numericvalue.LongValue
import com.github.phzhou76.retask.model.value.numericvalue.NumericValue

/**
 * Calculates the modulus of the left value with the right value.
 *
 * @constructor Creates a ModulusOperation that calculates the modulus of the left
 *      value with the right value and returns the result.
 */
class ModulusOperation(leftValue: NumericValue?, rightValue: NumericValue?)
    : ArithmeticOperation(leftValue, rightValue)
{
    constructor(parcel: Parcel) : this(
            parcel.readParcelable(NumericValue::class.java.classLoader),   /* mLeftValue */
            parcel.readParcelable(NumericValue::class.java.classLoader)    /* mRightValue */
    )

    override fun evaluateFloatOperation(leftValue: NumericValue, rightValue: NumericValue): FloatValue
    {
        return FloatValue(leftValue.toFloatValue().mFloatValue % rightValue.toFloatValue().mFloatValue)
    }

    override fun evaluateLongOperation(leftValue: NumericValue, rightValue: NumericValue): LongValue
    {
        return LongValue(leftValue.toLongValue().mLongValue % rightValue.toLongValue().mLongValue)
    }

    override fun evaluateIntOperation(leftValue: NumericValue, rightValue: NumericValue): IntValue
    {
        return IntValue(leftValue.toIntValue().mIntValue % rightValue.toIntValue().mIntValue)
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

    companion object CREATOR : Parcelable.Creator<ModulusOperation>
    {
        override fun createFromParcel(parcel: Parcel): ModulusOperation
        {
            return ModulusOperation(parcel)
        }

        override fun newArray(size: Int): Array<ModulusOperation?>
        {
            return arrayOfNulls(size)
        }
    }

}