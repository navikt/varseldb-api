package no.nav.personbruker.varseldb.api.varsel

import java.time.LocalDateTime

data class Varsel(
    val id: Int?,
    val datoOpprettet: LocalDateTime,
    val datoLest: LocalDateTime? = null,
    val aktoerid: String,
    val varselid: String,
    val meldingstype: String,
    val varseltekst: String,
    val url: String
) {
    constructor(
        datoOpprettet: LocalDateTime,
        aktoerid: String,
        varselid: String,
        meldingstype: String,
        varseltekst: String,
        url: String
    ) : this(id = null,
        datoOpprettet = datoOpprettet,
        datoLest = null,
        aktoerid = aktoerid,
        varselid = varselid,
        meldingstype = meldingstype,
        varseltekst = varseltekst,
        url = url
    )
}
