package com.github.phzhou76.retask.model.value.numericvalue

import com.github.phzhou76.retask.model.value.Value
import com.github.phzhou76.retask.model.value.ValueType

abstract class NumericValue(valueType: ValueType) : Value(valueType)
{
    /**
     * Evaluate the value as an integer value.
     *
     * @return The value as an integer.
     */
    abstract fun evaluateValueAsInt(): Int

    /**
     * Evaluate the value as a float value.
     *
     * @return The value as a float.
     */
    abstract fun evaluateValueAsFloat(): Float

    /**
     * Evaluate the value as a long value.
     *
     * @return The value as a long.
     */
    abstract fun evaluateValueAsLong(): Long
}