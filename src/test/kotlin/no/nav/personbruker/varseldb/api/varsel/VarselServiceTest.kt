package no.nav.personbruker.varseldb.api.varsel

import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.read.ListAppender
import kotlinx.coroutines.runBlocking
import no.nav.personbruker.varseldb.api.common.`with message containing`
import no.nav.personbruker.varseldb.api.common.database.H2Database
import no.nav.personbruker.varseldb.api.common.exception.DuplicateVarselException
import no.nav.personbruker.varseldb.api.varsel.VarselDTOObjectMother.createVarselDTO
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should throw`
import org.amshove.kluent.invoking
import org.junit.jupiter.api.*
import org.slf4j.LoggerFactory

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class VarselServiceTest {

    private val database = H2Database()
    private val varselService = VarselService(database)

    private val appender: ListAppender<ILoggingEvent> = ListAppender()
    private val logger: Logger = LoggerFactory.getLogger(VarselService::class.java) as Logger

    @BeforeAll
    private fun `setup`() {
        appender.start()
        logger.addAppender(appender)
    }

    @AfterAll
    private fun `teardown`() {
        appender.stop()
        logger.detachAppender(appender)
    }

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
        val varselDTO1 = createVarselDTO(varselId = "1")
        val varselDTO2 = createVarselDTO(varselId = "2")
        val varselDTO3 = createVarselDTO(varselId = "3")
        runBlocking {
            varselService.createVarsel(varselDTO1)
            varselService.createVarsel(varselDTO2)
            varselService.createVarsel(varselDTO3)
            val allVarsel = database.dbQuery {
                getAllVarsel()
            }
            allVarsel.size `should be equal to` 3
        }
    }

    @Test
    fun `Should throw exeption if Varsel with varselId is already persisted in DB`() {
        val duplicateVarselId = "duplicateVarsel"
        val newVarsel = createVarselDTO(varselId = duplicateVarselId)
        val existingVarsel = createVarselDTO(varselId = duplicateVarselId)
        runBlocking {
            varselService.createVarsel(newVarsel)
            invoking {
                runBlocking { varselService.createVarsel(existingVarsel) }
            } `should throw` DuplicateVarselException::class `with message containing` "Varsel med varsel-id: $duplicateVarselId finnes allerede i databasen."
            val allVarsel = database.dbQuery {
                getAllVarsel()
            }
            allVarsel.size `should be equal to` 1
            allVarsel[0].varselId `should be equal to` duplicateVarselId
        }
    }
}
