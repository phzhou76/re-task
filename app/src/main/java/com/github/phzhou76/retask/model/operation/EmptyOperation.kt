package com.github.phzhou76.retask.model.operation

import android.os.Parcel
import android.os.Parcelable
import com.github.phzhou76.retask.model.value.Value
import com.github.phzhou76.retask.model.value.ValueType

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
     * Returns the type of the Value.
     *
     * @throws NullPointerException Thrown if mLeftValue is null.
     *
     * @return The type of the Value.
     */
    override fun evaluateValueType(): ValueType
    {
        return mLeftValue?.mValueType
                ?: throw NullPointerException("Null Input: $TAG")
    }

    /**
     * Returns the Variable's Value or RValue.
     *
     * @throws NullPointerException Thrown if mLeftValue is null.
     *
     * @return Variable's Value or RValue.
     */
    override fun evaluateOperation(): Value
    {
        return mLeftValue ?: throw NullPointerException("Null Input: $TAG")
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
        private val TAG: String = EmptyOperation::class.java.simpleName

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