create schema if not exists academyTest collate latin1_swedish_ci;

create table if not exists auth_user
(
	id int(64) not null,
	login varchar(255) not null,
	password varchar(255) not null,
	role varchar(63) not null,
	constraint id
		unique (id, role)
);

create table if not exists curs
(
	id int(64) auto_increment
		primary key,
	name varchar(255) null,
	start_date date null,
	end_date date null
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

create table if not exists gradebook
(
	id int(64) auto_increment
		primary key,
	student_id int(64) not null,
	curs_id int(64) not null,
	grade int(64) default 0 not null,
	constraint gradebook_curs_id_fk
		foreign key (curs_id) references curs (id),
	constraint gradebook_student_id_fk
		foreign key (student_id) references student (id)
);

create table if not exists student_curs
(
	student_id int null,
	curs_id int null,
	constraint student_curs_ibfk_1
		foreign key (student_id) references student (id),
	constraint student_curs_ibfk_2
		foreign key (curs_id) references curs (id)
);

create index curs_id
	on student_curs (curs_id);

create index student_id
	on student_curs (student_id);

create table if not exists teacher
(
	id int(64) auto_increment
		primary key,
	name varchar(255) not null,
	second_name varchar(255) not null,
	email varchar(63) not null,
	curs_id int(64) null,
	constraint teacher_curs_id_fk
		foreign key (curs_id) references curs (id)
);

