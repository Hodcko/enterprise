create schema if not exists academy collate latin1_swedish_ci;

create table if not exists auth_user
(
	id int(64) not null,
	login varchar(255) not null,
	password varchar(255) not null,
	role varchar(63) not null
);

create table if not exists curs
(
	id int(64) auto_increment
		primary key,
	name varchar(255) not null,
	start_date date null,
	end_date date null
);

create table if not exists gradebook
(
	id int(64) auto_increment
		primary key,
	student_id int(64) not null,
	curs_id int(64) not null,
	grade int(64) default 0 not null
);

create table if not exists student
(
	id int(64) auto_increment
		primary key,
	name varchar(255) not null,
	second_name varchar(255) not null,
	email varchar(255) not null,
	age int(64) not null
);

create table if not exists student_curs
(
	student_id int null,
	curs_id int null
);

create table if not exists teacher
(
	id int(64) auto_increment
		primary key,
	name varchar(255) not null,
	second_name varchar(255) not null,
	email varchar(63) not null,
	curs_id int(64) null
);


