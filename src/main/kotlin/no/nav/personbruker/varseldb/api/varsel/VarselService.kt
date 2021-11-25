package no.nav.personbruker.varseldb.api.varsel

import no.nav.personbruker.varseldb.api.common.database.Database
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class VarselService(private val database: Database) {

    private val log: Logger = LoggerFactory.getLogger(VarselService::class.java)

    suspend fun createVarsel(varsel: Varsel) {
        val dbQuery = database.dbQuery {
            getVarselByVarselid(varsel.varselid)
        }
        if(dbQuery != null) {
            database.dbQuery {
                createVarsel(varsel)
            }
        } else {
            log.warn("Varsel med varsel-id: ${varsel.varselid} finnes allerede i databasen. Varselet blir ikke lagret.")
        }
    }
}
