package com.github.phzhou76.retask.model.value.rvalue

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.github.phzhou76.retask.model.value.ValueType

class BooleanValue(booleanValue: Boolean) : RValue(ValueType.BOOLEAN)
{
    var mBooleanValue: Boolean = booleanValue

    constructor(parcel: Parcel) : this(
            (parcel.readInt() == 1) /* mBooleanValue */
    )

    override fun printDebugLog()
    {
        Log.d(TAG, "Boolean Value: " + this)
    }

    override fun toString(): String
    {
        return if (mBooleanValue) "true" else "false"
    }


    /* Parcel implementation. */
    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeInt(if (mBooleanValue) 1 else 0)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BooleanValue>
    {
        private val TAG: String = BooleanValue::class.java.simpleName

        override fun createFromParcel(parcel: Parcel): BooleanValue
        {
            return BooleanValue(parcel)
        }

        override fun newArray(size: Int): Array<BooleanValue?>
        {
            return arrayOfNulls(size)
        }
    }
}