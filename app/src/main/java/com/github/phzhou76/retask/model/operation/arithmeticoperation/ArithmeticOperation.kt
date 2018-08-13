package com.github.phzhou76.retask.model.operation.arithmeticoperation

import com.github.phzhou76.retask.model.operation.Operation
import com.github.phzhou76.retask.model.value.Value
import com.github.phzhou76.retask.model.value.rvalue.FloatValue
import com.github.phzhou76.retask.model.value.rvalue.IntValue

abstract class ArithmeticOperation(leftValue: Value, rightValue: Value)
    : Operation(leftValue, rightValue)
{
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