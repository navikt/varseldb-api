package no.nav.personbruker.varseldb.api.varsel

import java.net.URL
import java.time.LocalDateTime

object VarselObjectMother {

    fun createVarsel(
        datoOpprettet: LocalDateTime = LocalDateTime.now(),
        aktoerId: String = "00001234",
        varselId: String = "varsel-id-1",
        meldingstype: String = "SPORSMAL",
        varseltekst: String = "Du har fått et spørsmål fra NAV",
        url: URL = URL("https://www.nav.no")
    ): Varsel {
        return Varsel(
            datoOpprettet = datoOpprettet,
            aktoerId = aktoerId,
            varselId = varselId,
            meldingstype = meldingstype,
            varseltekst = varseltekst,
            url = url
        )
    }
}
