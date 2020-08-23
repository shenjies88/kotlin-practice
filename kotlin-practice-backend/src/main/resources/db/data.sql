use kotlin_db;
create table user
(
    id       int unsigned auto_increment primary key comment '主键',
    account  varchar(20) not null comment '账号',
    pwd      varchar(20) not null comment '密码',
    nickname varchar(10) not null comment '昵称',
    icon     varchar(50) null default '' comment '头像'
) comment '用户';
insert into user (account, pwd, nickname)
values ('18178324804', '123456', 'shenjies88'),
       ('18178324805', '123456', 'shenjies05'),
       ('18178324806', '123456', 'shenjies06'),
       ('18178324807', '123456', 'shenjies07'),
       ('18178324808', '123456', 'shenjies08'),
       ('18178324809', '123456', 'shenjies09'),
       ('1817832480a', '123456', 'shenjies0a'),
       ('1817832480b', '123456', 'shenjies0b'),
       ('1817832480c', '123456', 'shenjies0c');

create table goods
(
    id      int unsigned auto_increment primary key comment '主键',
    user_id int unsigned not null comment '用户id',
    name    varchar(20)  not null comment '产品名称'
) comment '产品';
insert into goods (user_id, name)
values (1, '商品1号'),
       (1, '商品2号'),
       (1, '商品3号'),
       (1, '商品4号'),
       (1, '商品5号');
