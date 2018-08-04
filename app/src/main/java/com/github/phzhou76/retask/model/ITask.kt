package com.github.phzhou76.retask.model

import android.os.Parcelable

interface ITask: Parcelable
{
    var mTaskTitle: String

    /**
     * This method should be overridden to contain any executable tasks, such as
     * waiting or dispatching gestures.
     */
    fun execute()
}