package com.github.phzhou76.retask.model.value.numericvalue

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.github.phzhou76.retask.model.value.ValueType

open class IntValue(intValue: Int) : NumericValue(ValueType.INT)
{
    override var mValueName: String = "Integer Value"

    var mIntValue: Int = intValue

    constructor(parcel: Parcel) : this(
            parcel.readInt()    /* mIntValue */
    )

    /** Evaluation methods. */
    override fun toIntValue(): IntValue
    {
        return this
    }

    override fun toFloatValue(): FloatValue
    {
        return FloatValue(mIntValue.toFloat())
    }

    override fun toLongValue(): LongValue
    {
        return LongValue(mIntValue.toLong())
    }

    override fun printDebugLog()
    {
        Log.d(TAG, "$mValueName: $mIntValue")
    }

    override fun toString(): String
    {
        return "$mIntValue"
    }

    /** Parcelable implementation. */
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