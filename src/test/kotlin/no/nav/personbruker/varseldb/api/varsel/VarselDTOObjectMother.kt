package no.nav.personbruker.varseldb.api.varsel

object VarselDTOObjectMother {

    fun createVarselDTO(
        aktoerId: String = "1234",
        varselId: String = "abc123",
        varseltypeId: String = "SPORSMAL",
        varseltekst: String = "Spørsmål fra NAV",
        varselURL: String = "https://www.nav.no"): VarselDTO {
        return VarselDTO(
            aktoerId = aktoerId,
            varselId = varselId,
            varseltypeId = varseltypeId,
            varseltekst = varseltekst,
            varselURL = varselURL
        )
    }
}
