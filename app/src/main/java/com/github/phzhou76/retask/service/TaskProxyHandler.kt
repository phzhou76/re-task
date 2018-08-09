package com.github.phzhou76.retask.service

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.Parcelable
import android.util.Log
import java.util.*

class TaskProxyHandler(taskProxyService: TaskProxyService) : Handler()
{
    private val mTaskProxyService: TaskProxyService = taskProxyService

    /**
     * This method handles any incoming Messages from clients and forwards any
     * relevant data to the proxy service.
     *
     * @param message The Message to handle.
     */
    override fun handleMessage(message: Message?)
    {
        /* If the Message object is not a null value. */
        message?.let { validMessage ->

            /* Since the Message came from a different process, the Bundle that
             * holds the tasks in Parcel form must be extracted.
             */
            val messageBundle: Bundle? = validMessage.obj as? Bundle

            messageBundle?.let { validBundle ->
//
//                /* Always need to set the class loader of the bundle before taking any Parcelable contents out. */
//                validBundle.classLoader = Array<ITask>::class.java.classLoader
//                val parcelArray: Array<Parcelable> = validBundle.getParcelableArray("CLICK")
//                val taskArray: Array<ITask> = Arrays.copyOf(parcelArray, parcelArray.size, Array<ITask>::class.java)
//
//                mTaskProxyService.execute(taskArray)
            }
        }
    }
}