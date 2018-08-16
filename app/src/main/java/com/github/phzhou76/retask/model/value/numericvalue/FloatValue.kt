package com.github.phzhou76.retask.model.value.numericvalue

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.github.phzhou76.retask.model.value.ValueType

open class FloatValue(floatValue: Float) : NumericValue(ValueType.FLOAT)
{
    override var mValueName: String = "Float Value"

    var mFloatValue: Float = floatValue

    constructor(parcel: Parcel) : this(
            parcel.readFloat()  /* mFloatValue */
    )

    /** Evaluation methods. */
    override fun toIntValue(): IntValue
    {
        return IntValue(mFloatValue.toInt())
    }

    override fun toFloatValue(): FloatValue
    {
        return this
    }

    override fun toLongValue(): LongValue
    {
        return LongValue(mFloatValue.toLong())
    }

    override fun printDebugLog()
    {
        Log.d(TAG, "$mValueName: $mFloatValue")
    }

    override fun toString(): String
    {
        return "$mFloatValue"
    }

    /** Parcelable implementation. */
    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeFloat(mFloatValue)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FloatValue>
    {
        private val TAG: String = FloatValue::class.java.simpleName

        override fun createFromParcel(parcel: Parcel): FloatValue
        {
            return FloatValue(parcel)
        }

        override fun newArray(size: Int): Array<FloatValue?>
        {
            return arrayOfNulls(size)
        }
    }
}