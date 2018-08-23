package com.github.phzhou76.retask.model.value

import android.os.Parcel
import android.os.Parcelable
import android.util.Log

open class BooleanValue(booleanValue: Boolean) : Value(ValueType.BOOLEAN)
{
    override var mValueName: String = "Boolean Value"

    open var mBooleanValue: Boolean = booleanValue

    constructor() : this(true)

    constructor(parcel: Parcel) : this(
            (parcel.readInt() == 1) /* mBooleanValue */
    )

    override fun printDebugLog()
    {
        Log.d(TAG, "$mValueName: " + (if (mBooleanValue) "true" else "false"))
    }

    override fun toString(): String
    {
        return if (mBooleanValue) "true" else "false"
    }

    /** Parcelable implementation. */
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