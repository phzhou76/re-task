package com.github.phzhou76.retask.model.value

import android.os.Parcelable

abstract class Value(valueType: ValueType) : Parcelable
{
    /* Type of the Value. */
    val mValueType: ValueType = valueType

    /**
     * Returns this Value. Should be used by extending classes to extract a Value
     * from them.
     */
    open fun evaluateValue(): Value
    {
        return this
    }

    abstract fun printDebugLog()
}