package no.nav.personbruker.varseldb.api.varsel

import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.read.ListAppender
import kotlinx.coroutines.runBlocking
import no.nav.personbruker.varseldb.api.common.database.H2Database
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should contain`
import org.junit.jupiter.api.*
import org.slf4j.LoggerFactory

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class VarselServiceTest {

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
    fun `Should not persist Varsel with varselid already present in DB`() {
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

    @Test
    fun `Should log warning if Varsel with existing varselid is persisted`() {
        val duplicateVarselId = "duplicateVarsel"
        val newVarsel = VarselObjectMother.createVarselWithVarselid(duplicateVarselId)
        val existingVarsel = VarselObjectMother.createVarselWithVarselid(duplicateVarselId)
        runBlocking {
            varselService.createVarsel(newVarsel)
            varselService.createVarsel(existingVarsel)
        }
        val logevent = appender.list.first()
        logevent.level.levelStr `should be equal to` "WARN"
        logevent.formattedMessage `should contain` "Varsel med varsel-id: $duplicateVarselId finnes allerede i databasen."
    }
}
