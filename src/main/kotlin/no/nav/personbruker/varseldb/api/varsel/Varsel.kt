package no.nav.personbruker.varseldb.api.varsel

data class Varsel(
    val aktoerId: String,
    val varselId: String,
    val meldingsType: String,
    val varseltekst: String,
    val url: String
)
