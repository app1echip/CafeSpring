create schema cafe collate utf8mb4_0900_ai_ci;

create table category
(
	id char(36) not null
		primary key,
	name varchar(64) null,
	constraint category_name_uindex
		unique (name)
);

create table dish
(
	id char(36) not null
		primary key,
	name varchar(64) null,
	category char(36) not null,
	price decimal(13,2) null,
	stock int null,
	sales int null,
	description text null,
	constraint dish_category_id_fk
		foreign key (category) references category (id)
			on update cascade
);

create table user
(
	id char(36) not null
		primary key,
	username varchar(64) not null,
	email varchar(64) null,
	password varchar(64) not null,
	phone varchar(64) null,
	constraint user_username_uindex
		unique (username)
);

create table `order`
(
	id char(36) not null
		primary key,
	date datetime null,
	no int null,
	user char(36) not null,
	constraint order_user_id_fk
		foreign key (user) references user (id)
			on update cascade
);

create table order_dish
(
	`order` char(36) not null,
	dish char(36) not null,
	qty int not null,
	primary key (`order`, dish),
	constraint order_dish_dish_id_fk
		foreign key (dish) references dish (id)
			on update cascade,
	constraint order_dish_order_id_fk
		foreign key (`order`) references `order` (id)
			on update cascade
);


