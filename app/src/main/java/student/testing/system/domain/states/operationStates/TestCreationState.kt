package student.testing.system.domain.states.operationStates

import stdio.lilith.annotations.OperationState
import student.testing.system.domain.models.Test

@OperationState
sealed interface TestCreationState<out R> {
    data class Created(val test: Test) : TestCreationState<Nothing>
    data object EmptyName : TestCreationState<Nothing>
    data object NoQuestions : TestCreationState<Nothing>
}