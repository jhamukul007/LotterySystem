
create table wallet(
	id varchar(36) primary key not null,
	wallet_name varchar(255) default null,
	amount BIGINT(19) default 0,
	user_id varchar(36) not null
);