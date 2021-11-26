package no.nav.personbruker.varseldb.api.varsel

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.routing.*
import java.time.LocalDateTime

fun Route.varselApi(service: VarselService) {

    post("/varsel") {
        val varselDto = call.receive<VarselDTO>()
        val varsel = VarselTransformer.fromDTO(varselDto)
        service.createVarsel(varsel)
    }

}
