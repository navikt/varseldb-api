package no.nav.personbruker.varseldb.api.varsel

import java.time.LocalDateTime

data class Varsel(
    val datoOpprettet: LocalDateTime,
    val datoLest: LocalDateTime? = null,
    val aktoerId: String,
    val varselId: String,
    val meldingsType: String,
    val varseltekst: String,
    val url: String
)
