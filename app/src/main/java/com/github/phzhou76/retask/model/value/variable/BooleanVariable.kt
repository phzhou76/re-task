package com.github.phzhou76.retask.model.value.variable

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.github.phzhou76.retask.model.value.ValueType
import com.github.phzhou76.retask.model.value.rvalue.BooleanValue

/**
 * A Variable that holds a boolean Value.
 *
 * @constructor Creates a BooleanVariable object that holds a boolean Value object.
 */
class BooleanVariable(variableName: String, booleanValue: BooleanValue)
    : Variable(ValueType.BOOLEAN, variableName, booleanValue)
{
    constructor(parcel: Parcel) : this(
            parcel.readString()
                    ?: throw NullPointerException("Parcel Error: BooleanVariable (mVariableName)"), /* mVariableName */
            parcel.readParcelable(BooleanValue::class.java.classLoader)
                    ?: throw NullPointerException("Parcel Error: BooleanVariable (mValue)")         /* mValue */
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