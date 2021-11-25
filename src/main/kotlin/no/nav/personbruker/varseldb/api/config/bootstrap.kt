package no.nav.personbruker.varseldb.api.config

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.routing.*
import io.prometheus.client.hotspot.DefaultExports
import no.nav.personbruker.varseldb.api.health.healthApi
import no.nav.personbruker.varseldb.api.varsel.varselApi
import no.nav.tms.token.support.azure.validation.AzureAuthenticator
import no.nav.tms.token.support.azure.validation.installAzureAuth

fun Application.mainModule(appContext: ApplicationContext = ApplicationContext()) {

    DefaultExports.initialize()

    install(DefaultHeaders)

    installAzureAuth {
        setAsDefault = true
        enableDefaultProxy = true
    }

    install(ContentNegotiation)

    routing {
        healthApi(appContext.healthService)
        authenticate(AzureAuthenticator.name) {
            varselApi(appContext.varselService)
        }
    }
}
