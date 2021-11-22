package no.nav.personbruker.varseldb.api.varsel

import kotlinx.serialization.Serializable

@Serializable
data class VarselDTO(
    val aktoerId: String,
    val varselId: String,
    val varseltypeId: String,
    val varseltekst: String,
    val varselURL: String
)
