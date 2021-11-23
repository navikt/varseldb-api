package no.nav.personbruker.varseldb.api.varsel

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.routing.*
import java.time.LocalDateTime

fun Route.varselApi(
    service: VarselService
) {
    post("/varsel") {
        val varselDto = call.receive<VarselDTO>()
        val varsel = varselMapper(varselDto)
        service.createVarsel(varsel)
    }
}

private fun varselMapper(varselDTO: VarselDTO): Varsel {
    return Varsel(
        datoOpprettet = LocalDateTime.now(),
        aktoerId = varselDTO.aktoerId,
        varselId = varselDTO.varselId,
        meldingsType = varselDTO.varseltypeId,
        varseltekst = varselDTO.varseltekst,
        url = varselDTO.varselURL
    )
}
