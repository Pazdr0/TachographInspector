create table "tacho".users(
	id serial primary key,
	username varchar(30) not null,
	email varchar(30) not null,
	passwd varchar(200) not null
);

create table "tacho".dane(
	id serial primary key,
	usr_id int references "tacho".users(id) not null,
	dane varchar(1000) not null,
	upload_date date not null
);

alter table "tacho".dane
	alter column upload_data timestamp without time zone not null;

drop table "tacho".dane
