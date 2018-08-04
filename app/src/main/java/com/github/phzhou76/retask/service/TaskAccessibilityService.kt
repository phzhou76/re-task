package com.github.phzhou76.retask.service

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.view.accessibility.AccessibilityEvent

class TaskAccessibilityService : AccessibilityService()
{
    companion object
    {
        private var SHARED_INSTANCE: TaskAccessibilityService? = null

        fun getSharedInstance(): TaskAccessibilityService?
        {
            return SHARED_INSTANCE
        }

        /**
         * Determines if the Accessibility Service is currently bound to the Android
         * system or not.
         *
         * @return True if the Accessibility Service is bound to the system, false
         *      otherwise.
         */
        fun isConnected(): Boolean
        {
            return SHARED_INSTANCE != null
        }
    }

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
     * This method is called when the system wants to interrupt the feedback the
     * Accessibility Service is providing. Typically, this method will be called
     * in response to a user action, like moving focus to a different control.
     */
    override fun onInterrupt()
    {
        TODO("not implemented")
    }

    /**
     * This method is called by the system when it detects an event that matches
     * the event(s) specified by the Accessibility Service. For example, if this
     * Accessibility Service specifies that button clicks are to be captured, any
     * button click events that occur will cause the system to call this method.
     *
     * @param accessibilityEvent The event that occurred.
     */
    override fun onAccessibilityEvent(accessibilityEvent: AccessibilityEvent?)
    {
        TODO("not implemented")
    }
}