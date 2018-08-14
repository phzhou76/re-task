package com.github.phzhou76.retask.model.value.rvalue

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.github.phzhou76.retask.model.value.ValueType

/**
 * An integer Value object.
 *
 * @constructor Creates an integer Value object.
 */
class IntValue(intValue: Int) : RValue(ValueType.INT)
{
    var mIntValue: Int = intValue

    constructor(parcel: Parcel) : this(
            parcel.readInt()    /* mIntValue */
    )

    override fun printDebugLog()
    {
        Log.d(TAG, "Integer Value: " + this)
    }

    override fun toString(): String
    {
        return "$mIntValue"
    }


    /* Parcel implementation. */
    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeInt(mIntValue)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<IntValue>
    {
        private val TAG: String = IntValue::class.java.simpleName

        override fun createFromParcel(parcel: Parcel): IntValue
        {
            return IntValue(parcel)
        }

        override fun newArray(size: Int): Array<IntValue?>
        {
            return arrayOfNulls(size)
        }
    }
}