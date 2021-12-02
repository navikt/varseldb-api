package no.nav.personbruker.varseldb.api.common.database

import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.runBlocking

class H2Database : Database {

    private val memDataSource: HikariDataSource

    init {
        memDataSource = createDataSource()
        createTables()
    }

    override val dataSource: HikariDataSource
        get() = memDataSource

    private fun createDataSource(): HikariDataSource {
        return HikariDataSource().apply {
            jdbcUrl = "jdbc:h2:mem:testdb;MODE=Oracle"
            username = "sa"
            password = ""
            validate()
        }
    }

    private fun createTables() {
        runBlocking {
            val fileContent = this::class.java.getResource("/db/createTables.sql").readText()
            dbQuery { prepareStatement(fileContent).execute() }
        }
    }

}
