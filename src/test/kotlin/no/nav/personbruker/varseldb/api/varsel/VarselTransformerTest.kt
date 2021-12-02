package no.nav.personbruker.varseldb.api.varsel

import no.nav.personbruker.varseldb.api.common.`with message containing`
import no.nav.personbruker.varseldb.api.common.exception.FieldValidationException
import no.nav.personbruker.varseldb.api.varsel.VarselDTOObjectMother.createVarselDTO
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should throw`
import org.amshove.kluent.invoking
import org.junit.jupiter.api.Test

internal class VarselTransformerTest {

    @Test
    fun `Should transform from DTO`() {
        val varselDTO = createVarselDTO()
        val varsel = VarselTransformer.fromDTO(varselDTO)
        varsel.varselId `should be equal to` varselDTO.varselId
        varsel.aktoerId `should be equal to` varselDTO.aktoerId
        varsel.meldingstype `should be equal to` varselDTO.varseltypeId
        varsel.varseltekst `should be equal to` varselDTO.varseltekst
        varsel.url.toString() `should be equal to` varselDTO.varselURL
    }

    @Test
    fun `Should throw exception if too long aktoerId`() {
        val varselDTO = createVarselDTO(aktoerId = "1".repeat(256))
        invoking {
            VarselTransformer.fromDTO(varselDTO)
        } `should throw` FieldValidationException::class `with message containing` "aktoerId"
    }

    @Test
    fun `Should throw exception if too long varselId`() {
        val varselDTO = createVarselDTO(varselId = "1".repeat(256))
        invoking {
            VarselTransformer.fromDTO(varselDTO)
        } `should throw` FieldValidationException::class `with message containing` "varselId"
    }

    @Test
    fun `Should throw exception if too long varseltypeId`() {
        val varselDTO = createVarselDTO(varseltypeId = "t".repeat(256))
        invoking {
            VarselTransformer.fromDTO(varselDTO)
        } `should throw` FieldValidationException::class `with message containing` "meldingstype"
    }

    @Test
    fun `Should throw exception if invalid URL`() {
        val varselDTO = createVarselDTO(varselURL = "t")
        invoking {
            VarselTransformer.fromDTO(varselDTO)
        } `should throw` FieldValidationException::class `with message containing` "url"
    }

    @Test
    fun `Should set varseltypeId as meldingstype`() {
        val varselDTO = createVarselDTO(varseltypeId = "OPPGAVE")
        val varsel = VarselTransformer.fromDTO(varselDTO)
        varsel.meldingstype `should be equal to` varselDTO.varseltypeId
    }
}
