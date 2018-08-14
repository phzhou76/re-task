package com.github.phzhou76.retask.service

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.util.Log
import android.view.KeyEvent
import android.view.accessibility.AccessibilityEvent

class TaskAccessibilityService : AccessibilityService()
{
    /* Reference to proxy service to disable script manually. */
    private var mTaskProxyService: TaskProxyService? = null

    /**
     * Called when the Android system binds to the Accessibility Service; enables
     * the Accessibility Service to be accessed from the static method.
     */
    override fun onServiceConnected()
    {
        super.onServiceConnected()
        SHARED_INSTANCE = this
    }

    /**
     * Called when Android unbinds from the Accessibility Service.
     *
     * @param intent The Intent object that was used to bind to this service.
     *
     * @return True if onRebind(Intent) should be called when new clients bind to
     *      the Service.
     */
    override fun onUnbind(intent: Intent?): Boolean
    {
        SHARED_INSTANCE = null
        return super.onUnbind(intent)
    }

    /**
     * Called when the system wants to interrupt the feedback the Accessibility
     * Service is providing. Typically, will be called in response to a user action,
     * like moving focus to a different control.
     */
    override fun onInterrupt()
    {
        TODO("not implemented")
    }

    /**
     * Called by the system when it detects an event that matches the event(s)
     * specified by the Accessibility Service configuration.
     *
     * @param accessibilityEvent The event that occurred.
     */
    override fun onAccessibilityEvent(accessibilityEvent: AccessibilityEvent?)
    {
        TODO("not implemented")
    }

    /**
     * Called by the system when a device hardware button has been pressed.
     *
     * @param event The event that occurred.
     */
    override fun onKeyEvent(event: KeyEvent?): Boolean
    {
        Log.d(TAG, "onKeyEvent")
        mTaskProxyService?.stopExecution()

        return super.onKeyEvent(event)
    }

    /**
     * Binds a proxy service to this Accessibility Service so that it can
     * communicate with the proxy service.
     *
     * @param taskProxyService The proxy service to bind with.
     */
    fun bindTaskProxyService(taskProxyService: TaskProxyService)
    {
        mTaskProxyService = taskProxyService
    }

    /**
     * Unbinds the proxy service from the Accessibility Service.
     */
    fun unbindTaskProxyService()
    {
        mTaskProxyService = null
    }

    companion object
    {
        private val TAG: String = TaskAccessibilityService::class.java.simpleName

        private var SHARED_INSTANCE: TaskAccessibilityService? = null

        /**
         * Singleton pattern. Returns a Singleton instance of the Accessibility
         * Service.
         *
         * @return The instance of the Accessibility Service.
         */
        fun getSharedInstance(): TaskAccessibilityService?
        {
            return SHARED_INSTANCE
        }
    }

}