package com.github.phzhou76.retask.model.value

import android.os.Parcel
import android.os.Parcelable
import android.util.Log

class LongValue(longValue: Long) : Value(ValueType.LONG)
{
    var mLongValue: Long = longValue

    constructor(parcel: Parcel) : this(
            parcel.readLong()       /* mLongValue */
    )

    override fun printDebugLog()
    {
        Log.d(TAG, "Long Value: " + this)
    }

    override fun toString(): String
    {
        return "$mLongValue"
    }

    /** Parcelable implementation. */
    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeLong(mLongValue)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LongValue>
    {
        private val TAG: String = LongValue::class.java.simpleName

        override fun createFromParcel(parcel: Parcel): LongValue
        {
            return LongValue(parcel)
        }

        override fun newArray(size: Int): Array<LongValue?>
        {
            return arrayOfNulls(size)
        }
    }
}