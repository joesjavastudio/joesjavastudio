
drop table if exists customer_orders;
drop table if exists customer_profile;
drop table if exists customer;

create table if not exists customer
(
    id      serial primary key,
    name    text,
    version serial
);

create table if not exists customer_orders
(
    id          serial primary key,
    customer    bigint not null references customer (id),
    name        varchar(255)
);

create table if not exists customer_profile (
    id          serial primary key,
    customer    bigint not null references customer (id),
    username    text not null,
    password    text not null
);

delete from customer_profile;
delete from customer_orders;
delete from customer;