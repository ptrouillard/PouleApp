create table audit (
    id bigint not null,
    comment varchar(255),
    date timestamp,
    primary key (id));

create table door (
    id bigint not null,
    close_step_time bigint,
    close_time bigint,
    open_step_time bigint,
    open_time bigint,
    primary key (id));

create sequence hibernate_sequence start with 1 increment by 1;
