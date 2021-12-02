package no.nav.personbruker.varseldb.api.varsel

import java.net.URL
import java.sql.*

private val createQuery = """INSERT INTO VARSEL(AKTOER_ID, VARSELTEKST, DATO_OPPRETTET, URL, MELDINGS_TYPE, DATO_LEST, VARSEL_ID)
    |VALUES (?, ?, ?, ?, ?, ?, ?)""".trimMargin()
private val getQuery = """SELECT * FROM VARSEL WHERE VARSEL_ID = ?"""


fun Connection.createVarsel(varsel: Varsel): Int =
    prepareStatement(createQuery, arrayOf("ID")).use {
        it.buildStatementForSingleRow(varsel)
        it.executeUpdate()
        if(it.generatedKeys.next()) {
            it.generatedKeys.getInt(1)
        } else {
            -1
        }
    }

fun Connection.getVarselByVarselid(varselId: String): Varsel? =
    prepareStatement(getQuery)
        .use {
            it.setString(1, varselId)
            it.executeQuery().singleResult {
                toVarsel()
            }
        }

fun ResultSet.toVarsel(): Varsel {
    return Varsel(
        id = getInt("ID"),
        datoOpprettet = getTimestamp("DATO_OPPRETTET").toLocalDateTime(),
        datoLest = getTimestamp("DATO_OPPRETTET")?.toLocalDateTime(),
        aktoerId = getString("AKTOER_ID"),
        varselId = getString("VARSEL_ID"),
        meldingstype = getString("MELDINGS_TYPE"),
        varseltekst = getString("VARSELTEKST"),
        url = URL(getString("URL"))
    )
}

private fun PreparedStatement.buildStatementForSingleRow(varsel: Varsel) {
    setString(1, varsel.aktoerId)
    setString(2, varsel.varseltekst)
    setObject(3, varsel.datoOpprettet, Types.TIMESTAMP)
    setString(4, varsel.url.toString())
    setString(5, varsel.meldingstype)
    setObject(6, varsel.datoLest)
    setString(7, varsel.varselId)
}



private fun <T> ResultSet.singleResult(result: ResultSet.() -> T): T? =
    if (next()) {
        result()
    } else {
        null
    }

