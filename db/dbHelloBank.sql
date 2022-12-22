CREATE DATABASE hellobank;

USE hellobank;
drop table bank_transaction;
drop table bank_account;
drop table bank_client;
drop table bank_user;

CREATE TABLE bank_user(
id_admin int not null UNIQUE,
name_admin varchar(50) not null,
lastName_admin varchar(50) not null,
password_admin VARCHAR(20) not null,
primary key (id_admin)
);

CREATE TABLE bank_client(
id int auto_increment,
id_client int not null UNIQUE,
idType_client varchar(15) not null,
name_client varchar(50) not null,
lastName_client varchar(50) not null,
email_client varchar(60) not null,
birthDate_client date not null,
creationDate_client date not null,
modificationDate_client timestamp,
id_admin int not null,
primary key (id),
foreign key (id_admin) references bank_user(id_admin)
);

CREATE TABLE bank_account(
id_account int  primary key auto_increment,
type_account varchar(20),
number_account int,
state_account varchar(10),
balance_account float,
availableBalance_account float,
creationDate_account date,
modificactionDate_account timestamp,
id_admin int,
id int,
foreign key (id_admin) references bank_user(id_admin),
foreign key (id) references bank_client(id)
);

CREATE TABLE bank_transaction(
id_transaction int primary key,
type_transaction varchar(50),
dateMovement_transaction timestamp,
description_transaction varchar(100),
value_transaction float,
typeMovement_transaction varchar(20),
id_account int,
foreign key(id_account) references bank_account(id_account)
);

select * from bank_user;

INSERT INTO bank_user VALUES(1, 'Harry', 'Kane', '012345');
INSERT INTO bank_user VALUES(2, 'Edd', 'Foster', '101920');
INSERT INTO bank_user VALUES(3, 'Fedrerick', 'Taylor', '202214');
INSERT INTO bank_user VALUES(4, 'Jason', 'Ruby', '202214');


SELECT * FROM bank_user b WHERE b.id_admin = 4 AND b.password_admin = 09876;
SELECT * FROM bank_account;