package student.testing.system.domain.usecases.auth

import student.testing.system.domain.states.operationStates.AuthState
import student.testing.system.domain.states.operationStates.OperationState
import student.testing.system.domain.models.PrivateUser
import student.testing.system.sharedPreferences.PrefsUtils
import javax.inject.Inject

class AuthIfPossibleUseCase @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val prefsUtils: PrefsUtils
) {

    suspend operator fun invoke(): AuthState<PrivateUser> {
        return if (isAuthDataSaved()) {
            authWithSavedData()
        } else {
            OperationState.NoState
        }
    }

    private fun isAuthDataSaved() = prefsUtils.getEmail().isNotEmpty()

    private suspend fun authWithSavedData(): AuthState<PrivateUser> {
        return loginUseCase(prefsUtils.getEmail(), prefsUtils.getPassword())
    }
}