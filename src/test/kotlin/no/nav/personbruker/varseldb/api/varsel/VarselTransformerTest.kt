package no.nav.personbruker.varseldb.api.varsel

import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

class VarselTransformerTest {

    @Test
    fun `Should transform from DTO`() {
        val varselDTO = createVarselDTO()
        val varsel = VarselTransformer.fromDTO(varselDTO)
        varsel.varselid `should be equal to` varselDTO.varselId
        varsel.aktoerid `should be equal to` varselDTO.aktoerId
        varsel.meldingstype `should be equal to` varselDTO.varseltypeId
        varsel.varseltekst `should be equal to` varselDTO.varseltekst
        varsel.url `should be equal to` varselDTO.varselURL
    }

    private fun createVarselDTO(): VarselDTO {
        return VarselDTO(
            aktoerId = "1234",
            varselId = "abc123",
            varseltypeId = "SPORSMAL",
            varseltekst = "Spørsmål fra NAV",
            varselURL = "https://www.nav.no"
        )
    }

}
