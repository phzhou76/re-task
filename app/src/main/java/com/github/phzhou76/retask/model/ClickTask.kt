package com.github.phzhou76.retask.model

import android.accessibilityservice.GestureDescription
import android.graphics.Path
import android.os.Parcel
import android.os.Parcelable
import com.github.phzhou76.retask.service.TaskAccessibilityService

/**
 * A task that causes the automation to deliver a touch gesture at a certain
 * point on the device.
 *
 * @constructor Creates a ClickTask with the given title and coordinate point.
 */
class ClickTask(taskTitle: String, xCoordinate: Float, yCoordinate: Float) : ITask
{
    override var mTaskTitle: String = taskTitle

    /* If either coordinate is modified, the gesture must be updated. */
    var mXCoordinate: Float = xCoordinate
        set(value)
        {
            field = value
            updateGesture()
        }
    var mYCoordinate: Float = yCoordinate
        set(value)
        {
            field = value
            updateGesture()
        }

    /* Describes the click for this task. */
    private lateinit var mGestureDescription: GestureDescription

    init
    {
        updateGesture()
    }

    /**
     * Secondary constructor for the ClickTask Parcel. The init block is guaranteed
     * to run after the title and click coordinates have been set, so the gesture
     * will be updated.
     */
    constructor(parcel: Parcel) : this(
            parcel.readString() /* mTaskTitle */,
            parcel.readFloat() /* mXCoordinate */,
            parcel.readFloat() /* mYCoordinate */
    )

    /**
     * Utilizes the Accessibility Service to deliver a touch gesture at the assigned
     * (x, y) coordinates.
     */
    override fun execute()
    {
        TaskAccessibilityService.getSharedInstance()?.dispatchGesture(mGestureDescription,
                null, null)
    }

    /**
     * Updates the gesture to reflect any changes in the click coordinates of this
     * task.
     */
    private fun updateGesture()
    {
        val gestureDescriptionBuilder = GestureDescription.Builder()

        /* Only one point on the path for a single click. */
        val inputPath = Path()
        inputPath.moveTo(mXCoordinate, mYCoordinate)

        /* Input tap should occur immediately. */
        val gestureStrokeDescription = GestureDescription.StrokeDescription(inputPath,
                0, 1)
        gestureDescriptionBuilder.addStroke(gestureStrokeDescription)

        mGestureDescription = gestureDescriptionBuilder.build()
    }


    /* Parcel methods. */
    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeString(mTaskTitle)
        parcel.writeFloat(mXCoordinate)
        parcel.writeFloat(mYCoordinate)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ClickTask>
    {
        override fun createFromParcel(parcel: Parcel): ClickTask
        {
            return ClickTask(parcel)
        }

        override fun newArray(size: Int): Array<ClickTask?>
        {
            return arrayOfNulls(size)
        }
    }

}