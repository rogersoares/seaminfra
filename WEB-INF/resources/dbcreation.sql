CREATE SEQUENCE userinfo_seq start 1 increment 1 maxvalue 2147483647 minvalue 1  cache 1 cycle;
SELECT nextval ('userinfo_seq');

CREATE TABLE userinfo (
	id int not null,
	fullname character varying(30),
	username varchar(16),
	password character(16) not null,
	primary key(id)
);
insert into userinfo values (1, 'admin', 'admin', 'uga2buga');

CREATE TABLE profile (
	id int not null,
	description varchar(128),
	primary key(id)
);
insert into profile values (1, 'admin');

CREATE TABLE userinfo_profile (
	profile_id int not null references profile,
	userinfo_id int not null references userinfo,
	primary key(profile_id, userinfo_id)
);
insert into userinfo_profile values (1, 1);
