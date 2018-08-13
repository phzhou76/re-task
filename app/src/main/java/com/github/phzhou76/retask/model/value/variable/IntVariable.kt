package com.github.phzhou76.retask.model.value.variable

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.github.phzhou76.retask.model.value.ValueType
import com.github.phzhou76.retask.model.value.rvalue.IntValue

class IntVariable(variableName: String, intValue: IntValue)
    : Variable(ValueType.INT, variableName, intValue)
{
    constructor(parcel: Parcel) : this(
            parcel.readString()
                    ?: "Parcel Error: Variable",    /* mVariableName */
            parcel.readParcelable(IntValue::class.java.classLoader)
                    ?: IntValue(-1)         /* mValue */
    )

    override fun printDebugLog()
    {
        Log.d(TAG, this.toString())
        mValue.printDebugLog()
    }


    /* Parcel implementation. */
    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeString(mVariableName)
        parcel.writeParcelable(mValue, flags)
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