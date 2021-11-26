package no.nav.personbruker.varseldb.api.varsel

import kotlinx.coroutines.runBlocking
import no.nav.personbruker.varseldb.api.common.database.H2Database
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

class VarselServiceTest {

    private val database = H2Database()
    private val varselService = VarselService(database)

    @AfterEach
    private fun cleanUp() {
        runBlocking {
            database.dbQuery {
                deleteAllVarsel()
            }
        }
    }

    @Test
    fun `Should persist each new Varsel`() {
        val varsel1 = VarselObjectMother.createVarselWithVarselid("1")
        val varsel2 = VarselObjectMother.createVarselWithVarselid("2")
        val varsel3 = VarselObjectMother.createVarselWithVarselid("3")
        runBlocking {
            varselService.createVarsel(varsel1)
            varselService.createVarsel(varsel2)
            varselService.createVarsel(varsel3)
            val allVarsel = database.dbQuery {
                getAllVarsel()
            }
            allVarsel.size `should be equal to` 3
        }
    }

    @Test
    fun `Should not persist already existing Varsel in DB`() {
        val duplicateVarselId = "duplicateVarsel"
        val newVarsel = VarselObjectMother.createVarselWithVarselid(duplicateVarselId)
        val existingVarsel = VarselObjectMother.createVarselWithVarselid(duplicateVarselId)
        runBlocking {
            varselService.createVarsel(newVarsel)
            varselService.createVarsel(existingVarsel)
            val allVarsel = database.dbQuery {
                getAllVarsel()
            }
            allVarsel.size `should be equal to` 1
            allVarsel[0].varselid `should be equal to` duplicateVarselId
        }
    }
}
