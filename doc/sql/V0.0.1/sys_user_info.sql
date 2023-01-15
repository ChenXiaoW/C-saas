-- auto-generated definition
create table sys_user_info
(
    id          varchar(32)                                   not null comment '主键ID'
        primary key,
    user_name   varchar(128)                                  not null comment '用户姓名',
    gender      char(16)                                      null comment '性别',
    avatar      varchar(255)                                  null comment '头像',
    mobile      varchar(64)                                   null comment '手机号',
    state       tinyint(1) unsigned default 1                 not null comment '状态：0-禁用，1-启用',
    account     varchar(128)                                  not null comment '账号',
    password    varchar(128)                                  not null comment '密码',
    salt        varchar(64)                                   not null comment '随机盐',
    create_by   varchar(32)                                   null comment '创建人ID',
    create_time datetime            default CURRENT_TIMESTAMP null comment '创建时间',
    update_by   varchar(32)                                   null comment '更新人ID',
    update_time datetime            default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    del_flag    tinyint(1)          default 0                 not null comment '删除标记：0-否，1-是'
)
    comment '用户信息表';

