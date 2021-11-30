/**
 * Anbefalte versjoner av tredjepartsbiblioteker.
 */
object DittNAV {
    object Common {
        private const val version = "2021.05.18-12.42-9ba5c329c21d"
        private const val groupId = "com.github.navikt.dittnav-common-lib"

        const val utils = "$groupId:dittnav-common-utils:$version"
    }
}

object H2Database {
    private const val version = "1.4.200"
    const val h2 = "com.h2database:h2:$version"
}

object Hikari {
    private const val version = "5.0.0"
    const val cp = "com.zaxxer:HikariCP:$version"
}

object Junit {
    private const val version = "5.4.1"
    private const val groupId = "org.junit.jupiter"

    const val api = "$groupId:junit-jupiter-api:$version"
    const val engine = "$groupId:junit-jupiter-engine:$version"
}


object Kluent {
    private const val version = "1.68"
    const val kluent = "org.amshove.kluent:kluent:$version"
}

object Kotlin {
    const val version = "1.4.31"
    private const val groupId = "org.jetbrains.kotlin"

    const val reflect = "$groupId:kotlin-reflect:$version"
}

object Kotlinx {
    private const val groupId = "org.jetbrains.kotlinx"

    const val coroutines = "$groupId:kotlinx-coroutines-core:1.5.2"
}

object Ktor {
    const val version = "1.6.5"
    private const val groupId = "io.ktor"

    const val authJwt = "$groupId:ktor-auth-jwt:$version"
    const val htmlBuilder = "$groupId:ktor-html-builder:$version"
    const val clientSerializationJvm = "$groupId:ktor-client-serialization-jvm:$version"
    const val serialization = "$groupId:ktor-serialization:$version"
}

object Logback {
    // Velger å ta i bruk følgende alpha-versjon, fordi det har fikset feilen som kan gi stackoverflow i Logback i Ktor:
    // https://youtrack.jetbrains.com/issue/KTOR-2040#focus=Comments-27-5225266.0-0
    private const val version = "1.3.0-alpha10"
    const val classic = "ch.qos.logback:logback-classic:$version"
}

object Logstash {
    private const val version = "6.4"
    const val logbackEncoder = "net.logstash.logback:logstash-logback-encoder:$version"
}

object Mockk {
    private const val version = "1.10.5"
    const val mockk = "io.mockk:mockk:$version"
}

object Oracle {
    private const val version = "19.3.0.0"
    private const val groupId = "com.oracle.ojdbc"

    const val ojdbc10 = "$groupId:ojdbc10:$version"
}

object Prometheus {
    private const val version = "0.9.0"
    private const val groupId = "io.prometheus"

    const val common = "$groupId:simpleclient_common:$version"
    const val hotspot = "$groupId:simpleclient_hotspot:$version"
    const val logback = "$groupId:simpleclient_logback:$version"
}

object Shadow {
    const val version = "7.0.0"
    const val pluginId = "com.github.johnrengelman.shadow"
}

object Tms {
    object KtorTokenSupport {
        private const val version = "2021.11.25-12.24-895cf154b0ec"
        private const val groupId = "com.github.navikt.tms-ktor-token-support"

        const val azureValidation = "$groupId:token-support-azure-validation:$version"
    }
}
