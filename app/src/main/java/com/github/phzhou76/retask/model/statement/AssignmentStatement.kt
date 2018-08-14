package com.github.phzhou76.retask.model.statement

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.github.phzhou76.retask.model.operation.Operation
import com.github.phzhou76.retask.model.value.ValueType
import com.github.phzhou76.retask.model.value.rvalue.BooleanValue
import com.github.phzhou76.retask.model.value.rvalue.FloatValue
import com.github.phzhou76.retask.model.value.rvalue.IntValue
import com.github.phzhou76.retask.model.value.rvalue.RValue
import com.github.phzhou76.retask.model.value.variable.Variable

/**
 * A Statement that assigns the result of an Operation to a Variable.
 *
 * @constructor Creates an AssignmentStatement object that evaluates the result
 *      of the Operation and assigns the value to the Variable.
 */
class AssignmentStatement(variable: Variable, assignment: Operation) : Statement()
{
    /* The Variable to assign a new value to. */
    var mVariable: Variable = variable

    /* The Operation whose value will be assigned to the Variable. */
    var mAssignment: Operation = assignment

    constructor(parcel: Parcel) : this(
            parcel.readParcelable<Variable>(Variable::class.java.classLoader)
                    ?: throw NullPointerException("Parcel Error: AssignmentStatement (mVariable)"),     /* mVariable */
            parcel.readParcelable<Operation>(Operation::class.java.classLoader)
                    ?: throw NullPointerException("Parcel Error: AssignmentStatement (mAssignment)")    /* mAssignment */
    )

    /**
     * Evaluates the operation and assigns the result to the Variable if the two
     * types are compatible.
     */
    override fun execute()
    {
        val assignmentResult: RValue = mAssignment.evaluateOperation()

        val variableType = mVariable.mValueType
        val assignmentType = assignmentResult.mValueType

        if (!determineValidAssignment(variableType, assignmentType))
        {
            throw IllegalArgumentException("AssignmentStatement: Invalid inputs.")
        }

        assignVariable(assignmentResult)
    }

    override fun printDebugLog()
    {
        Log.d(TAG, this.toString())
    }

    override fun toString(): String
    {
        return "Assign: $mVariable = $mAssignment"
    }

    /**
     * Determines if the two input types can be assigned to one another. In general,
     * booleans can only be assigned to boolean variables, and ints and floats can
     * be assigned to each other through casting.
     *
     * @param variableType The type of the Variable.
     * @param assignmentType The type of the value being assigned to the Variable.
     *
     * @return True if the two types are compatible, false otherwise.
     */
    private fun determineValidAssignment(variableType: ValueType, assignmentType: ValueType)
            : Boolean
    {
        return if (variableType == ValueType.BOOLEAN && assignmentType != ValueType.BOOLEAN)
        {
            false
        }
        else !(variableType != ValueType.BOOLEAN && assignmentType == ValueType.BOOLEAN)
    }

    /**
     * Assigns the assignment's value to the Variable, and performs any casting
     * if necessary, such as ints to float variables. Type checking with
     * determineValidAssignment must be done prior to calling this method, since
     * it assumes that the Variable and the assignment values' types are compatible.
     *
     * @param assignmentValue The value that will be assigned to the Variable.
     */
    private fun assignVariable(assignmentValue: RValue)
    {
        when (assignmentValue)
        {
            is BooleanValue -> mVariable.mValue = assignmentValue
            is IntValue     ->
            {
                var tempValue = assignmentValue

                /* Cast the assignment value to a float type. */
                if (mVariable.mValueType == ValueType.FLOAT)
                {
                    tempValue = FloatValue(tempValue.mIntValue.toFloat())
                }
                mVariable.mValue = tempValue
            }
            is FloatValue   ->
            {
                var tempValue = assignmentValue

                /* Cast the assignment value to an int type. */
                if (mVariable.mValueType == ValueType.INT)
                {
                    tempValue = IntValue(tempValue.mFloatValue.toInt())
                }
                mVariable.mValue = tempValue
            }
        }
    }


    /* Parcelable implementation. */
    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeParcelable(mVariable, flags)
        parcel.writeParcelable(mAssignment, flags)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AssignmentStatement>
    {
        private val TAG: String = AssignmentStatement::class.java.simpleName

        override fun createFromParcel(parcel: Parcel): AssignmentStatement
        {
            return AssignmentStatement(parcel)
        }

        override fun newArray(size: Int): Array<AssignmentStatement?>
        {
            return arrayOfNulls(size)
        }
    }
}