package no.nav.personbruker.varseldb.api.varsel

import java.sql.Connection

fun Connection.deleteAllVarsel() {
    prepareStatement("""DELETE FROM VARSEL""")
        .use { it.execute() }
}
