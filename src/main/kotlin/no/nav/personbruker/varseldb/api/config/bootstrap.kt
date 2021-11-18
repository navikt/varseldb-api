package no.nav.personbruker.varseldb.api.config

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.client.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.*
import io.ktor.util.pipeline.*
import io.prometheus.client.hotspot.DefaultExports
import no.nav.personbruker.varseldb.api.common.AuthenticatedUser
import no.nav.personbruker.varseldb.api.common.AuthenticatedUserFactory
import no.nav.personbruker.varseldb.api.health.healthApi
import no.nav.security.token.support.ktor.tokenValidationSupport

@KtorExperimentalAPI
fun Application.mainModule(appContext: ApplicationContext = ApplicationContext()) {
    val environment = Environment()

    DefaultExports.initialize()

    install(DefaultHeaders)


    install(ContentNegotiation) {
        jackson {
            enableDittNavJsonConfig()
        }
    }

    routing {
        healthApi(appContext.healthService)

        get("/usikret") {
            call.respondText(text = "Usikret API.", contentType = ContentType.Text.Plain)
        }

    }

    configureShutdownHook(appContext.httpClient)
}

private fun Application.configureShutdownHook(httpClient: HttpClient) {
    environment.monitor.subscribe(ApplicationStopping) {
        httpClient.close()
    }
}

val PipelineContext<Unit, ApplicationCall>.authenticatedUser: AuthenticatedUser
    get() = AuthenticatedUserFactory.createNewAuthenticatedUser(call)
