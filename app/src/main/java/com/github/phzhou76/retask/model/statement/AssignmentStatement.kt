package com.github.phzhou76.retask.model.statement

import android.os.Parcel
import android.os.Parcelable
import com.github.phzhou76.retask.model.operation.EmptyOperation
import com.github.phzhou76.retask.model.operation.Operation
import com.github.phzhou76.retask.model.value.rvalue.IntValue
import com.github.phzhou76.retask.model.value.variable.IntVariable
import com.github.phzhou76.retask.model.value.variable.Variable

class AssignmentStatement(variable: Variable, assignment: Operation) : Statement()
{
    var mVariable: Variable = variable
    var mAssignment: Operation = assignment

    constructor(parcel: Parcel) : this(
            parcel.readParcelable<Variable>(Variable::class.java.classLoader)
                    ?: IntVariable("Parcel error", IntValue(-1)),                   /* mVariable */
            parcel.readParcelable<Operation>(Operation::class.java.classLoader)
                    ?: EmptyOperation(IntVariable("Parcel error", IntValue(-1)))    /* mAssignment */
    )

    override fun execute()
    {
        mVariable.mValue = mAssignment.evaluateOperation()
    }

    override fun toString(): String
    {
        return "Assign: $mVariable = $mAssignment"
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