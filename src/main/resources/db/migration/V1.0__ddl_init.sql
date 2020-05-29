create table user (
   id bigint not null auto_increment,
	creation datetime not null,
	creation_username varchar(50) not null,
	last_updated datetime not null,
	last_updated_username varchar(50) not null,
	status bit not null,
	password varchar(100) not null,
	username varchar(50) not null,
	primary key (id)
) engine=InnoDB;

