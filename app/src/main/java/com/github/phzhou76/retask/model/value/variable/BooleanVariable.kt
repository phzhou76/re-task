package com.github.phzhou76.retask.model.value.variable

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.github.phzhou76.retask.model.value.BooleanValue
import com.github.phzhou76.retask.model.value.Value
import com.github.phzhou76.retask.model.value.ValueType

class BooleanVariable(variableName: String, booleanValue: Boolean)
    : Variable(ValueType.BOOLEAN, variableName)
{
    /* Boolean value that the Variable holds. */
    var mBooleanValue: Boolean = booleanValue

    constructor(parcel: Parcel) : this(
            parcel.readString()
                    ?: throw NullPointerException("Parcel Error: BooleanVariable (mVariableName)"), /* mVariableName */
            (parcel.readInt() == 1)                                                                 /* mBooleanValue */
    )

    override fun evaluateValue(): Value
    {
        return BooleanValue(mBooleanValue)
    }

    override fun printDebugLog()
    {
        Log.d(TAG, this.toString())
    }

    override fun toString(): String
    {
        return "$mVariableName: " + (if (mBooleanValue) "true" else "false")
    }

    /** Parcelable implementation. */
    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeString(mVariableName)
        parcel.writeInt(if (mBooleanValue) 1 else 0)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BooleanVariable>
    {
        private val TAG: String = BooleanVariable::class.java.simpleName

        override fun createFromParcel(parcel: Parcel): BooleanVariable
        {
            return BooleanVariable(parcel)
        }

        override fun newArray(size: Int): Array<BooleanVariable?>
        {
            return arrayOfNulls(size)
        }
    }
}