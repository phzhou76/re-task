package com.github.phzhou76.retask.model.operation.equalityoperation

import com.github.phzhou76.retask.model.operation.Operation
import com.github.phzhou76.retask.model.value.Value
import com.github.phzhou76.retask.model.value.rvalue.BooleanValue
import com.github.phzhou76.retask.model.value.rvalue.NullValue
import com.github.phzhou76.retask.model.value.rvalue.RValue

class EmptyEqualityOperation(value: Value) : Operation(value, NullValue())
{
    override fun evaluateOperation(): RValue
    {
        //
    }
}