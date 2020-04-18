/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  not-sure
 * Created: Jul 6, 2019
 */

create database psprojekat1;

create table users(
  username varchar(200),
  password varchar(200) not null,
  firstname varchar(200) not null,
  lastname varchar(200) not null,
  PRIMARY KEY (username)
);

insert into users (username, password, firstname, lastname) values ('pera', 'pera123','Pera','Peric'), ('zika','zika123','Zika','Zikic'),('mika','mika123','Mika','Mikic'),('sima','sima123','Sima','Simic');

create table  tip(
    name varchar(200),
    PRIMARY KEY (name)
);

insert into tip (name) values ('1'), ('x'), ('2'), ('0-1'), ('2-3'), ('3+'), ('5+');

create table team(
    id varchar(200),
    name varchar(200) not null,
    primary key (id)
);

insert into team (id, name) values ('rma','Real Madrid'), ('bar','Barcelona'),('atl','Atletico Madrid'),('mil','Milan'),('juv','Juventus'),('int','Inter'),('manutd','Manchester United'),('ars','Arsenal'),('mancit','Manchester City'),('liv','Liverpool'),('fch','Chelsea'),('bay','Bayern Munich'),('ben','Benfica'),('por','Porto'),('psg','Paris Saint German'),('Lyon','Olimpic Lyon');

create table match(
    id  SERIAL PRIMARY KEY,
    starttime timestamp not null,
    scoreinserted boolean default false,
    goalshome integer,
    goalsaway integer,
    username varchar(200) references users(username) on update cascade,
    home varchar(200) references team(id),
    away varchar(200) references team(id)
);

create table odds(
    id integer references match(id),
    name varchar(200) references tip(name),
    value DECIMAL not null check ( value >= 1 ),
    primary key (id,name)
);

create table tickets(
    id  SERIAL PRIMARY KEY,
    paid decimal not null check ( paid > 0 ),
    totalodds decimal not null check ( totalodds >= 1 ),
    win decimal not null check ( win > 0 ),
    timeofpayment timestamp not null,
    status varchar(200) not null,
    username varchar(200) references users(username) on update cascade
);

create table has(
    matchid integer,
    name varchar(200),
    ticketid integer references tickets(id) on delete cascade on update cascade,
    FOREIGN KEY (matchid, name) references odds(id, name),
    primary key (matchid, name, ticketid)
);

commit;
