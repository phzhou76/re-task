package com.github.phzhou76.retask.model.operation.arithmeticoperation

import com.github.phzhou76.retask.model.operation.Operation
import com.github.phzhou76.retask.model.value.Value
import com.github.phzhou76.retask.model.value.ValueType
import com.github.phzhou76.retask.model.value.numericvalue.FloatValue
import com.github.phzhou76.retask.model.value.numericvalue.IntValue
import com.github.phzhou76.retask.model.value.numericvalue.LongValue
import com.github.phzhou76.retask.model.value.numericvalue.NumericValue

abstract class ArithmeticOperation(leftValue: NumericValue?, rightValue: NumericValue?)
    : Operation(leftValue, rightValue)
{
    /**
     * Evaluates the left and right input types to determine which type should be
     * used as the return value, and then calls the relevant evaluation operation
     * to return as that value.
     *
     * @throws IllegalArgumentException Thrown if either input is of type boolean.
     * @throws NullPointerException Thrown if either input is null.
     *
     * @return The result of the operation.
     */
    override fun evaluateOperation(): NumericValue
    {
        if (!determineValidOperation())
        {
            throw IllegalArgumentException("Invalid Inputs: $TAG")
        }

        val leftValueCopy: NumericValue? = mLeftValue as NumericValue
        val rightValueCopy: NumericValue? = mRightValue as NumericValue

        if (leftValueCopy != null && rightValueCopy != null)
        {
            /* Highest priority: Cast and operate as Float values. */
            if (leftValueCopy.mValueType == ValueType.FLOAT
                    || rightValueCopy.mValueType == ValueType.FLOAT)
            {
                return evaluateFloatOperation(leftValueCopy, rightValueCopy)
            }

            /* Next priority: Cast and operate as Long values. */
            else if (leftValueCopy.mValueType == ValueType.LONG
                    || rightValueCopy.mValueType == ValueType.LONG)
            {
                return evaluateLongOperation(leftValueCopy, rightValueCopy)
            }

            /* Lowest priority: Cast and operate as Int values. */
            if (leftValueCopy.mValueType == ValueType.INT
                    || rightValueCopy.mValueType == ValueType.INT)
            {
                return evaluateIntOperation(leftValueCopy, rightValueCopy)
            }
        }

        throw NullPointerException("Null Inputs: $TAG")
    }

    /**
     * Determines if the operation has valid inputs. In this case, the operation
     * is valid if both inputs are of type float, long, or int.
     *
     * @throws NullPointerException Thrown if either input is null.
     *
     * @return True if both inputs are valid, false otherwise.
     */
    override fun determineValidOperation(): Boolean
    {
        val leftValueCopy: Value? = mLeftValue
        val rightValueCopy: Value? = mRightValue

        if (leftValueCopy != null && rightValueCopy != null)
        {
            return !(leftValueCopy.mValueType == ValueType.BOOLEAN
                    || rightValueCopy.mValueType == ValueType.BOOLEAN)
        }

        throw NullPointerException("Null Inputs: $TAG")
    }

    /**
     * Evaluates the operation and returns the result as a float.
     *
     * @param leftValue Left value to operate with.
     * @param rightValue Right value to operate with.
     *
     * @return The result of the operation, as a float value.
     */
    abstract fun evaluateFloatOperation(leftValue: NumericValue,
                                        rightValue: NumericValue): FloatValue

    /**
     * Evaluates the operation and returns the result as a long.
     *
     * @param leftValue Left value to operate with.
     * @param rightValue Right value to operate with.
     *
     * @return The result of the operation, as a long value.
     */
    abstract fun evaluateLongOperation(leftValue: NumericValue,
                                       rightValue: NumericValue): LongValue

    /**
     * Evaluates the operation and returns the result as an integer.
     *
     * @param leftValue Left value to operate with.
     * @param rightValue Right value to operate with.
     *
     * @return The result of the operation, as an integer value.
     */
    abstract fun evaluateIntOperation(leftValue: NumericValue,
                                      rightValue: NumericValue): IntValue

    companion object
    {
        private val TAG: String = ArithmeticOperation::class.java.simpleName
    }
}