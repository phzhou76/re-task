package com.github.phzhou76.retask.model.statement

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.github.phzhou76.retask.model.operation.Operation
import com.github.phzhou76.retask.model.value.BooleanValue
import com.github.phzhou76.retask.model.value.BooleanVariable
import com.github.phzhou76.retask.model.value.Value
import com.github.phzhou76.retask.model.value.ValueType
import com.github.phzhou76.retask.model.value.numericvalue.*

/**
 * A Statement that assigns the result of an Operation to a Variable.
 *
 * @constructor Creates an AssignmentStatement object that evaluates the result
 *      of the Operation and assigns the value to the Variable.
 */
class AssignmentStatement(variable: Value, assignment: Operation) : Statement()
{
    /* Variable to assign a new value to. */
    var mVariable: Value = variable

    /* Operation whose result will be assigned to the Variable. */
    var mAssignment: Operation = assignment

    constructor(parcel: Parcel) : this(
            parcel.readParcelable(Value::class.java.classLoader)
                    ?: throw NullPointerException("Parcel Error: $TAG (mVariable)"),     /* mVariable */
            parcel.readParcelable<Operation>(Operation::class.java.classLoader)
                    ?: throw NullPointerException("Parcel Error: $TAG (mAssignment)")    /* mAssignment */
    )

    /**
     * Evaluates the operation and assigns the result to the Variable if the two
     * types are compatible.
     */
    override fun execute()
    {
        assignVariable()
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
        return !((variableType == ValueType.BOOLEAN && assignmentType != ValueType.BOOLEAN) ||
                (variableType != ValueType.BOOLEAN && assignmentType == ValueType.BOOLEAN))
    }

    /**
     * Assigns the assignment's value to the Variable, and performs any casting
     * if necessary, such as int to float variables.
     *
     * @throws IllegalArgumentException Thrown if the two types are incompatible.
     */
    private fun assignVariable()
    {
        if (!determineValidAssignment(mVariable.mValueType, mAssignment.evaluateValueType()))
        {
            throw IllegalArgumentException("Invalid Inputs: $TAG")
        }

        val assignmentResult: Value = mAssignment.evaluateOperation()
        val variableCopy: Value = mVariable

        when (variableCopy)
        {
            is BooleanVariable ->
            {
                variableCopy.mBooleanValue = (assignmentResult as BooleanValue).mBooleanValue
            }
            is FloatVariable   ->
            {
                val numericResult: NumericValue = (assignmentResult as NumericValue)
                variableCopy.mFloatValue = numericResult.toFloatValue().mFloatValue
            }
            is LongVariable    ->
            {
                val numericResult: NumericValue = (assignmentResult as NumericValue)
                variableCopy.mLongValue = numericResult.toLongValue().mLongValue
            }
            is IntVariable     ->
            {
                val numericResult: NumericValue = (assignmentResult as NumericValue)
                variableCopy.mIntValue = numericResult.toIntValue().mIntValue
            }
        }
    }

    /** Parcelable implementation. */
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