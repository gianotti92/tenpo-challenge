create table tenpodb.external_call
(
    id serial
        constraint external_call_pk
            primary key,
    result varchar(20),
    http_code int not null,
    created date not null
);

