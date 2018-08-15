package com.github.phzhou76.retask.model.operation.equalityoperation

import com.github.phzhou76.retask.model.value.Value
import com.github.phzhou76.retask.model.value.ValueType
import com.github.phzhou76.retask.model.value.BooleanValue

class EqualOperation(leftValue: Value?, rightValue: Value?) : EqualityOperation(leftValue, rightValue)
{
    override fun evaluateBooleanOperation(): BooleanValue
    {
        if (!determineValidOperation())
        {
            throw IllegalArgumentException("IllegalArgumentException: EqualOperation")
        }

        var leftValueCopy: Value? = mLeftValue
        var rightValueCopy: Value? = mRightValue

        if (leftValueCopy != null && rightValueCopy != null)
        {
            

            if (leftValueCopy.mValueType == ValueType.BOOLEAN && rightValueCopy.mValueType == ValueType.BOOLEAN)
            {

            }
        }

        throw NullPointerException("NullPointerException: EqualOperation")
    }
}