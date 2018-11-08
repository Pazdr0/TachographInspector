-- tabele
select * from "tacho".users;

select * from "tacho".drivers;

select * from "tacho".data;

--tabele delete
delete from "tacho".data;

--funckje
select "tacho".register ('tachograf', 'tachograf@tacho.pl', 'tachograf');

select "tacho".login ('qqq', 'qqq');

select upload_data ('tachograf', 'adasdasdsadsad sadsad ads aasds asda sdasd asda');

--zapytania
select * from "tacho".users
		where (username = '' or email = 'postgres')
		and passwd = crypt('postgres', passwd);

select id from "tacho".users where username = 'tachograf';

--inserty
insert into "tacho".data (usr_id, dane, upload_date) values ((select id from "tacho".users where username = 'tachograf'), 'asd adasd sad ad sadsad sadasd asd asd asda');

-- insert into "tacho".users (roles, enabled) values ('user', 'true') where username = 'tacho';

insert into "tacho".drivers (user_id, first_name, surname) values ((select id from "tacho".users where username='tachograf'), 'Muniek', 'Staszczyk');

--inne
update "tacho".users
	set roles = 'USER',
	 	active = true
	where username = 'pazdr0';
	
select now()
