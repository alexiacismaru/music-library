drop table if exists songsonthealbum cascade;
drop table if exists albumsmadebyartist cascade;
drop table if exists song  cascade;
drop table if exists album  cascade;
drop table if exists artist cascade;
drop table if exists users cascade;

create table users
(
    id       serial primary key unique,
    username varchar not null,
    email    varchar not null,
    password varchar not null,
    userrole     varchar not null
);

create table artist
(
    id        serial primary key unique,
    name      varchar not null,
    profile    varchar not null,
    description varchar
);

create table album
(
    id            serial primary key unique,
    title         varchar not null,
    releasedate   date,
    cover         varchar,
    artistid      integer,
    constraint fk_artistid
        foreign key (artistid) references artist on delete cascade
);

create table song
(
    id       serial primary key unique,
    songname varchar not null,
    duration integer,
    genre    varchar,
    audio    varchar,
    albumid  integer,
    constraint fk_albumid foreign key (albumid) references album on delete cascade
);

create table songsonthealbum
(
    albumid integer,
    songid  integer,
    constraint fk_albumid foreign key (albumid) references album on delete cascade,
    constraint fk_songid foreign key (songid) references song  on delete cascade
);

create table albumsmadebyartist
(
    artistid integer,
    albumid  integer,
    constraint fk_artistid foreign key (artistid) references artist on delete cascade,
    constraint fk_albumid foreign key (albumid) references album on delete cascade
);
