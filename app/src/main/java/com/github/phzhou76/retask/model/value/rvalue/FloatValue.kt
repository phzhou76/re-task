package com.github.phzhou76.retask.model.value.rvalue

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.github.phzhou76.retask.model.value.ValueType

class FloatValue(floatValue: Float) : RValue(ValueType.FLOAT)
{
    var mFloatValue: Float = floatValue

    constructor(parcel: Parcel) : this(
            parcel.readFloat()  /* mFloatValue */
    )

    override fun printDebugLog()
    {
        Log.d(TAG, "Float Value: " + this)
    }

    override fun toString(): String
    {
        return "$mFloatValue"
    }

    /** Parcelable implementation. */
    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeFloat(mFloatValue)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FloatValue>
    {
        private val TAG: String = FloatValue::class.java.simpleName

        override fun createFromParcel(parcel: Parcel): FloatValue
        {
            return FloatValue(parcel)
        }

        override fun newArray(size: Int): Array<FloatValue?>
        {
            return arrayOfNulls(size)
        }
    }
}