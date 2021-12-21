package no.nav.personbruker.varseldb.api.common.validation

import no.nav.personbruker.varseldb.api.common.exception.FieldValidationException

fun validateMaxLength(field: String, fieldName: String, maxLength: Int): String {
    if(field.length > maxLength) {
        throw FieldValidationException("Feltet $fieldName kan ikke inneholde mer enn $maxLength tegn.")
    } else {
        return field
    }
}
