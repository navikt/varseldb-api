package no.nav.personbruker.varseldb.api.varsel

import java.sql.Connection
import java.sql.ResultSet

fun Connection.deleteAllVarsel() {
    prepareStatement("""DELETE FROM VARSEL""")
        .use { it.execute() }
}

fun Connection.getAllVarsel(): List<Varsel> {
    return prepareStatement("""SELECT * FROM VARSEL""")
        .use { it.executeQuery().list {
            toVarsel()
        } }
}

private fun <T> ResultSet.list(result: ResultSet.() -> T): List<T> =
    mutableListOf<T>().apply {
        while (next()) {
            add(result())
        }
    }
