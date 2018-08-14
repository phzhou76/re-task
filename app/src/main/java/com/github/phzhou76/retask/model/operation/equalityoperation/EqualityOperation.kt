package com.github.phzhou76.retask.model.operation.equalityoperation

import com.github.phzhou76.retask.model.operation.Operation
import com.github.phzhou76.retask.model.value.Value
import com.github.phzhou76.retask.model.value.rvalue.BooleanValue
import com.github.phzhou76.retask.model.value.rvalue.RValue

abstract class EqualityOperation(leftValue: Value, rightValue: Value)
    : Operation(leftValue, rightValue)
{
    override fun evaluateOperation(): RValue
    {
        return evaluateBooleanOperation()
    }

    /**
     * Evaluates the operation and returns the result as a boolean.
     *
     * @return The result of the operation, as a boolean value.
     */
    abstract fun evaluateBooleanOperation(): BooleanValue
}