create table addition
(
    id             serial
        constraint addition_pk
            primary key,
    first_addend  numeric not null,
    second_addend numeric not null,
    percentage     integer not null,
    result         numeric not null,
    created        date    not null
);