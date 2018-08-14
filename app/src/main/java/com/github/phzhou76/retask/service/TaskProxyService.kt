package com.github.phzhou76.retask.service

import android.app.IntentService
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import android.os.Messenger
import android.util.Log
import com.github.phzhou76.retask.MainActivity
import com.github.phzhou76.retask.handler.TaskProxyHandler
import com.github.phzhou76.retask.model.TaskScript

class TaskProxyService : IntentService("TaskProxyService")
{
    /* Messenger that the client Activity sends Messages to. */
    private val mMessenger: Messenger = Messenger(TaskProxyHandler(this))

    /* Reference to the TaskScript that this proxy service is running. */
    private var mTaskScript: TaskScript? = null

    /**
     * Handles the intent that was sent to this Intent Service, and executes the
     * script's contents.
     *
     * @param intent Holds the script to be executed.
     */
    override fun onHandleIntent(intent: Intent?)
    {
        TaskAccessibilityService.getSharedInstance()?.bindTaskProxyService(this)

        Log.d(TAG, "onHandleIntent")

        intent?.let { validIntent ->
            val bundle: Bundle = validIntent.getBundleExtra(MainActivity.KEY_BUNDLE)
            bundle.classLoader = TaskScript::class.java.classLoader

            val taskScript: TaskScript? = bundle.getParcelable(MainActivity.KEY_TASK_SCRIPT)
            taskScript?.let {
                mTaskScript = it

                it.printDebugLog()
                it.execute()
            }
        }

        Log.d(TAG, "Finished script.")

        TaskAccessibilityService.getSharedInstance()?.unbindTaskProxyService()
    }

    /**
     * Called when a client binds to this service.
     *
     * @param intent The Intent that the client sent to this service.
     *
     * @return An IBinder-implementing object that the client can use to communicate
     *      with the service.
     */
    override fun onBind(intent: Intent?): IBinder
    {
        return mMessenger.binder
    }

    /**
     * Stops the execution of the script.
     */
    fun stopExecution()
    {
        mTaskScript?.stopExecution()
    }

    /**
     * Checks if the Accessibility Service is connected to the Android system.
     *
     * @return True if the Accessibility Service is connected, false otherwise.
     */
    fun isAccessibilityConnected(): Boolean
    {
        return TaskAccessibilityService.getSharedInstance() != null
    }

    companion object
    {
        private val TAG: String = TaskProxyService::class.java.simpleName
    }
}