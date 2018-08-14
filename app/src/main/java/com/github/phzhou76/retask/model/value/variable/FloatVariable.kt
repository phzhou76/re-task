package com.github.phzhou76.retask.model.value.variable

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.github.phzhou76.retask.model.value.ValueType
import com.github.phzhou76.retask.model.value.rvalue.FloatValue

/**
 * A Variable that holds a float value.
 *
 * @constructor Creates a FloatVariable object that holds a float Value object.
 */
class FloatVariable(variableName: String, floatValue: FloatValue)
    : Variable(ValueType.FLOAT, variableName, floatValue)
{
    constructor(parcel: Parcel) : this(
            parcel.readString()
                    ?: throw NullPointerException("Parcel Error: FloatVariable (mVariableName)"),   /* mVariableName */
            parcel.readParcelable(FloatValue::class.java.classLoader)
                    ?: throw NullPointerException("Parcel Error: FloatVariable (mValue)")           /* mValue */
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

    companion object CREATOR : Parcelable.Creator<FloatVariable>
    {
        private val TAG: String = FloatVariable::class.java.simpleName

        override fun createFromParcel(parcel: Parcel): FloatVariable
        {
            return FloatVariable(parcel)
        }

        override fun newArray(size: Int): Array<FloatVariable?>
        {
            return arrayOfNulls(size)
        }
    }
}