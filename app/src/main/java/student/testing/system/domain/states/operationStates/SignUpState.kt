package student.testing.system.domain.states.operationStates

import androidx.annotation.StringRes
import stdio.lilith.annotations.FunctionalityState

@FunctionalityState
sealed interface SignUpState<out R> {
    data class NameError(@StringRes val messageResId: Int) : SignUpState<Nothing>
}