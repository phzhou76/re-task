package com.github.phzhou76.retask.model.statement

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.github.phzhou76.retask.model.value.numericvalue.FloatValue
import java.util.*

/**
 * A Statement that clicks on a random coordinate point in a region.
 *
 * @constructor Creates a ClickRegionStatement that clicks at a random coordinate
 *      point in the given rectangular region, specified by the top left coordinate
 *      and bottom right coordinate of the region.
 */
class ClickRegionStatement(topLeftCoordinate: Pair<FloatValue, FloatValue>,
                           bottomRightCoordinate: Pair<FloatValue, FloatValue>)
    : ClickStatement(Pair(FloatValue(0.0f), FloatValue(0.0f)))
{
    /* Top left coordinate point of the clickable region. */
    var mTopLeftCoordinate: Pair<FloatValue, FloatValue> = topLeftCoordinate

    /* Bottom right coordinate point of the clickable region. */
    var mBottomRightCoordinate: Pair<FloatValue, FloatValue> = bottomRightCoordinate

    constructor(parcel: Parcel) : this(
            Pair(parcel.readParcelable(FloatValue::class.java.classLoader),
                    parcel.readParcelable(FloatValue::class.java.classLoader)),   /* mTopLeftCoordinate */
            Pair(parcel.readParcelable(FloatValue::class.java.classLoader),
                    parcel.readParcelable(FloatValue::class.java.classLoader))    /* mBottomRightCoordinate */
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
    private fun selectRandomCoordinate(): Pair<Float, Float>
    {
        val random = Random()

        /* Select a random point on the x-axis of the region. */
        val randomXCoordinate = mTopLeftCoordinate.first +
                ((mBottomRightCoordinate.first - mTopLeftCoordinate.first) * random.nextFloat())

        /* Select a random point on the y-axis of the region. */
        val randomYCoordinate = mTopLeftCoordinate.second +
                ((mBottomRightCoordinate.second - mTopLeftCoordinate.second) * random.nextFloat())

        return Pair(randomXCoordinate, randomYCoordinate)
    }

    override fun printDebugLog()
    {
        Log.d(TAG, this.toString())
    }

    override fun toString(): String
    {
        return "Clicking in region: " + mTopLeftCoordinate.toString() +
                " to " + mBottomRightCoordinate.toString()
    }

    /** Parcelable implementation. */
    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeParcelable(mTopLeftCoordinate.first, flags)
        parcel.writeParcelable(mTopLeftCoordinate.second, flags)
        parcel.writeParcelable(mBottomRightCoordinate.first, flags)
        parcel.writeParcelable(mBottomRightCoordinate.second, flags)
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