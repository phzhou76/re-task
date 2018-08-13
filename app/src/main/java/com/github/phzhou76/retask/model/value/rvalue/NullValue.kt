package com.github.phzhou76.retask.model.value.rvalue

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.github.phzhou76.retask.model.value.ValueType

class NullValue() : RValue(ValueType.NULL)
{
    constructor(@Suppress("UNUSED_PARAMETER") parcel: Parcel) : this()

    override fun printDebugLog()
    {
        Log.d(TAG, "Null value.")
    }

    override fun toString(): String
    {
        return "NULL"
    }


    /* Parcel implementation. */
    override fun writeToParcel(parcel: Parcel, flags: Int)
    {

    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NullValue>
    {
        private val TAG: String = NullValue::class.java.simpleName

        override fun createFromParcel(parcel: Parcel): NullValue
        {
            return NullValue(parcel)
        }

        override fun newArray(size: Int): Array<NullValue?>
        {
            return arrayOfNulls(size)
        }
    }
}