package no.nav.personbruker.varseldb.api.config

import no.nav.personbruker.varseldb.api.common.database.Database
import no.nav.personbruker.varseldb.api.health.HealthService
import no.nav.personbruker.varseldb.api.varsel.VarselService

class ApplicationContext {

    private val environment = Environment()

    val database: Database = OracleDatabase(environment)
    val varselService = VarselService(database)

    val healthService = HealthService(this)

}
