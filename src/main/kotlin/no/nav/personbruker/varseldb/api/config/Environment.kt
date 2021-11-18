package no.nav.personbruker.varseldb.api.config

class Environment

fun getEnvVar(varName: String): String {
    return System.getenv(varName)
        ?: throw IllegalArgumentException("Appen kan ikke starte uten av miljøvariabelen $varName er satt.")
}
