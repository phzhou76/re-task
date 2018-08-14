package com.github.phzhou76.retask.model.value

import android.os.Parcelable

abstract class Value(valueType: ValueType) : Parcelable
{
    /* Type of the Value. */
    val mValueType: ValueType = valueType

    abstract fun printDebugLog()
}