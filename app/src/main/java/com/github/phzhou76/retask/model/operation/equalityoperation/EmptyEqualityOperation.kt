package com.github.phzhou76.retask.model.operation.equalityoperation

import android.os.Parcel
import android.os.Parcelable
import com.github.phzhou76.retask.model.operation.Operation
import com.github.phzhou76.retask.model.value.Value
import com.github.phzhou76.retask.model.value.rvalue.BooleanValue
import com.github.phzhou76.retask.model.value.rvalue.NullValue
import com.github.phzhou76.retask.model.value.rvalue.RValue

class EmptyEqualityOperation(value: BooleanValue) : EqualityOperation(value, NullValue())
{
    constructor(parcel: Parcel) : this(
            parcel.readParcelable<BooleanValue>(BooleanValue::class.java.classLoader)
                    ?: BooleanValue(false)      /* mLeftValue */
    )

    override fun evaluateBooleanOperation(): BooleanValue
    {
        return mLeftValue as BooleanValue
    }

    override fun toString(): String
    {
        return mLeftValue.toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeParcelable(mLeftValue, flags)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EmptyEqualityOperation>
    {
        override fun createFromParcel(parcel: Parcel): EmptyEqualityOperation
        {
            return EmptyEqualityOperation(parcel)
        }

        override fun newArray(size: Int): Array<EmptyEqualityOperation?>
        {
            return arrayOfNulls(size)
        }
    }
}