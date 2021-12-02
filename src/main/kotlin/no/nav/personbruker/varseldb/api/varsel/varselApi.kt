package no.nav.personbruker.varseldb.api.varsel

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import no.nav.personbruker.varseldb.api.common.exception.ExceptionResponseHandler
import org.slf4j.LoggerFactory

val log = LoggerFactory.getLogger(VarselService::class.java)

fun Route.varselApi(service: VarselService) {

    post("/varsel") {
        try {
            val varselDto = call.receive<VarselDTO>()
            service.createVarsel(varselDto)
            call.respond(HttpStatusCode.Created)
        } catch(exception: Exception) {
            val errorCode = ExceptionResponseHandler.logExceptionAndDecideErrorResponseCode(log, exception)
            call.respond(errorCode)
        }

    }

}
