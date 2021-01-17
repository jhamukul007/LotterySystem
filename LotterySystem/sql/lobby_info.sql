create table lobby_info(
	id varchar(36) primary key not null,
	max_size int,
	total_person int,
	start_time datetime default null,
	end_time datetime default null
);