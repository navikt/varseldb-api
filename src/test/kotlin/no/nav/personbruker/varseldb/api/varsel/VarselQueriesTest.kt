package no.nav.personbruker.varseldb.api.varsel

import kotlinx.coroutines.runBlocking
import no.nav.personbruker.varseldb.api.common.database.H2Database
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be null`
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class VarselQueriesTest {

    private val database = H2Database()

    @AfterAll
    fun tearDown() {
        runBlocking {
            database.dbQuery { deleteAllVarsel() }
        }
    }

    @Test
    fun `Should create Varsel`() {
        runBlocking {
            database.dbQuery {
                createVarsel(VarselObjectMother.createVarsel(varselId = "varselId1"))
                createVarsel(VarselObjectMother.createVarsel(varselId = "varselId2"))
                createVarsel(VarselObjectMother.createVarsel(varselId = "varselId3"))
            }
            val varselWithVarselid = database.dbQuery { getVarselByVarselid("varselId3") }
            varselWithVarselid?.varselId `should be equal to` "varselId3"
        }
    }

    @Test
    fun `Should return Varsel with varselId`() {
        val varselToPersist = VarselObjectMother.createVarsel(varselId = "varselId4")
        runBlocking {
            database.dbQuery {
                createVarsel(varselToPersist)
            }
            val varselWithVarselid = database.dbQuery { getVarselByVarselid("varselId4") }
            varselToPersist.varselId `should be equal to` varselWithVarselid?.varselId
            varselToPersist.varseltekst `should be equal to` varselWithVarselid?.varseltekst
            varselToPersist.aktoerId `should be equal to` varselWithVarselid?.aktoerId
            varselToPersist.datoOpprettet `should be equal to` varselWithVarselid?.datoOpprettet
            varselToPersist.meldingstype `should be equal to` varselWithVarselid?.meldingstype
            varselToPersist.url `should be equal to` varselWithVarselid?.url
        }
    }

    @Test
    fun `Should return null if Varsel with varselId is not found`() {
        runBlocking {
            val emptyVarsel = database.dbQuery { getVarselByVarselid("unknown") }
            emptyVarsel.`should be null`()
        }
    }
}
