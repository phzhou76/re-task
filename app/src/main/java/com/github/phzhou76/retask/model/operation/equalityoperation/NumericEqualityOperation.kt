package com.github.phzhou76.retask.model.operation.equalityoperation

import com.github.phzhou76.retask.model.value.Value
import com.github.phzhou76.retask.model.value.ValueType
import com.github.phzhou76.retask.model.value.numericvalue.NumericValue

abstract class NumericEqualityOperation(leftValue: NumericValue?, rightValue: NumericValue?)
    : EqualityOperation(leftValue, rightValue)
{
    /**
     * Determines if the operation has valid inputs. In this case, the operation
     * is valid if both inputs are of type float, long, or int.
     *
     * @throws NullPointerException Thrown if either input is null.
     *
     * @return True if the inputs are valid, false otherwise.
     */
    override fun determineValidOperation(): Boolean
    {
        val leftValueCopy: Value? = mLeftValue
        val rightValueCopy: Value? = mRightValue

        if (leftValueCopy != null && rightValueCopy != null)
        {
            return !(leftValueCopy.mValueType == ValueType.BOOLEAN || rightValueCopy.mValueType == ValueType.BOOLEAN)
        }

        throw NullPointerException("Null Inputs: $TAG")
    }

    companion object
    {
        private val TAG: String = NumericEqualityOperation::class.java.simpleName
    }
}