package com.github.phzhou76.retask.model.value.variable

import com.github.phzhou76.retask.model.value.Value
import com.github.phzhou76.retask.model.value.ValueType
import com.github.phzhou76.retask.model.value.rvalue.RValue

abstract class Variable(valueType: ValueType, variableName: String, value: RValue) : Value(valueType)
{
    var mVariableName: String = variableName
    var mValue: RValue = value

    override fun toString(): String
    {
        return mVariableName
    }
}