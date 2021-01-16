
CREATE table user(
	id varchar(36) primary key not null,
	name varchar(100) not null,
	phone varchar(20) not null,
	email varchar(100) not null,
	wallet_id varchar(36) not null
);