DROP TABLE IF EXISTS t_award;
create table t_award
(
	id int auto_increment primary key,
	phase_id int not null comment '期数',
	name varchar(64) not null,
	`desc` varchar(256) null,
	count int default '0' not null,
	remain_count int default '0' not null,
	state INT NOT NULL DEFAULT 0,
	create_time DATETIME NOT NULL ,
	update_time DATETIME
) ENGINE =innodb, comment '奖品表';


DROP TABLE IF EXISTS t_phase;
create table t_phase
(
	id int auto_increment primary key,
	name varchar(64) not null,
	`desc` varchar(256) null,
	start_time DATETIME NOT NULL ,
	end_time DATETIME NOT NULL,
	state INT NOT NULL DEFAULT 0,
	create_time DATETIME NOT NULL ,
	update_time DATETIME
) ENGINE =innodb, comment '抽奖期数表';

DROP TABLE IF EXISTS t_record;
create table t_record
(
	id int auto_increment primary key,
	phase_id int not null comment '期数id',
	award_id int not null comment '奖品id',
	user_id int not null comment '用户id',
	award_name varchar(64) not null,
	`award_desc` varchar(256) null,
	state INT NOT NULL DEFAULT 0,
	create_time DATETIME NOT NULL,
	update_time DATETIME
) ENGINE =innodb, comment '中奖纪录';

DROP TABLE IF EXISTS t_user;
create table t_user
(
	id int auto_increment primary key,
	phone VARCHAR(11) not null comment '期数id',
	nick_name varchar(64) not null,
	password varchar(256) null,
	state INT NOT NULL DEFAULT 0,
	create_time DATETIME NOT NULL,
	update_time DATETIME
) ENGINE =innodb, comment '用户';