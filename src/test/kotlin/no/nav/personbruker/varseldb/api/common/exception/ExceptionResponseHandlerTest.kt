package no.nav.personbruker.varseldb.api.common.exception

import io.ktor.http.*
import io.mockk.clearMocks
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.SerializationException
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.Logger

internal class ExceptionResponseHandlerTest {

    private val log = mockk<Logger>(relaxed = true)

    @BeforeEach
    fun clearMock() {
        clearMocks(log)
    }

    @Test
    fun `Should handle DuplicateVarselException`() {
        val exception = DuplicateVarselException("Simulert feil")

        val errorCode = runBlocking {
            ExceptionResponseHandler.logExceptionAndDecideErrorResponseCode(log, exception)
        }

        errorCode `should be equal to` HttpStatusCode.Conflict
        coVerify(exactly = 1) { log.warn(any<String>(), any()) }
        confirmVerified(log)
    }

    @Test
    fun `Should handle FieldValidationException`() {
        val exception = FieldValidationException("Simulert feil")

        val errorCode = runBlocking {
            ExceptionResponseHandler.logExceptionAndDecideErrorResponseCode(log, exception)
        }

        errorCode `should be equal to` HttpStatusCode.BadRequest
        coVerify(exactly = 1) { log.warn(any<String>(), any()) }
        confirmVerified(log)
    }

    @Test
    fun `Should handle SerializationException`() {
        val exception = SerializationException("Simulert feil")

        val errorCode = runBlocking {
            ExceptionResponseHandler.logExceptionAndDecideErrorResponseCode(log, exception)
        }

        errorCode `should be equal to` HttpStatusCode.BadRequest
        coVerify(exactly = 1) { log.warn(any<String>(), any()) }
        confirmVerified(log)
    }

    @Test
    fun `Should handle DatabaseException`() {
        val exception = DatabaseException("Simulert feil")

        val errorCode = runBlocking {
            ExceptionResponseHandler.logExceptionAndDecideErrorResponseCode(log, exception)
        }

        errorCode `should be equal to` HttpStatusCode.InternalServerError
        coVerify(exactly = 1) { log.error(any<String>(), any()) }
        confirmVerified(log)
    }

    @Test
    fun `Should handle unknown exceptions`() {
        val exception = SecurityException("Simulert feil")

        val errorCode = runBlocking {
            ExceptionResponseHandler.logExceptionAndDecideErrorResponseCode(log, exception)
        }

        errorCode `should be equal to` HttpStatusCode.InternalServerError
        coVerify(exactly = 1) { log.error(any<String>(), any()) }
        confirmVerified(log)
    }

}
