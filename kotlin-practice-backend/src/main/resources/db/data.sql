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
values ('18178324804', '123456', 'shenjies88');