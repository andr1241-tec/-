package student.testing.system.domain.usecases.auth

import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import student.testing.system.R
import student.testing.system.data.repository.FakeAuthRepository
import student.testing.system.domain.states.operationStates.AuthState
import student.testing.system.domain.states.operationStates.OperationState
import student.testing.system.domain.models.PrivateUser
import student.testing.system.sharedPreferences.PrefsUtils


@ExperimentalCoroutinesApi
class LoginUseCaseTest {

    private val repository = FakeAuthRepository()
    private val prefsUtils = mockk<PrefsUtils>(relaxed = true)
    private val validateAuthDataUseCase = ValidateAuthDataUseCase()
    private val loginUseCase = LoginUseCase(repository, prefsUtils, validateAuthDataUseCase)

    @Test
    fun `empty data returns InputIsInvalid`() = runTest {
        val expected =
            AuthState.InputIsInvalid(R.string.error_empty_field, R.string.error_empty_field)
        val actual = loginUseCase.invoke("", "")
        assertEquals(expected, actual)
    }

    @Test
    fun `invalid E-mail format returns EmailError`() = runTest {
        val expected = AuthState.EmailError(R.string.error_invalid_email)
        val actual = loginUseCase.invoke("someEmail", "pass")
        assertEquals(expected, actual)
    }

    @Test
    fun `empty password returns PasswordError`() = runTest {
        val expected = AuthState.PasswordError(R.string.error_empty_field)
        val actual = loginUseCase.invoke("test@mail.ru", "")
        assertEquals(expected, actual)
    }

    @Test
    fun `success auth returns PrivateUser`() = runBlocking {
        val actual = loginUseCase.invoke("test@mail.ru", "pass")
        assertTrue(actual is OperationState.Success)
        assertThat((actual as OperationState.Success).data, instanceOf(PrivateUser::class.java))
    }

    @Test
    fun `failed auth returns Error`() = runBlocking {
        val expected = OperationState.Error("Incorrect username or password")
        val actual = loginUseCase.invoke("other@mail.ru", "pass")
        assertEquals(expected, actual)
    }
}