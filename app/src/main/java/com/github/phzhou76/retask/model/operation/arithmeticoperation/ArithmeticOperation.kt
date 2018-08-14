package com.github.phzhou76.retask.model.operation.arithmeticoperation

import com.github.phzhou76.retask.model.operation.Operation
import com.github.phzhou76.retask.model.value.Value
import com.github.phzhou76.retask.model.value.ValueType
import com.github.phzhou76.retask.model.value.rvalue.FloatValue
import com.github.phzhou76.retask.model.value.rvalue.IntValue
import com.github.phzhou76.retask.model.value.rvalue.RValue

abstract class ArithmeticOperation(leftValue: Value, rightValue: Value)
    : Operation(leftValue, rightValue)
{
    override fun evaluateOperation(): RValue
    {
        if (!determineValidOperation())
        {
            throw IllegalArgumentException("ArithmeticOperation: Invalid inputs.")
        }

        return if (mLeftValue.mValueType == ValueType.INT && mRightValue.mValueType == ValueType.INT)
        {
            evaluateIntOperation()
        }
        else
        {
            evaluateFloatOperation()
        }
    }

    /**
     * Only ints and floats can be inputs for arithmetic operations.
     *
     * @return True if both inputs are valid, false otherwise.
     */
    override fun determineValidOperation(): Boolean
    {
        if (mLeftValue.mValueType == ValueType.BOOLEAN
                || mRightValue.mValueType == ValueType.BOOLEAN)
        {
            return false
        }

        return true
    }

    /**
     * Evaluates the operation and returns the result as an integer.
     *
     * @return The result of the operation, as an integer value.
     */
    abstract fun evaluateIntOperation(): IntValue

    /**
     * Evaluates the operation and returns the result as a float.
     *
     * @return The result of the operation, as a float value.
     */
    abstract fun evaluateFloatOperation(): FloatValue
}