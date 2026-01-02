create table product (
    id binary(16) primary key not null ,
    name varchar(255) not null ,
    model varchar(255) not null ,
    cpu varchar(255) not null ,
    ram bigint not null ,
    rom double not null ,
    quantity bigint not null ,
    created_at datetime not null
)