package no.nav.personbruker.varseldb.api.common.database

import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import no.nav.personbruker.varseldb.api.health.HealthCheck
import no.nav.personbruker.varseldb.api.health.HealthStatus
import no.nav.personbruker.varseldb.api.health.Status
import org.slf4j.LoggerFactory
import java.sql.Connection

private val log = LoggerFactory.getLogger(Database::class.java)

interface Database : HealthCheck {

    val dataSource: HikariDataSource

    suspend fun <T> dbQuery(operationToExecute: Connection.() -> T): T = withContext(Dispatchers.IO) {
        dataSource.connection.use { openConnection ->
            try {
                openConnection.operationToExecute().apply {
                    openConnection.commit()
                }

            } catch (e: Exception) {
                try {
                    openConnection.rollback()
                } catch (rollbackException: Exception) {
                    e.addSuppressed(rollbackException)
                }
                throw e
            }
        }
    }

    override suspend fun status(): HealthStatus {
        val serviceName = "Database"
        return withContext(Dispatchers.IO) {
            try {
                dbQuery { prepareStatement("""SELECT 1""").execute() }
                HealthStatus(serviceName, Status.OK, "200 OK", includeInReadiness = false)
            } catch (e: Exception) {
                log.error("Selftest mot databasen feilet.", e)
                HealthStatus(serviceName, Status.ERROR, "Feil mot DB", includeInReadiness = false)
            }
        }
    }
}

