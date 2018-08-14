package com.github.phzhou76.retask.model.value.variable

import com.github.phzhou76.retask.model.value.Value
import com.github.phzhou76.retask.model.value.ValueType

abstract class Variable(valueType: ValueType, variableName: String) : Value(valueType)
{
    /* Name of the Variable. */
    var mVariableName: String = variableName
}