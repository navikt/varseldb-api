package no.nav.personbruker.varseldb.api.common.validation

import no.nav.personbruker.varseldb.api.common.exception.FieldValidationException
import java.net.URL

fun validateMaxLength(field: String, fieldName: String, maxLength: Int): String {
    if(field.length > maxLength) {
        throw FieldValidationException("Feltet $fieldName kan ikke inneholde mer enn $maxLength tegn.")
    } else {
        return field
    }
}

fun toUrl(field: String?, fieldName: String): URL? {
    return try {
        return if(!field.isNullOrBlank()) {
            URL(field)
        } else {
            null
        }
    } catch (exception: Exception) {
        throw FieldValidationException("Feltet $fieldName inneholdt ugyldig url.", exception)
    }
}
