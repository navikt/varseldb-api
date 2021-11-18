package no.nav.personbruker.varseldb.api.health

import no.nav.personbruker.varseldb.api.config.ApplicationContext

class HealthService(private val applicationContext: ApplicationContext) {

    suspend fun getHealthChecks(): List<HealthStatus> {
        return emptyList()
    }
}
