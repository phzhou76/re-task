package com.github.phzhou76.retask.model.statement.conditionalstatement

import android.util.Log
import com.github.phzhou76.retask.model.operation.equalityoperation.EmptyEqualityOperation
import com.github.phzhou76.retask.model.statement.StatementBlock
import com.github.phzhou76.retask.model.value.rvalue.BooleanValue

/**
 * A Statement that should only be linked to an IfStatement, since there is no
 * condition required for the StatementBlock to be executed.
 *
 * @constructor Creates an ElseStatement with the input StatementBlock.
 */
class ElseStatement(statementBlock: StatementBlock)
    : IfStatement(EmptyEqualityOperation(BooleanValue((true))), statementBlock, null)
{
    companion object
    {
        private val TAG: String = ElseStatement::class.java.simpleName
    }

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
}