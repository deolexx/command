#!/bin/bash
set -e
psql --username admin --dbname postgres <<-EOSQL
  CREATE DATABASE $APP_DB_NAME;
  \connect postgres admin
  BEGIN;
    CREATE SCHEMA command;
 create  table command.user (
     user_id integer PRIMARY KEY,
     username varchar(50),
     first_name varchar(50),
     last_name varchar(50),
     user_role varchar(20),
     user_group varchar(50)
     );
create table command.mentor_student
(
	mentor_id integer not null
		constraint fkgt4pp0cudpkjggg3g62m7vabo
			references command."user",
	student_id integer not null
		constraint fknx31pdrht7qlg5uth68l5sdp9
			references command."user",
	constraint mentor_student_pkey
		primary key (mentor_id, student_id)
);

alter table command.mentor_student owner to admin;




  COMMIT;
EOSQL