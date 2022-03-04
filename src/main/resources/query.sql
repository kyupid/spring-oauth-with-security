drop table if exists `user`;

create table user
(
    id      int auto_increment
        primary key,
    name    varchar(100) not null,
    email   varchar(255) not null,
    picture varchar(255) null,
    location varchar(50) not null,
    age     int(10)      not null,
    role    varchar(100) not null
) DEFAULT CHARSET = utf8;

drop table if exists `post`;

create table post
(
    id      int auto_increment
        primary key,
    title   varchar(255) not null,
    content text         not null,
    author  varchar(100) not null
) DEFAULT CHARSET = utf8;

insert into post(author, content, title)
values ('author', 'content', 'title');
