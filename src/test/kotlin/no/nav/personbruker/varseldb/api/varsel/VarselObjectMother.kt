package no.nav.personbruker.varseldb.api.varsel

import java.time.LocalDateTime

object VarselObjectMother {

    private val defaultDatoOpprettet = LocalDateTime.now()
    private val defaultAktoerid = "00001234"
    private val defaultVarselid = "varsel-id-1"
    private val defaultMeldingstype = "SPORSMAL"
    private val defaultVarseltekst = "Du har fått et spørsmål fra NAV"
    private val defaultUrl = "https://www.nav.no"


    fun createVarsel(): Varsel {
        return Varsel(
            datoOpprettet = defaultDatoOpprettet,
            aktoerid = defaultAktoerid,
            varselid = defaultVarselid,
            meldingstype = defaultMeldingstype,
            varseltekst = defaultVarseltekst,
            url = defaultUrl
        )
    }

    fun createVarselWithVarselid(varselid: String): Varsel {
        return Varsel(
            datoOpprettet = defaultDatoOpprettet,
            aktoerid = defaultAktoerid,
            varselid = varselid,
            meldingstype = defaultMeldingstype,
            varseltekst = defaultVarseltekst,
            url = defaultUrl
        )
    }
}
