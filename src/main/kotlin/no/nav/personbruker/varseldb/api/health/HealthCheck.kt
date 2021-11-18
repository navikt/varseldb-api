package no.nav.personbruker.varseldb.api.health

interface HealthCheck {

    suspend fun status(): HealthStatus

}
