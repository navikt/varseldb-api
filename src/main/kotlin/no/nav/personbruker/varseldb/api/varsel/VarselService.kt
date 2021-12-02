package no.nav.personbruker.varseldb.api.varsel

import no.nav.personbruker.varseldb.api.common.database.Database
import no.nav.personbruker.varseldb.api.common.exception.DuplicateVarselException

class VarselService(private val database: Database) {

    suspend fun createVarsel(varselDto: VarselDTO) {
        val varsel = VarselTransformer.fromDTO(varselDto)
        if(!varselAlreadyExists(varsel)) {
            database.dbQuery {
                createVarsel(varsel)
            }
        } else {
            val message = "Varsel med varsel-id: ${varsel.varselId} finnes allerede i databasen. Varselet blir ikke lagret."
            throw DuplicateVarselException(message)
        }
    }

    private suspend fun varselAlreadyExists(varsel: Varsel): Boolean {
        val existingVarsel = database.dbQuery {
            getVarselByVarselid(varsel.varselId)
        }
        return existingVarsel != null
    }
}
