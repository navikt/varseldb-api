package no.nav.personbruker.varseldb.api.common.exception

import io.ktor.http.*
import kotlinx.serialization.SerializationException
import org.slf4j.Logger
import java.lang.Exception

object ExceptionResponseHandler {

    fun logExceptionAndDecideErrorResponseCode(log: Logger, exception: Exception): HttpStatusCode {
        return when(exception) {
            is DuplicateVarselException -> {
                val errorCode = HttpStatusCode.Conflict
                val msg = "Klarte ikke opprette varsel. Returnerer feilkoden $errorCode"
                log.warn(msg, exception)
                errorCode
            }
            is FieldValidationException -> {
                val errorCode = HttpStatusCode.BadRequest
                val msg = "Mottok request med ugyldig input. Returnerer feilkoden $errorCode"
                log.warn(msg, exception)
                errorCode
            }
            is SerializationException -> {
                val errorCode = HttpStatusCode.BadRequest
                val msg = "Klarte ikke serialisere input. Returnerer feilkoden $errorCode"
                log.warn(msg, exception)
                errorCode
            }
            is DatabaseException -> {
                val errorCode = HttpStatusCode.InternalServerError
                val msg = "Det skjedde en database-relatert feil. Returnerer feilkoden $errorCode"
                log.error(msg, exception)
                errorCode
            }
            else -> {
                val errorCode = HttpStatusCode.InternalServerError
                val msg = "Ukjent feil oppstod. Returnerer feilkoden $errorCode. $exception"
                log.error(msg, exception)
                errorCode
            }
        }
    }
}
