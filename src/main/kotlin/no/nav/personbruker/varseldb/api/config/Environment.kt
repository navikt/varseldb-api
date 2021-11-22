package no.nav.personbruker.varseldb.api.config

import no.nav.personbruker.dittnav.common.util.config.StringEnvVar.getEnvVar

data class Environment(val dbHost: String = getEnvVar("DB_HOST"),
                       val dbName: String = getEnvVar("DB_NAME"),
                       val dbUser: String = getEnvVar("DB_USER"),
                       val dbPassword: String = getEnvVar("DB_PASSWORD"),
                       val dbUrl: String = "jdbc:oracle:thin:@$dbHost/$dbName"
)
