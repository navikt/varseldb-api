package no.nav.personbruker.varseldb.api.varsel

import java.time.LocalDateTime

object VarselObjectMother {

    fun createVarsel(): Varsel {
        return Varsel(
            datoOpprettet = LocalDateTime.now(),
            aktoerId = "0001234",
            varselId = "varsel-id-1",
            meldingsType = "SPORSMAL",
            varseltekst = "Du har fått et spørsmål fra NAV",
            url = "https://www.nav.no"
        )
    }
}
