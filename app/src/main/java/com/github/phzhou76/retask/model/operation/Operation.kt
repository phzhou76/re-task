package com.github.phzhou76.retask.model.operation

import android.os.Parcelable
import com.github.phzhou76.retask.model.value.Value
import com.github.phzhou76.retask.model.value.ValueType

abstract class Operation(leftValue: Value?, rightValue: Value?) : Parcelable
{
    /* Operation inputs. */
    var mLeftValue: Value? = leftValue
    var mRightValue: Value? = rightValue

    /**
     * Determines what type the operation will evaluate to.
     *
     * @throws IllegalArgumentException Thrown if the left and right inputs cannot
     *      be evaluated to one type.
     * @throws NullPointerException Thrown if either the left or right inputs are
     *      null.
     *
     * @return The type of the result of the operation.
     */
    open fun evaluateValueType(): ValueType
    {
        if (!determineValidOperation())
        {
            throw IllegalArgumentException("Invalid Inputs: $TAG")
        }

        val leftValueCopy: Value? = mLeftValue
        val rightValueCopy: Value? = mRightValue

        if (leftValueCopy != null && rightValueCopy != null)
        {
            /* Priority of value types:
             *  1. Boolean
             *  2. Float
             *  3. Long
             *  4. Int
             */

            /* If mLeftValue is a Boolean, then mRightValue must be a Boolean. */
            if (leftValueCopy.mValueType == ValueType.BOOLEAN)
            {
                return ValueType.BOOLEAN
            }
            else if (leftValueCopy.mValueType == ValueType.FLOAT
                    || rightValueCopy.mValueType == ValueType.FLOAT)
            {
                return ValueType.FLOAT
            }
            else if (leftValueCopy.mValueType == ValueType.LONG
                    || rightValueCopy.mValueType == ValueType.LONG)
            {
                return ValueType.LONG
            }
            else
            {
                return ValueType.INT
            }
        }

        throw NullPointerException("Null Inputs: $TAG")
    }

    /**
     * Evaluates the operation and returns the value.
     *
     * @return The result of the operation.
     */
    abstract fun evaluateOperation(): Value

    /**
     * Determines if the left-hand and right-hand values are valid for the
     * operation.
     *
     * @return True if the inputs are valid, false otherwise.
     */
    abstract fun determineValidOperation(): Boolean

    companion object
    {
        private val TAG: String = Operation::class.java.simpleName
    }
}