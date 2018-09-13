-- tabele
select * from "tacho".users

select * from "tacho".dane

--tabele delete
delete from "tacho".dane
--funckje
select "tacho".register ('tachograf', 'tachograf@tacho.pl', 'tachograf')

select "tacho".login ('qqq', 'qqq')

select upload_data ('tachograf', 'adasdasdsadsad sadsad ads aasds asda sdasd asda')

--zapytania
select * from "tacho".users 
		where (username = '' or email = 'postgres') 
		and passwd = crypt('postgres', passwd);

select id from "tacho".users where username = 'tachograf'

insert into "tacho".dane (usr_id, dane, upload_date) values ((select id from "tacho".users where username = 'tachograf'), 'asd adasd sad ad sadsad sadasd asd asd asda')

select now()
