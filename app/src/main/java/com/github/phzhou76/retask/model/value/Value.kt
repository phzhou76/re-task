package com.github.phzhou76.retask.model.value

import android.os.Parcelable

abstract class Value(valueType: ValueType) : Parcelable
{
    /* Type of the Value. */
    val mValueType: ValueType = valueType

    /**
     * Returns the Value object.
     *
     * @return The object's Value.
     */
    open fun getValue(): Value
    {
        return this
    }

    /**
     * Prints a debug log of the Value's contents.
     */
    abstract fun printDebugLog()
}