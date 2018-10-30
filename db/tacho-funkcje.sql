--Register-------------------------------------------------------------------------------------------------------
create or replace function "tacho".register (_username varchar(30), _email varchar(30), _passwd varchar(200))
	returns boolean as $registered$
declare 
	registered boolean;
begin
	if (select count(*) from "tacho".users 
			where lower(username) = lower(_username)) >=1 
	then
		return false;
	end if;
										  
	if (select count(*) from "tacho".users 
			where lower(email) = lower(_email)) 
	then 
		return false;
	end if;
	insert into "tacho".users (username, email, passwd) values (_username, lower(_email), crypt(_passwd, gen_salt('bf', 8)));
	registered := true;

	return registered;
end;
$registered$ language plpgsql
-----------------------------------------------------------------------------------------------------------------
																										   
--Login----------------------------------------------------------------------------------------------------------						   
create or replace function "tacho".login (_login varchar(30), _passwd varchar(200))
	returns boolean as $loggedd$
declare 
	loggedd boolean;
begin
	if (select count(*) from "tacho".users 
			where (username = lower(_login) or email = (_login)) 
			and passwd = crypt(_passwd, passwd)) >= 1
	then 
		loggedd := true;
	else 
		loggedd := false;
	end if;
							   
	return loggedd;
end;
$loggedd$ language plpgsql
-----------------------------------------------------------------------------------------------------------------							   
							   
--Upload Data----------------------------------------------------------------------------------------------------						   
create or replace function upload_data(_username varchar(30), _dane varchar(1000)) 
	returns boolean as $uploaded$
declare 
	uploaded boolean;
begin
	insert into "tacho".dane (usr_id, dane, upload_date) 
		values ((select id from "tacho".users where username = _username), _dane, date_trunc('second', now()));
	return true;
end;
$uploaded$ language plpgsql
-----------------------------------------------------------------------------------------------------------------

--Get Drivers----------------------------------------------------------------------------------------------------																						 
create or replace function get_drivers(_)


--Import Data----------------------------------------------------------------------------------------------------						   
create or replace function import_data(_username)
																							 
																							 
--Import Data 2--------------------------------------------------------------------------------------------------						   
create or replace function import_data(_username, _date)																						 
																							 
																	 
																							 
																							 
