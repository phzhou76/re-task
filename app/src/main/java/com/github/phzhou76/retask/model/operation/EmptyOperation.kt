package com.github.phzhou76.retask.model.operation

import android.os.Parcel
import android.os.Parcelable
import com.github.phzhou76.retask.model.value.Value
import com.github.phzhou76.retask.model.value.rvalue.RValue
import com.github.phzhou76.retask.model.value.variable.Variable

/**
 * Holds a Variable or Value with no operations applied to it.
 *
 * @constructor Creates an EmptyOperation that just holds a Variable or Value.
 */
class EmptyOperation(value: Value?) : Operation(value, null)
{
    constructor(parcel: Parcel) : this(
            parcel.readParcelable<Value>(Value::class.java.classLoader)
    )

    /**
     * Returns the Variable's Value or RValue.
     *
     * @throws IllegalArgumentException Thrown if mLeftValue is not a Variable or
     *      RValue.
     * @throws NullPointerException Thrown if mLeftValue is null.
     *
     * @return Variable's Value or RValue.
     */
    override fun evaluateOperation(): RValue
    {
        val valueCopy: Value? = mLeftValue

        valueCopy?.let {
            return when (it)
            {
                is Variable -> it.getValue()
                is RValue   -> it
                else        -> throw IllegalArgumentException("InvalidArgumentException: EmptyOperation")
            }
        }

        throw NullPointerException("NullPointerException: EmptyOperation")
    }

    /**
     * Operation is always valid if there is no operation.
     *
     * @return True
     */
    override fun determineValidOperation(): Boolean
    {
        return true
    }

    override fun toString(): String
    {
        return mLeftValue.toString()
    }

    /** Parcelable implementation. */
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