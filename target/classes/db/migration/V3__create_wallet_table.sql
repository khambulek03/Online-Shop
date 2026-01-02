create table wallet (
    id binary(16) primary key not null ,
    balance double not null ,
    customer_id binary(16) not null ,
    created_at datetime(6) not null ,
    updated_at datetime(6) not null
)