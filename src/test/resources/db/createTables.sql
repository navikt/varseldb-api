create table if not exists varsel
(
    ID serial not null
        constraint varsel_pkey
        primary key,
    AKTOER_ID       varchar(255) not null,
    VARSELTEKST     clob not null,
    DATO_OPPRETTET  timestamp not null,
    URL             varchar(255),
    MELDINGS_TYPE   varchar(255),
    DATO_LEST       timestamp,
    VARSEL_ID       varchar(255)
)
