package com.github.phzhou76.retask.model.value.numericvalue

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.github.phzhou76.retask.model.value.ValueType

open class LongValue(longValue: Long) : NumericValue(ValueType.LONG)
{
    override var mValueName: String = "Long Value"

    var mLongValue: Long = longValue

    constructor(parcel: Parcel) : this(
            parcel.readLong()       /* mLongValue */
    )

    /** Evaluation methods. */
    override fun evaluateValueAsInt(): Int
    {
        return mLongValue.toInt()
    }

    override fun evaluateValueAsFloat(): Float
    {
        return mLongValue.toFloat()
    }

    override fun evaluateValueAsLong(): Long
    {
        return mLongValue
    }

    override fun printDebugLog()
    {
        Log.d(TAG, mValueName + ": " + this)
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