create table district_judicial_sector (id integer not null, number_sector int, address varchar(255),web_address varchar(255),district_id integer, work_shedule_judicial_sector_id integer, primary key (id)) engine=InnoDB;

create table inhabited_locality (id integer not null, name varchar(255),district_judicial_sector_id integer, primary key (id)) engine=InnoDB;

create table settlement (id integer not null, name varchar(255), inhabited_locality_id integer, primary key (id)) engine=InnoDB;



alter table district_judicial_sector add constraint district_key foreign key (district_id) references district (id);

alter table district_judicial_sector add constraint work_shedule_judicial_sector_key foreign key (work_shedule_judicial_sector_id) references work_shedule_judicial_sector (id);

alter table inhabited_locality add constraint district_judicial_key foreign key (district_judicial_sector_id) references district_judicial_sector (id);

alter table settlement add constraint nhabited_locality_key foreign key (inhabited_locality_id) references inhabited_locality (id);

