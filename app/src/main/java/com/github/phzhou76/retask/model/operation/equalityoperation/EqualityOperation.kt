package com.github.phzhou76.retask.model.operation.equalityoperation

import com.github.phzhou76.retask.model.operation.Operation
import com.github.phzhou76.retask.model.value.BooleanValue
import com.github.phzhou76.retask.model.value.Value
import com.github.phzhou76.retask.model.value.ValueType

abstract class EqualityOperation(leftValue: Value?, rightValue: Value?)
    : Operation(leftValue, rightValue)
{
    /**
     * All evaluations for equality operations will be evaluated in
     * evaluateBooleanOperation instead, which is guaranteed to return a
     * BooleanValue object.
     *
     * @return A BooleanValue object.
     */
    override fun evaluateOperation(): BooleanValue
    {
        return evaluateBooleanOperation()
    }

    /**
     * Determines if the operation has valid inputs. In this case, the operation
     * is valid if both inputs are of type boolean, or if both inputs are of type
     * float, long, or int.
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
            return !((leftValueCopy.mValueType == ValueType.BOOLEAN && rightValueCopy.mValueType != ValueType.BOOLEAN)
                    || (leftValueCopy.mValueType != ValueType.BOOLEAN && rightValueCopy.mValueType == ValueType.BOOLEAN))
        }

        throw NullPointerException("NullPointerException: EqualityOperation")
    }

    /**
     * Evaluates the operation and returns the result as a boolean.
     *
     * @return The result of the operation, as a boolean value.
     */
    abstract fun evaluateBooleanOperation(): BooleanValue
}