create table "tacho".users(
	id serial primary key,
	username varchar(30) not null,
	email varchar(30) not null,
	passwd varchar(200) not null,
	roles varchar(10) not null default 'user',
	active boolean not null default false
);

create table "tacho".dane(
	id serial primary key,
	usr_id int references "tacho".users(id) not null,
	dane varchar(1000) not null,
	upload_date date not null
);

alter table "tacho".data
	--alter column upload_data timestamp without time zone not null;
	alter column date date not null default 'user'

drop table "tacho".users
