package com.github.phzhou76.retask.model.value.variable

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.github.phzhou76.retask.model.value.IntValue
import com.github.phzhou76.retask.model.value.Value
import com.github.phzhou76.retask.model.value.ValueType

class IntVariable(variableName: String, intValue: Int)
    : Variable(ValueType.INT, variableName)
{
    /* Int value that the Variable holds. */
    var mIntValue: Int = intValue

    constructor(parcel: Parcel) : this(
            parcel.readString()
                    ?: throw NullPointerException("Parcel Error: IntVariable (mVariableName)"), /* mVariableName */
            parcel.readInt()                                                                    /* mLongValue */
    )

    override fun evaluateValue(): Value
    {
        return IntValue(mIntValue)
    }

    override fun printDebugLog()
    {
        Log.d(TAG, this.toString())
    }

    override fun toString(): String
    {
        return "$mVariableName: $mIntValue"
    }

    /** Parcelable implementation. */
    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeString(mVariableName)
        parcel.writeInt(mIntValue)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<IntVariable>
    {
        private val TAG: String = IntVariable::class.java.simpleName

        override fun createFromParcel(parcel: Parcel): IntVariable
        {
            return IntVariable(parcel)
        }

        override fun newArray(size: Int): Array<IntVariable?>
        {
            return arrayOfNulls(size)
        }
    }
}