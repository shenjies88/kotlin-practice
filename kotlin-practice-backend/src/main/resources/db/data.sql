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

create table admin_user
(
    id      int unsigned auto_increment primary key comment '主键',
    account varchar(20) not null comment '账号',
    pwd     varchar(20) not null comment '密码'
) comment '管理员';
insert into admin_user (account, pwd)
values ('shenjies88', '123456');

create table goods
(
    id      int unsigned auto_increment primary key comment '主键',
    user_id int unsigned not null comment '用户id',
    name    varchar(20)  not null comment '商品名称'
) comment '商品';
insert into goods (user_id, name)
values (1, '商品1'),
       (1, '商品2'),
       (1, '商品3'),
       (1, '商品4'),
       (1, '商品5'),
       (1, '商品6'),
       (1, '商品7'),
       (1, '商品8'),
       (1, '商品9'),
       (1, '商品10'),
       (1, '商品11'),
       (1, '商品12'),
       (1, '商品13'),
       (1, '商品14'),
       (1, '商品15'),
       (1, '商品16'),
       (1, '商品17'),
       (1, '商品18'),
       (1, '商品19'),
       (1, '商品20'),
       (1, '商品21'),
       (1, '商品22'),
       (1, '商品23'),
       (1, '商品24'),
       (1, '商品25'),
       (1, '商品26'),
       (1, '商品27'),
       (1, '商品28'),
       (1, '商品29');

