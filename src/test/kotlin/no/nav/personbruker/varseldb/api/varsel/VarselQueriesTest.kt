package no.nav.personbruker.varseldb.api.varsel

import kotlinx.coroutines.runBlocking
import no.nav.personbruker.varseldb.api.common.database.H2Database
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be null`
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class VarselQueriesTest {

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
                createVarsel(VarselObjectMother.createVarselWithVarselid("varselid1"))
                createVarsel(VarselObjectMother.createVarselWithVarselid("varselid2"))
                createVarsel(VarselObjectMother.createVarselWithVarselid("varselid3"))
            }
            val varselWithVarselid = database.dbQuery { getVarselByVarselid("varselid3") }
            varselWithVarselid?.varselid `should be equal to` "varselid3"
        }
    }

    @Test
    fun `Should return Varsel with varselid`() {
        val varselToPersist = VarselObjectMother.createVarselWithVarselid("varselid4")
        runBlocking {
            database.dbQuery {
                createVarsel(varselToPersist)
            }
            val varselWithVarselid = database.dbQuery { getVarselByVarselid("varselid4") }
            varselToPersist.varselid `should be equal to` varselWithVarselid?.varselid
            varselToPersist.varseltekst `should be equal to` varselWithVarselid?.varseltekst
            varselToPersist.aktoerid `should be equal to` varselWithVarselid?.aktoerid
            varselToPersist.datoOpprettet `should be equal to` varselWithVarselid?.datoOpprettet
            varselToPersist.meldingstype `should be equal to` varselWithVarselid?.meldingstype
            varselToPersist.url `should be equal to` varselWithVarselid?.url
        }
    }

    @Test
    fun `Should return null if Varsel with varselid is not found`() {
        runBlocking {
            val emptyVarsel = database.dbQuery { getVarselByVarselid("unknown") }
            emptyVarsel.`should be null`()
        }
    }
}
