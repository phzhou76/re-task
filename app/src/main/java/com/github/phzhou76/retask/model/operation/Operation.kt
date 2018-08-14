package com.github.phzhou76.retask.model.operation

import android.os.Parcelable
import com.github.phzhou76.retask.model.TaskScript
import com.github.phzhou76.retask.model.value.Value
import com.github.phzhou76.retask.model.value.rvalue.RValue

abstract class Operation(leftValue: Value, rightValue: Value) : Parcelable
{
    /* All operations need a reference to the TaskScript to access Variables. */
    var mTaskScript: TaskScript? = TaskScript.getSharedInstance()

    /* Operator parameters. */
    var mLeftValue: Value = leftValue
    var mRightValue: Value = rightValue

    /**
     * Evaluates the operation and returns the value.
     *
     * @return The result of the operation.
     */
    abstract fun evaluateOperation(): RValue

    /**
     * Determines if the left-hand and right-hand values are valid for the
     * operation.
     *
     * @return True if the inputs are valid, false otherwise.
     */
    abstract fun determineValidOperation(): Boolean
}