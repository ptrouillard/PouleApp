alter table audit add time bigint;
alter table audit add remote_addr varchar(50);
alter table audit add old_value varchar(50);
alter table audit add new_value varchar(50);