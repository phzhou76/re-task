package com.github.phzhou76.retask.model.value

import android.os.Parcel
import android.os.Parcelable
import android.util.Log

class IntVariable(variableName: String, intValue: Int) : IntValue(intValue)
{
    override var mValueName: String = variableName

    constructor(parcel: Parcel) : this(
            parcel.readString()
                    ?: throw NullPointerException("Parcel Error: IntVariable"), /* mValueName */
            parcel.readInt()                                                    /* mLongValue */
    )

    override fun printDebugLog()
    {
        Log.d(TAG, this.toString())
    }

    override fun toString(): String
    {
        return "$mValueName: $mIntValue"
    }

    /** Parcelable implementation. */
    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeString(mValueName)
        parcel.writeInt(mIntValue)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<IntVariable>
    {
        private val TAG: String = IntVariable::class.java.simpleName

        override fun createFromParcel(parcel: Parcel): IntVariable
        {
            return IntVariable(parcel)
        }

        override fun newArray(size: Int): Array<IntVariable?>
        {
            return arrayOfNulls(size)
        }
    }
}