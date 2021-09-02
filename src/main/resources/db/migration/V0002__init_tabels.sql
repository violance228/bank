-- auto-generated definition
create table if not exists card_table
(
    id       bigint not null
        constraint card_table_pk
            primary key,
    number    varchar(16) UNIQUE not null ,
    pin_code  varchar not null,
    balance   NUMERIC(16, 2) not null
);

alter table card_table
    owner to postgres;

create unique index if not exists card_table_login_uindex
    on card_table (number);

CREATE TYPE operation_type AS ENUM ('TRANSFER_BETWEEN_ACCOUNTS', 'REPLENISHMENT', 'WITHDRAWAL');

create table if not exists transaction_table
(
    id       bigint not null
        constraint transaction_table_pk
            primary key,
    type operation_type not null ,
    interaction varchar(16) not null,
    balance_change_from NUMERIC(16, 2) not null,
    balance_change_to NUMERIC(16, 2) not null,
    card_id integer
        constraint transaction_table_card_table_id_fk
            references card_table
);

alter  table transaction_table
    owner to postgres;