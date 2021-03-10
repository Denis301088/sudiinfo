create table city (id integer not null, name varchar(255), district_id integer, primary key (id)) engine=InnoDB;

create table diapason_houses_street (diapason_houses_id integer not null, housing varchar(255), number integer not null, diapason_key varchar(255) not null, primary key (diapason_houses_id, diapason_key)) engine=InnoDB;

create table diapason_houses (id integer not null, streetside varchar(255), street_id integer not null, primary key (id)) engine=InnoDB;

create table district (id integer not null, region_name varchar(255), primary key (id)) engine=InnoDB;

create table district_city (id integer not null, name varchar(255), city_id integer, primary key (id)) engine=InnoDB;

create table hibernate_sequence (next_val bigint) engine=InnoDB;

insert into hibernate_sequence values ( 1 );

create table judicial_sector (id integer not null, address varchar(255), number_sector integer not null, web_address varchar(255), district_city_id integer, work_shedule_judicial_sector_id integer, primary key (id)) engine=InnoDB;

create table street (id integer not null, name varchar(255) not null, judicial_sector_id integer, primary key (id)) engine=InnoDB;

create table street_house (street_id integer not null, housing varchar(255), number integer not null) engine=InnoDB;

create table work_shedule_info (work_shedule_id integer not null, work_schedule varchar(255)) engine=InnoDB;

create table work_shedule_judicial_sector (id integer not null, primary key (id)) engine=InnoDB;

alter table city add constraint FK6i8553aggy2wl3ylubqvt5aro foreign key (district_id) references district (id);

alter table diapason_houses_street add constraint FKgglw4078f3obgulmxlxeputuo foreign key (diapason_houses_id) references diapason_houses (id);

alter table diapason_houses add constraint FKefub7hx8t085le2lho0r7dpwy foreign key (street_id) references street (id);

alter table district_city add constraint FKpw6oajgpve40wq3r1uo7v9amn foreign key (city_id) references city (id);

alter table judicial_sector add constraint FK6358jwf0edabny1bhxehwmaif foreign key (district_city_id) references district_city (id);

alter table judicial_sector add constraint FKrbwfnrhk3jd5icwjiki4ldofr foreign key (work_shedule_judicial_sector_id) references work_shedule_judicial_sector (id);

alter table street add constraint FKi8kf154i2k3sva1x4n6o1o8be foreign key (judicial_sector_id) references judicial_sector (id);

alter table street_house add constraint FKblidgkpqdth7v5q9pnqp7pj2h foreign key (street_id) references street (id);

alter table work_shedule_info add constraint FKmueodlmbff0k5h9xe95fg2uxu foreign key (work_shedule_id) references work_shedule_judicial_sector (id);
