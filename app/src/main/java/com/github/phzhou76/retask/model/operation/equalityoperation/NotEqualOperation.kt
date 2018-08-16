package com.github.phzhou76.retask.model.operation.equalityoperation

import android.os.Parcel
import android.os.Parcelable
import com.github.phzhou76.retask.model.value.BooleanValue
import com.github.phzhou76.retask.model.value.Value
import com.github.phzhou76.retask.model.value.ValueType
import com.github.phzhou76.retask.model.value.numericvalue.NumericValue

/**
 * Determines if one value is not equal to another.
 *
 * @constructor Creates a NotEqualOperation that determines if the left value is
 *      not equal to the right value.
 */
class NotEqualOperation(leftValue: Value?, rightValue: Value?) : EqualityOperation(leftValue, rightValue)
{
    constructor(parcel: Parcel) : this(
            parcel.readParcelable(Value::class.java.classLoader),   /* mLeftValue */
            parcel.readParcelable(Value::class.java.classLoader)    /* mRightValue */
    )

    /**
     * Determines if the left and right values are not equal.
     *
     * @throws IllegalArgumentException Thrown if the two inputs cannot be compared.
     * @throws NullPointerException Thrown if either input is null.
     *
     * @return A BooleanValue holding true if the two values are not equal, or a
     *      BooleanValue holding false if the two values are equal.
     */
    override fun evaluateBooleanOperation(): BooleanValue
    {
        if (!determineValidOperation())
        {
            throw IllegalArgumentException("Invalid Inputs: $TAG")
        }

        val leftValueCopy: Value? = mLeftValue
        val rightValueCopy: Value? = mRightValue

        if (leftValueCopy != null && rightValueCopy != null)
        {
            /* Compare as Boolean values. */
            if (leftValueCopy.mValueType == ValueType.BOOLEAN
                    && rightValueCopy.mValueType == ValueType.BOOLEAN)
            {
                val leftBooleanValue: BooleanValue = (leftValueCopy as BooleanValue)
                val rightBooleanValue: BooleanValue = (rightValueCopy as BooleanValue)

                return BooleanValue(leftBooleanValue.mBooleanValue
                        != rightBooleanValue.mBooleanValue)
            }

            /* Cast and compare as Float values. */
            else if (leftValueCopy.mValueType == ValueType.FLOAT
                    || rightValueCopy.mValueType == ValueType.FLOAT)
            {
                val numericLeftValue: NumericValue = (leftValueCopy as NumericValue)
                val numericRightValue: NumericValue = (rightValueCopy as NumericValue)

                return BooleanValue(numericLeftValue.toFloatValue().mFloatValue
                        != numericRightValue.toFloatValue().mFloatValue)
            }

            /* Cast and compare as Long values. */
            else if (leftValueCopy.mValueType == ValueType.LONG
                    || rightValueCopy.mValueType == ValueType.LONG)
            {
                val numericLeftValue: NumericValue = (leftValueCopy as NumericValue)
                val numericRightValue: NumericValue = (rightValueCopy as NumericValue)

                return BooleanValue(numericLeftValue.toLongValue().mLongValue
                        != numericRightValue.toLongValue().mLongValue)
            }

            /* Cast and compare as Int values. */
            else if (leftValueCopy.mValueType == ValueType.INT
                    || rightValueCopy.mValueType == ValueType.INT)
            {
                val numericLeftValue: NumericValue = (leftValueCopy as NumericValue)
                val numericRightValue: NumericValue = (rightValueCopy as NumericValue)

                return BooleanValue(numericLeftValue.toIntValue().mIntValue
                        != numericRightValue.toIntValue().mIntValue)
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

    companion object CREATOR : Parcelable.Creator<NotEqualOperation>
    {
        private val TAG: String = NotEqualOperation::class.java.simpleName

        override fun createFromParcel(parcel: Parcel): NotEqualOperation
        {
            return NotEqualOperation(parcel)
        }

        override fun newArray(size: Int): Array<NotEqualOperation?>
        {
            return arrayOfNulls(size)
        }
    }


}