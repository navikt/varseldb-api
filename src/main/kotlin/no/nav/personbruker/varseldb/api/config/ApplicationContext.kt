package no.nav.personbruker.varseldb.api.config

import no.nav.personbruker.varseldb.api.health.HealthService
import no.nav.personbruker.varseldb.api.varsel.VarselService

class ApplicationContext {

    val healthService = HealthService(this)
    val varselService = VarselService()

}
