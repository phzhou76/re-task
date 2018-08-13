package com.github.phzhou76.retask.model.statement.conditionalstatement

import com.github.phzhou76.retask.model.operation.equalityoperation.EqualityOperation
import com.github.phzhou76.retask.model.statement.Statement
import com.github.phzhou76.retask.model.statement.StatementBlock

abstract class ConditionalStatement(trueCondition: EqualityOperation, trueBlock: StatementBlock)
    : Statement()
{
    var mTrueCondition: EqualityOperation = trueCondition
    var mTrueBlock: StatementBlock = trueBlock
}