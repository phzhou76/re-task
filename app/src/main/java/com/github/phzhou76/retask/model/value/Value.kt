package com.github.phzhou76.retask.model.value

import android.os.Parcelable

abstract class Value(valueType: ValueType) : Parcelable
{
    /* Name of the Value. */
    open var mValueName: String = "Value"

    /* Type of the Value. */
    val mValueType: ValueType = valueType

    abstract fun printDebugLog()
}