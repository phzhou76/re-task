package com.github.phzhou76.retask.model.statement

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.github.phzhou76.retask.model.value.Coordinate
import com.github.phzhou76.retask.model.value.numericvalue.FloatValue
import java.util.*

/**
 * A Statement that clicks on a random coordinate point in a region.
 *
 * @constructor Creates a ClickRegionStatement that clicks at a random coordinate
 *      point in the given rectangular region, specified by the top left coordinate
 *      and bottom right coordinate of the region.
 */
class ClickRegionStatement(topLeftCoordinate: Coordinate,
                           bottomRightCoordinate: Coordinate) : ClickStatement(Coordinate())
{
    /* Top left coordinate point of the clickable region. */
    var mTopLeftCoordinate: Coordinate = topLeftCoordinate

    /* Bottom right coordinate point of the clickable region. */
    var mBottomRightCoordinate: Coordinate = bottomRightCoordinate

    constructor(parcel: Parcel) : this(
            parcel.readParcelable(Coordinate::class.java.classLoader)
                    ?: throw NullPointerException("Parcel Error: $TAG (mTopLeftCoordinate)"),       /* mTopLeftCoordinate */
            parcel.readParcelable(Coordinate::class.java.classLoader)
                    ?: throw NullPointerException("Parcel Error: $TAG (mBottomRightCoordinate)")    /* mBottomRightCoordinate */
    )

    /**
     * Picks a random coordinate point in the region and clicks on it.
     */
    override fun execute()
    {
        mClickCoordinate = selectRandomCoordinate()
        super.execute()
    }

    /**
     * Given the top left coordinate point and the bottom right coordinate point
     * of a rectangular region, selects a random coordinate point in the region.
     *
     * @return A random coordinate point in the region.
     */
    private fun selectRandomCoordinate(): Coordinate
    {
        val random = Random()

        /* Select a random point on the x-axis of the region. */
        val randomXCoordinate: Float = mTopLeftCoordinate.mXCoordinate.mFloatValue +
                ((mBottomRightCoordinate.mXCoordinate.mFloatValue
                        - mTopLeftCoordinate.mXCoordinate.mFloatValue)
                        * random.nextFloat())

        /* Select a random point on the y-axis of the region. */
        val randomYCoordinate: Float = mTopLeftCoordinate.mYCoordinate.mFloatValue +
                ((mBottomRightCoordinate.mYCoordinate.mFloatValue
                        - mTopLeftCoordinate.mYCoordinate.mFloatValue)
                        * random.nextFloat())

        return Coordinate(FloatValue(randomXCoordinate), FloatValue(randomYCoordinate))
    }

    override fun printDebugLog()
    {
        Log.d(TAG, this.toString())
    }

    override fun toString(): String
    {
        return "Clicking in region: $mTopLeftCoordinate to $mBottomRightCoordinate"
    }

    /** Parcelable implementation. */
    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeParcelable(mTopLeftCoordinate, flags)
        parcel.writeParcelable(mBottomRightCoordinate, flags)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ClickRegionStatement>
    {
        private val TAG: String = ClickRegionStatement::class.java.simpleName

        override fun createFromParcel(parcel: Parcel): ClickRegionStatement
        {
            return ClickRegionStatement(parcel)
        }

        override fun newArray(size: Int): Array<ClickRegionStatement?>
        {
            return arrayOfNulls(size)
        }
    }
}