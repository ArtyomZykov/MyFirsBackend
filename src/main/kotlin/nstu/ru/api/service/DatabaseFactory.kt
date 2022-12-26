package nstu.ru.api.service

import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.config.*
import nstu.ru.api.domain.Client
import nstu.ru.api.domain.House
import nstu.ru.api.domain.Request
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseFactory(private val config: HoconApplicationConfig) {

    fun initialize() {
        val dataSource = hikariDataSource()
        Database.connect(dataSource)
        transaction {
            SchemaUtils.createDatabase()
            SchemaUtils.create(tables = arrayOf(Client, Request, House))
        }
        runFlyWay(dataSource)
    }

    private fun runFlyWay(dataSource: HikariDataSource): Unit {
        val flyway = Flyway.configure()
            .dataSource(dataSource)
            .baselineOnMigrate(true)
            .load()
        try {
            flyway.info()
            flyway.migrate()
        } catch (e: Exception) {
            throw e
        }
    }

    private fun hikariDataSource(): HikariDataSource {
        val hikariDataSource = HikariDataSource()
        hikariDataSource.password = config.property("ktor.security.password").getString()
        hikariDataSource.jdbcUrl = config.property("ktor.security.connection-string").getString()
        hikariDataSource.username = config.property("ktor.security.userName").getString()
        hikariDataSource.schema = "public"
        hikariDataSource.driverClassName = "org.postgresql.Driver"
        return hikariDataSource
    }
}
