package com.github.phzhou76.retask.model.operation

import android.os.Parcel
import android.os.Parcelable
import com.github.phzhou76.retask.model.value.Value
import com.github.phzhou76.retask.model.value.rvalue.NullValue
import com.github.phzhou76.retask.model.value.rvalue.RValue
import com.github.phzhou76.retask.model.value.variable.Variable

class EmptyOperation(value: Value) : Operation(value, NullValue())
{
    constructor(parcel: Parcel) : this(
            parcel.readParcelable<Value>(Value::class.java.classLoader)
                    ?: NullValue()
    )

    override fun evaluateOperation(): RValue
    {
        val tempValue = mLeftValue

        return when (tempValue)
        {
            is Variable -> tempValue.mValue
            is RValue   -> tempValue
            else        -> NullValue()
        }
    }

    override fun getLabel(): String
    {
        return mLeftValue.getLabel()
    }

    /* Parcelable implementation. */
    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeParcelable(mLeftValue, flags)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EmptyOperation>
    {
        override fun createFromParcel(parcel: Parcel): EmptyOperation
        {
            return EmptyOperation(parcel)
        }

        override fun newArray(size: Int): Array<EmptyOperation?>
        {
            return arrayOfNulls(size)
        }
    }

}