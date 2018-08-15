package com.github.phzhou76.retask.model.operation.equalityoperation

import com.github.phzhou76.retask.model.value.Value
import com.github.phzhou76.retask.model.value.ValueType

abstract class NumericEqualityOperation(leftValue: Value?, rightValue: Value?)
    : EqualityOperation(leftValue, rightValue)
{
    /**
     * Determines if the operation has valid inputs. In this case, the operation
     * is valid if both inputs are of type float or int.
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

        throw NullPointerException("NullPointerException: EqualityOperation")
    }
}