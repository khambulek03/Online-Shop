create table customer (
    id binary(16) unique not null ,
    name varchar(255) not null ,
    surname varchar(255) not null ,
    email varchar(255) unique not null ,
    phone varchar(255) unique not null ,
    created_at datetime(6) not null
)