create schema if not exists academy collate latin1_swedish_ci;

create table if not exists auth_user
(
    id int(64) not null,
    login varchar(255) not null,
    password varchar(255) not null,
    Role varchar(63) not null
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
    student_id int(64) not null,
    grade int(64) default 0 not null,
    constraint gradebook_student_id_uindex
        unique (student_id)
);

alter table gradebook
    add primary key (student_id);

create table if not exists student
(
    id int(64) auto_increment
        primary key,
    name varchar(255) not null,
    second_name varchar(255) not null,
    email varchar(255) not null,
    age int(64) not null,
    curs_id int(64) null,
    constraint student_ibfk_1
        foreign key (curs_id) references curs (id)
);

create index curs_id
    on student (curs_id);

create table if not exists teacher
(
    id int(64) auto_increment
        primary key,
    name varchar(255) not null,
    second_name varchar(255) not null,
    email varchar(63) not null,
    curs_id int(64) null,
    constraint teacher_ibfk_1
        foreign key (curs_id) references curs (id)
);

create index curs_id
    on teacher (curs_id);

        <dependency>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>cobertura-maven-plugin</artifactId>
            <version>${cobertura-maven-plugin.version}</version>
            <exclusions>
            <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage</artifactId>
            </exclusion>
            </exclusions>
        </dependency>