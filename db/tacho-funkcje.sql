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
																										   
--Login - Deprecated----------------------------------------------------------------------------------------------
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
							   
create or replace function "tacho".enableRegisteredUser(_user_id integer)
 returns boolean as $enabled$
declare
 enabled boolean;
begin
 update "tacho".users
  set enabled = true
  where id = _user_id;

  enabled = true;
 return enabled
end;
$enabled$ language plpgsql