create table users
(
   id varchar(255) PRIMARY KEY
);

create table petitions
(
   id integer PRIMARY KEY AUTO_INCREMENT,
   name varchar(255) not null,
   description varchar(max) not null
);

create table votes
(
   id varchar(255) not null,
   petitionId integer not null,
   userId varchar(255) not null,
   votedAt timestamp not null
   );
