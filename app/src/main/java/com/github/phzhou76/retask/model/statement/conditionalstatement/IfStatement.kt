package com.github.phzhou76.retask.model.statement.conditionalstatement

import com.github.phzhou76.retask.model.operation.equalityoperation.EqualityOperation
import com.github.phzhou76.retask.model.statement.StatementBlock

class IfStatement(trueCondition: EqualityOperation, trueBlock: StatementBlock, elseStatement: IfStatement?)
    : ConditionalStatement(trueCondition, trueBlock)
{
    var mElseStatement: IfStatement? = elseStatement


}