package com.github.phzhou76.retask.model.value

import android.os.Parcel
import android.os.Parcelable
import android.util.Log

class BooleanVariable(variableName: String, booleanValue: Boolean) : BooleanValue(booleanValue)
{
    override var mValueName: String = variableName

    constructor(parcel: Parcel) : this(
            parcel.readString()
                    ?: throw NullPointerException("Parcel Error: BooleanVariable (mValueName)"),    /* mValueName */
            (parcel.readInt() == 1)                                                                 /* mBooleanValue */
    )

    override fun printDebugLog()
    {
        Log.d(TAG, this.toString())
    }

    override fun toString(): String
    {
        return "$mValueName: " + (if (mBooleanValue) "true" else "false")
    }

    /** Parcelable implementation. */
    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeString(mValueName)
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