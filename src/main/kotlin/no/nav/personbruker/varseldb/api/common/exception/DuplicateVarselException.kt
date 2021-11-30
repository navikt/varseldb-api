package no.nav.personbruker.varseldb.api.common.exception

class DuplicateVarselException(message: String, cause: Throwable?) : AbstractVarselException(message, cause) {

    constructor(message: String) : this(message, null)

}
