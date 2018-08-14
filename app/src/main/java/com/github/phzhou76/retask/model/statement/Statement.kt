package com.github.phzhou76.retask.model.statement

import android.os.Parcelable

/**
 * An abstract class that describes a single statement, such as an action or a
 * conditional.
 */
abstract class Statement : Parcelable
{
    /* If set to true, stop execution of Statement. Used to interrupt loops. */
    protected var mStopExecution: Boolean = false

    /**
     * Halts execution of the Statement and any child Statements.
     */
    open fun stopExecution()
    {
        mStopExecution = true
    }

    /**
     * Executes the Statement's contents.
     */
    abstract fun execute()

    /**
     * Prints a debug log of the Statement's contents.
     */
    abstract fun printDebugLog()
}