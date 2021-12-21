package no.nav.personbruker.varseldb.api.varsel

import java.time.LocalDateTime

data class Varsel(
    val id: Int?,
    val datoOpprettet: LocalDateTime,
    val datoLest: LocalDateTime? = null,
    val aktoerId: String,
    val varselId: String,
    val meldingstype: String,
    val varseltekst: String,
    val url: String?
) {
    constructor(
        datoOpprettet: LocalDateTime,
        aktoerId: String,
        varselId: String,
        meldingstype: String,
        varseltekst: String,
        url: String?
    ) : this(id = null,
        datoOpprettet = datoOpprettet,
        datoLest = null,
        aktoerId = aktoerId,
        varselId = varselId,
        meldingstype = meldingstype,
        varseltekst = varseltekst,
        url = url
    )
}
