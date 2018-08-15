package com.github.phzhou76.retask.model.value.variable

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.github.phzhou76.retask.model.value.LongValue
import com.github.phzhou76.retask.model.value.Value
import com.github.phzhou76.retask.model.value.ValueType

class LongVariable(variableName: String, longValue: Long)
    : Variable(ValueType.LONG, variableName)
{
    /* Long value that the Variable holds. */
    var mLongValue: Long = longValue

    constructor(parcel: Parcel) : this(
            parcel.readString()
                    ?: throw NullPointerException("Parcel Error: LongVariable (mVariableName)"), /* mVariableName */
            parcel.readLong()                                                                    /* mLongValue */
    )

    override fun evaluateValue(): Value
    {
        return LongValue(mLongValue)
    }

    override fun printDebugLog()
    {
        Log.d(TAG, this.toString())
    }

    override fun toString(): String
    {
        return "$mVariableName: $mLongValue"
    }

    /** Parcelable implementation. */
    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeString(mVariableName)
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