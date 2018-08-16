package com.github.phzhou76.retask.model.value.numericvalue

import android.os.Parcel
import android.os.Parcelable
import android.util.Log

class LongVariable(variableName: String, longValue: Long) : LongValue(longValue)
{
    override var mValueName: String = variableName

    constructor(parcel: Parcel) : this(
            parcel.readString()
                    ?: throw NullPointerException("Parcel Error: $TAG (mValueName)"),   /* mValueName */
            parcel.readLong()                                                           /* mLongValue */
    )

    override fun printDebugLog()
    {
        Log.d(TAG, "$mValueName: $mLongValue")
    }

    override fun toString(): String
    {
        return mValueName
    }

    /** Parcelable implementation. */
    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeString(mValueName)
        parcel.writeLong(mLongValue)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LongVariable>
    {
        private val TAG: String = LongVariable::class.java.simpleName

        override fun createFromParcel(parcel: Parcel): LongVariable
        {
            return LongVariable(parcel)
        }

        override fun newArray(size: Int): Array<LongVariable?>
        {
            return arrayOfNulls(size)
        }
    }
}