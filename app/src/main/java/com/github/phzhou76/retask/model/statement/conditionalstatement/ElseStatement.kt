package com.github.phzhou76.retask.model.statement.conditionalstatement

import android.util.Log
import com.github.phzhou76.retask.model.statement.StatementBlock

/**
 * A Statement that should only be linked to an IfStatement. If all other
 * IfStatements and ElseIfStatements have been executed, this ElseStatement can
 * be executed.
 *
 * @constructor Creates an ElseStatement with the input StatementBlock.
 */
class ElseStatement(statementBlock: StatementBlock)
    : ElseIfStatement(null, statementBlock, null)
{
    override fun execute()
    {
        mTrueBlock.execute()
    }

    override fun printDebugLog()
    {
        Log.d(TAG, this.toString())
        mTrueBlock.printDebugLog()
    }

    override fun toString(): String
    {
        return "Else:"
    }

    companion object
    {
        private val TAG: String = ElseStatement::class.java.simpleName
    }
}