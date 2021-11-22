package no.nav.personbruker.varseldb.api.config

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.prometheus.client.hotspot.DefaultExports
import no.nav.personbruker.varseldb.api.health.healthApi
import no.nav.personbruker.varseldb.api.varsel.varselApi
import no.nav.tms.token.support.azure.validation.AzureAuthenticator
import no.nav.tms.token.support.azure.validation.installAzureAuth

fun Application.mainModule(appContext: ApplicationContext = ApplicationContext()) {

    val environment = Environment()

    DefaultExports.initialize()

    install(DefaultHeaders)

    installAzureAuth {
        setAsDefault = true
    }

    install(ContentNegotiation)

    routing {
        healthApi(appContext.healthService)

        get("/usikret") {
            call.respondText(text = "Usikret API.", contentType = ContentType.Text.Plain)
        }
        authenticate(AzureAuthenticator.name) {
            varselApi(appContext.varselService)
        }

    }

}
