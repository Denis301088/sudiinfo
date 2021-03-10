create table admins (id integer not null, username varchar(255), password varchar(255), primary key (id)) engine=InnoDB;

create table admins_roles (admins_id integer, roles varchar(255)) engine=InnoDB;

alter table admins_roles add constraint roles_admin foreign key (admins_id) references admins (id);
