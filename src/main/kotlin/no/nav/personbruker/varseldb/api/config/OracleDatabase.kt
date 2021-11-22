package no.nav.personbruker.varseldb.api.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import no.nav.personbruker.varseldb.api.common.database.Database

class OracleDatabase(env: Environment) : Database {

    private val envDataSource: HikariDataSource

    init {
        envDataSource = createConnection(env)
    }

    override val dataSource: HikariDataSource
        get() = envDataSource

    private fun createConnection(env: Environment): HikariDataSource {
        val config = hikariCommonConfig(env)
        config.validate()
        return HikariDataSource(config)
    }

    companion object {
        private fun hikariCommonConfig(env: Environment): HikariConfig {
            val config = HikariConfig()
            config.driverClassName = "oracle.jdbc.driver.OracleDriver"
            config.jdbcUrl = env.dbUrl
            config.minimumIdle = 0
            config.maxLifetime = 1800000
            config.maximumPoolSize = 2
            config.connectionTimeout = 3000
            config.validationTimeout = 500
            config.idleTimeout = 30000
            config.isAutoCommit = false
            config.username = env.dbUser
            config.password = env.dbPassword
            return config
        }
    }
}
