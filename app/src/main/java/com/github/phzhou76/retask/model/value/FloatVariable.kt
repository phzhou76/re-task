package com.github.phzhou76.retask.model.value

import android.os.Parcel
import android.os.Parcelable
import android.util.Log

class FloatVariable(variableName: String, floatValue: Float) : FloatValue(floatValue)
{
    override var mValueName: String = variableName

    constructor(parcel: Parcel) : this(
            parcel.readString()
                    ?: throw NullPointerException("Parcel Error: FloatVariable"),   /* mValueName */
            parcel.readFloat()                                                      /* mFloatValue */
    )

    override fun printDebugLog()
    {
        Log.d(TAG, this.toString())
    }

    override fun toString(): String
    {
        return "$mValueName: $mFloatValue"
    }

    /** Parcelable implementation. */
    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeString(mValueName)
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