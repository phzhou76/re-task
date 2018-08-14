package com.github.phzhou76.retask.model.statement.conditionalstatement

import com.github.phzhou76.retask.model.operation.equalityoperation.EqualityOperation
import com.github.phzhou76.retask.model.statement.Statement
import com.github.phzhou76.retask.model.statement.StatementBlock

abstract class ConditionalStatement(trueCondition: EqualityOperation?, trueBlock: StatementBlock)
    : Statement()
{
    /* The condition required for execution. */
    var mTrueCondition: EqualityOperation? = trueCondition

    /* If the condition is true, then this StatementBlock will be executed. */
    var mTrueBlock: StatementBlock = trueBlock
}