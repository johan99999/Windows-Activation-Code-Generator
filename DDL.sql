show databases;

create database windows_activation;

use windows_activation;

show tables ;

create table windows_7
(
    id int not null primary key auto_increment,
    activation_code varchar(100) not null,
    version varchar(100),
    created_at datetime default current_timestamp
) engine InnoDB;
desc windows_7;
drop table windows_7;
select * from windows_7;


create table windows_8
(
    id int not null primary key auto_increment,
    activation_code varchar(100) not null,
    version varchar(100),
    created_at datetime default current_timestamp
) engine InnoDB;
desc windows_8;
drop table windows_8;

create table windows_10
(
    id int not null primary key auto_increment,
    activation_code varchar(100) not null,
    version varchar(100),
    created_at datetime default current_timestamp
) engine InnoDB;
desc windows_10;
drop table windows_10;

create table windows_11
(
    id int not null primary key auto_increment,
    activation_code varchar(100) not null,
    version varchar(100),
    created_at datetime default current_timestamp
) engine InnoDB;
desc windows_11;
drop table windows_11;