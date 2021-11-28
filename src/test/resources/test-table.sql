create schema command;


create table command.user
(
    user_id    integer PRIMARY KEY,
    username   varchar(50),
    first_name varchar(50),
    last_name  varchar(50),
    user_role  varchar(20),
    user_group varchar(50)
);