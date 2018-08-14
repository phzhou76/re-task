package com.github.phzhou76.retask.model.statement

import android.os.Parcelable

abstract class Statement : Parcelable
{
    /* If set to true, stop execution of Statement. Used to interrupt loops. */
    protected var mStopExecution: Boolean = false

    /**
     * Should be overridden to halt the execution of the Statement and any child
     * Statements.
     */
    open fun stopExecution()
    {
        mStopExecution = true
    }

    abstract fun execute()

    abstract fun printDebugLog()
}