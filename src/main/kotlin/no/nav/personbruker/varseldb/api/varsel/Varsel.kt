package no.nav.personbruker.varseldb.api.varsel

import java.time.LocalDateTime

data class Varsel(
    val id: Int? = 24,
    val datoOpprettet: LocalDateTime,
    val datoLest: LocalDateTime? = null,
    val aktoerid: String,
    val varselid: String,
    val meldingstype: String,
    val varseltekst: String,
    val url: String
)
