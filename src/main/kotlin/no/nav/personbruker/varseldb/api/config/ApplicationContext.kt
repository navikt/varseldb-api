package no.nav.personbruker.varseldb.api.config

import no.nav.personbruker.varseldb.api.health.HealthService

class ApplicationContext {

    val healthService = HealthService(this)

}
