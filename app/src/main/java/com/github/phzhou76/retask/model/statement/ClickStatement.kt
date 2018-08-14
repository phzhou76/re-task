package com.github.phzhou76.retask.model.statement

import android.accessibilityservice.GestureDescription
import android.graphics.Path
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.github.phzhou76.retask.service.TaskAccessibilityService

/**
 * A Statement that clicks at the given coordinate point.
 *
 * @constructor Creates a ClickStatement that clicks at the given coordinate point
 *      through the use of an Accessibility Service.
 */
open class ClickStatement(clickCoordinate: Pair<Float, Float>) : Statement()
{
    /* Coordinate point to click at. */
    open var mClickCoordinate: Pair<Float, Float> = clickCoordinate
        set(value)
        {
            field = value
            mCoordinateChanged = true
        }

    /* Marks whether or not the gesture description needs to be generated before
     * the click is executed.
     */
    private var mCoordinateChanged: Boolean = true

    /* Describes the click gesture. */
    private lateinit var mGestureDescription: GestureDescription

    constructor(parcel: Parcel) : this(
            Pair(parcel.readFloat(), parcel.readFloat())    /* mClickCoordinate */
    )

    /**
     * Utilizes Accessibility Services to deliver a click at the assigned (x, y)
     * coordinate.
     */
    override fun execute()
    {
        if (mCoordinateChanged)
        {
            updateGesture()
        }

        TaskAccessibilityService.getSharedInstance()?.dispatchGesture(mGestureDescription,
                null, null)
    }

    override fun printDebugLog()
    {
        Log.d(TAG, this.toString())
    }

    override fun toString(): String
    {
        return "Click at: " + mClickCoordinate.toString()
    }

    /**
     * Updates the gesture description to reflect any changes in the click
     * coordinate.
     */
    private fun updateGesture()
    {
        val gestureDescriptionBuilder = GestureDescription.Builder()

        /* Only one point on the path for a single click. */
        val inputPath = Path()
        inputPath.moveTo(mClickCoordinate.first, mClickCoordinate.second)

        /* Click should occur immediately. */
        val gestureStrokeDescription = GestureDescription.StrokeDescription(inputPath,
                0, 1)
        gestureDescriptionBuilder.addStroke(gestureStrokeDescription)

        mGestureDescription = gestureDescriptionBuilder.build()

        mCoordinateChanged = false
    }


    /* Parcelable implementation. */
    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeFloat(mClickCoordinate.first)
        parcel.writeFloat(mClickCoordinate.second)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ClickStatement>
    {
        private val TAG: String = ClickStatement::class.java.simpleName

        override fun createFromParcel(parcel: Parcel): ClickStatement
        {
            return ClickStatement(parcel)
        }

        override fun newArray(size: Int): Array<ClickStatement?>
        {
            return arrayOfNulls(size)
        }
    }
}