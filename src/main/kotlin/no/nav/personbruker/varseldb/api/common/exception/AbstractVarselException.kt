package no.nav.personbruker.varseldb.api.common.exception

abstract class AbstractVarselException(message: String, cause: Throwable?) : Exception(message, cause) {

    constructor(message: String) : this(message, null)

    val context: MutableMap<String, Any?> = mutableMapOf()

    fun addContext(key: String, value: Any?): AbstractVarselException {
        context[key] = value
        return this
    }

    override fun toString(): String {
        return when (context.isNotEmpty()) {
            true -> super.toString() + ", context: $context"
            false -> super.toString()
        }
    }

}
