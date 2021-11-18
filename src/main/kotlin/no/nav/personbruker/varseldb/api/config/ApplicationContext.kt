package no.nav.personbruker.varseldb.api.config

import no.nav.personbruker.varseldb.api.health.HealthService

class ApplicationContext {

    val httpClient = HttpClientBuilder.build()
    val healthService = HealthService(this)

}
