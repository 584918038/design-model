create table t_city
(
    id          int unsigned auto_increment comment '主键'
        primary key,
    city        varchar(200) not null comment '城市',
    province_id int unsigned not null comment '省份id'
)
    comment '城市表';


-- auto-generated definition
create table t_level
(
    id       int unsigned auto_increment comment '主键'
        primary key,
    level    varchar(200)            not null comment '等级',
    discount decimal(10, 2) unsigned not null comment '折扣'
)
    comment '会员等级表';

