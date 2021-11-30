package no.nav.personbruker.varseldb.api.common.exception


class FieldValidationException(message: String, cause: Throwable?) : AbstractVarselException(message, cause) {

    constructor(message: String) : this(message, null)

}
