package com.github.phzhou76.retask.model.value

import android.os.Parcel
import android.os.Parcelable
import com.github.phzhou76.retask.model.value.numericvalue.FloatValue

class Coordinate(xCoordinate: FloatValue, yCoordinate: FloatValue) : Parcelable
{
    var mXCoordinate: FloatValue = xCoordinate
    var mYCoordinate: FloatValue = yCoordinate

    /**
     * Initializes Coordinate with default value (0, 0).
     */
    constructor() : this(FloatValue(0.0f), FloatValue(0.0f))

    constructor(parcel: Parcel) : this(
            parcel.readParcelable(FloatValue::class.java.classLoader)
                    ?: throw NullPointerException("Parcel Error: Coordinate (mXCoordinate)"),   /* mXCoordinate */
            parcel.readParcelable(FloatValue::class.java.classLoader)
                    ?: throw NullPointerException("Parcel Error: Coordinate (mYCoordinate)")    /* mYCoordinate */
    )

    override fun toString(): String
    {
        return "($mXCoordinate, $mYCoordinate)"
    }

    /** Parcelable implementation. */
    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeParcelable(mXCoordinate, flags)
        parcel.writeParcelable(mYCoordinate, flags)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Coordinate>
    {
        override fun createFromParcel(parcel: Parcel): Coordinate
        {
            return Coordinate(parcel)
        }

        override fun newArray(size: Int): Array<Coordinate?>
        {
            return arrayOfNulls(size)
        }
    }
}