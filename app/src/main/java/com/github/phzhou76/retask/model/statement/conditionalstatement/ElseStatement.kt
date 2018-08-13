package com.github.phzhou76.retask.model.statement.conditionalstatement

import com.github.phzhou76.retask.model.operation.equalityoperation.EqualityOperation
import com.github.phzhou76.retask.model.statement.StatementBlock

class ElseStatement(trueCondition: EqualityOperation, trueBlock: StatementBlock, elseStatement: ElseStatement?)
    : ConditionalStatement(trueCondition, trueBlock)
{
    var mElseStatement: ElseStatement? = elseStatement


}