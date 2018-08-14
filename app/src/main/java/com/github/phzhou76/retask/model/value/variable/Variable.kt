package com.github.phzhou76.retask.model.value.variable

import com.github.phzhou76.retask.model.value.Value
import com.github.phzhou76.retask.model.value.ValueType
import com.github.phzhou76.retask.model.value.rvalue.RValue

abstract class Variable(valueType: ValueType, variableName: String, value: RValue) : Value(valueType)
{
    /* Name of the Variable. */
    var mVariableName: String = variableName

    /* Value of the Variable. */
    var mValue: RValue = value

    /**
     * Returns the Variable's Value to make operations between Variables and
     * RValues easier.
     *
     * @return The Variable's Value.
     */
    override fun getValue(): Value
    {
        return mValue
    }

    override fun toString(): String
    {
        return mVariableName
    }
}