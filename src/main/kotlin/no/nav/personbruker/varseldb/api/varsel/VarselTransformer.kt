package no.nav.personbruker.varseldb.api.varsel

import java.time.LocalDateTime

object VarselTransformer {

    fun fromDTO(varselDTO: VarselDTO): Varsel {
        return Varsel(
            datoOpprettet = LocalDateTime.now(),
            aktoerid = varselDTO.aktoerId,
            varselid = varselDTO.varselId,
            meldingstype = varselDTO.varseltypeId,
            varseltekst = varselDTO.varseltekst,
            url = varselDTO.varselURL
        )
    }
}
