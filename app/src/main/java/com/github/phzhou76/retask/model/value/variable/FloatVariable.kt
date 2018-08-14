package com.github.phzhou76.retask.model.value.variable

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.github.phzhou76.retask.model.value.ValueType

class FloatVariable(variableName: String, floatValue: Float)
    : Variable(ValueType.FLOAT, variableName)
{
    /* Float value that the Variable holds. */
    var mFloatValue: Float = floatValue

    constructor(parcel: Parcel) : this(
            parcel.readString()
                    ?: throw NullPointerException("Parcel Error: FloatVariable (mVariableName)"),   /* mFloatVariable */
            parcel.readFloat()                                                                      /* mFloatValue */
    )

    override fun printDebugLog()
    {
        Log.d(TAG, this.toString())
    }

    override fun toString(): String
    {
        return "$mVariableName: $mFloatValue"
    }

    /** Parcelable implementation. */
    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeString(mVariableName)
        parcel.writeFloat(mFloatValue)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FloatVariable>
    {
        private val TAG: String = FloatVariable::class.java.simpleName

        override fun createFromParcel(parcel: Parcel): FloatVariable
        {
            return FloatVariable(parcel)
        }

        override fun newArray(size: Int): Array<FloatVariable?>
        {
            return arrayOfNulls(size)
        }
    }
}