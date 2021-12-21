package no.nav.personbruker.varseldb.api.varsel

import no.nav.personbruker.varseldb.api.common.validation.validateMaxLength
import java.time.LocalDateTime

object VarselTransformer {

    fun fromDTO(varselDTO: VarselDTO): Varsel {
        return Varsel(
            datoOpprettet = LocalDateTime.now(),
            aktoerId = validateMaxLength(varselDTO.aktoerId, "aktoerId", 255),
            varselId = validateMaxLength(varselDTO.varselId, "varselId", 255),
            meldingstype = validateMaxLength(varselDTO.varseltypeId, "meldingstype", 255),
            varseltekst = varselDTO.varseltekst,
            url = varselDTO.varselURL
        )
    }
}
