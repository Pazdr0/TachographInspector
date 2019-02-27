-- tabele
select * from "tacho".users;

select * from "tacho".drivers;

select * from "tacho".data where driver_id = 3;

select * from "tacho".verification_token;

--tabele delete
delete from "tacho".data;

--funckje
select "tacho".register ('tachograf', 'tachograf@tacho.pl', 'tachograf');

select "tacho".login ('qqq', 'qqq');

select upload_data ('tachograf', 'adasdasdsadsad sadsad ads aasds asda sdasd asda');

--zapytania
select * from "tacho".users
where (username = 'pazdr0' or email = 'postgres')
  and passwd = crypt('', passwd);

select id from "tacho".users where username = 'tachograf';

--inserty
insert into "tacho".data (usr_id, dane, upload_date) values ((select id from "tacho".users where username = 'tachograf'), 'asd adasd sad ad sadsad sadasd asd asd asda');

-- insert into "tacho".users (roles, enabled) values ('user', 'true') where username = 'tacho';

insert into "tacho".drivers (user_id, first_name, surname) values ((select id from "tacho".users where username='tachograf'), 'Muniek', 'Staszczyk');

--inne
update "tacho".users
set roles = 'USER',
    enabled = false
where id = 46;



select now();

select * from "tacho".search_data('2017-06-08', '2017-06-20');

select * from "tacho".search_data(_since, _to);

select "tacho".register('kuba', 'kuba@wp.pl', 'kuba', true);



insert into "tacho".verification_token(user_id, token, expiry_date) VALUES (6, 'sdfadsf', (select now()));

select "tacho".enable_user(46);