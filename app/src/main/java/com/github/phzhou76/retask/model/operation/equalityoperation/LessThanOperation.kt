package com.github.phzhou76.retask.model.operation.equalityoperation

import android.os.Parcel
import android.os.Parcelable
import com.github.phzhou76.retask.model.value.BooleanValue
import com.github.phzhou76.retask.model.value.ValueType
import com.github.phzhou76.retask.model.value.numericvalue.NumericValue

/**
 * Determines if one value is less than the other value.
 *
 * @constructor Creates a LessThanOperation that determines if the left value is
 *      less than the right value.
 */
class LessThanOperation(leftValue: NumericValue?, rightValue: NumericValue?)
    : NumericEqualityOperation(leftValue, rightValue)
{
    constructor(parcel: Parcel) : this(
            parcel.readParcelable(NumericValue::class.java.classLoader),   /* mLeftValue */
            parcel.readParcelable(NumericValue::class.java.classLoader)    /* mRightValue */
    )

    /**
     * Determines if the left value is less than the right value.
     *
     * @throws IllegalArgumentException Thrown if the two inputs cannot be compared.
     * @throws NullPointerException Thrown if either input is null.
     *
     * @return A BooleanValue holding true if the left value is strictly less than
     *      the right value, or false if the left value is not strictly less than
     *      the right value.
     */
    override fun evaluateBooleanOperation(): BooleanValue
    {
        if (!determineValidOperation())
        {
            throw IllegalArgumentException("Invalid Inputs: $TAG")
        }

        val leftValueCopy: NumericValue? = mLeftValue as NumericValue
        val rightValueCopy: NumericValue? = mRightValue as NumericValue

        if (leftValueCopy != null && rightValueCopy != null)
        {
            /* Cast and compare as Float values. */
            if (leftValueCopy.mValueType == ValueType.FLOAT
                    || rightValueCopy.mValueType == ValueType.FLOAT)
            {
                return BooleanValue(leftValueCopy.toFloatValue().mFloatValue <
                        rightValueCopy.toFloatValue().mFloatValue)
            }

            /* Cast and compare as Long values. */
            else if (leftValueCopy.mValueType == ValueType.LONG
                    || rightValueCopy.mValueType == ValueType.LONG)
            {
                return BooleanValue(leftValueCopy.toLongValue().mLongValue <
                        rightValueCopy.toLongValue().mLongValue)
            }

            /* Cast and compare as Int values. */
            else if (leftValueCopy.mValueType == ValueType.INT
                    || rightValueCopy.mValueType == ValueType.INT)
            {
                return BooleanValue(leftValueCopy.toIntValue().mIntValue <
                        rightValueCopy.toIntValue().mIntValue)
            }
        }

        throw NullPointerException("Null Inputs: $TAG")
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

    companion object CREATOR : Parcelable.Creator<LessThanOperation>
    {
        private val TAG: String = LessThanOperation::class.java.simpleName

        override fun createFromParcel(parcel: Parcel): LessThanOperation
        {
            return LessThanOperation(parcel)
        }

        override fun newArray(size: Int): Array<LessThanOperation?>
        {
            return arrayOfNulls(size)
        }
    }
}