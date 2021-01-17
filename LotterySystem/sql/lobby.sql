create table lobby(
	id varchar(36) primary key not null,
	name varchar(100) default null,
	active bit(1)
);