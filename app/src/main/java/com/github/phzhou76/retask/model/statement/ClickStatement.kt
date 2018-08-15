package com.github.phzhou76.retask.model.statement

import android.accessibilityservice.GestureDescription
import android.graphics.Path
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.github.phzhou76.retask.model.value.numericvalue.FloatValue
import com.github.phzhou76.retask.service.TaskAccessibilityService

/**
 * A Statement that clicks at the given coordinate point.
 *
 * @constructor Creates a ClickStatement that clicks at the given coordinate point.
 */
open class ClickStatement(clickCoordinate: Pair<FloatValue, FloatValue>) : Statement()
{
    /* Coordinate point to click at. */
    open var mClickCoordinate: Pair<FloatValue, FloatValue> = clickCoordinate
        set(value)
        {
            field = value
            mCoordinateChanged = true
        }

    /* Marks whether or not the gesture description needs to be regenerated. */
    private var mCoordinateChanged: Boolean = true

    /* Describes the click gesture. */
    private lateinit var mGestureDescription: GestureDescription

    constructor(parcel: Parcel) : this(
            Pair(parcel.readParcelable<FloatValue>(FloatValue::class.java.classLoader),
                    parcel.readParcelable<FloatValue>(FloatValue::class.java.classLoader))    /* mClickCoordinate */
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
        inputPath.moveTo(mClickCoordinate.first.mFloatValue,
                mClickCoordinate.second.mFloatValue)

        /* Click should occur immediately. */
        val gestureStrokeDescription = GestureDescription.StrokeDescription(inputPath,
                0, 1)
        gestureDescriptionBuilder.addStroke(gestureStrokeDescription)

        mGestureDescription = gestureDescriptionBuilder.build()

        mCoordinateChanged = false
    }

    /** Parcelable implementation. */
    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeParcelable(mClickCoordinate.first, flags)
        parcel.writeParcelable(mClickCoordinate.second, flags)
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