package com.github.phzhou76.retask.model.operation.arithmeticoperation

import com.github.phzhou76.retask.model.value.Value
import com.github.phzhou76.retask.model.value.ValueType
import com.github.phzhou76.retask.model.value.FloatValue
import com.github.phzhou76.retask.model.value.IntValue

class AddOperation(leftValue: Value, rightValue: Value) : ArithmeticOperation(leftValue, rightValue)
{
    override fun evaluateIntOperation(): IntValue
    {
        var tempLeftValue: Value = mLeftValue
        when (tempLeftValue)
        {
            is IntValue -> tempLeftValue = tempLeftValue as IntValue
        }

        return IntValue((mLeftValue as IntValue).mIntValue + (mRightValue as IntValue).mIntValue)

    }

    override fun evaluateFloatOperation(): FloatValue
    {
        var tempLeftValue = mLeftValue
        if (tempLeftValue.mValueType == ValueType.INT)
        {

        }
    }
}