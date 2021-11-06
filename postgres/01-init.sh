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
  INSERT INTO command.user (
  user_id,
  username,
  first_name,
  last_name,
  user_role,
  user_group
  )
  VALUES
  (1,'rocky','Silvester','Stallone','user','green'),
  (2,'invincible','Chuck','Norris','lead','green'),
  (3,'terminator','Arnold','Schwarzenegger','admin','');
  COMMIT;
EOSQL