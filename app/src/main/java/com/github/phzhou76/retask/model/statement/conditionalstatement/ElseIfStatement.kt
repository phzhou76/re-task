package com.github.phzhou76.retask.model.statement.conditionalstatement

import android.util.Log
import com.github.phzhou76.retask.model.operation.equalityoperation.EqualityOperation
import com.github.phzhou76.retask.model.statement.StatementBlock

/**
 * A Statement that should only be linked to an IfStatement. If the condition is
 * true, the StatementBlock will be executed. If not, then the next ElseIfStatement
 * will be executed if available.
 *
 * @constructor Creates an ElseIfStatement with the input condition, StatementBlock,
 *      and an optional ElseIfStatement for the else block.
 */
open class ElseIfStatement(trueCondition: EqualityOperation?, trueBlock: StatementBlock, elseIfStatement: ElseIfStatement?)
    : IfStatement(trueCondition, trueBlock, elseIfStatement)
{
    override fun printDebugLog()
    {
        Log.d(TAG, this.toString())
        mTrueBlock.printDebugLog()
    }

    override fun toString(): String
    {
        return "Else if $mTrueCondition:"
    }

    companion object
    {
        private val TAG: String = ElseIfStatement::class.java.simpleName
    }
}