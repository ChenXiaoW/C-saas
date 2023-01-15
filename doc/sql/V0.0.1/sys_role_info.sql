create table sys_role_info
(
    id          varchar(32)                                   not null primary key default '主键ID',
    role_name   varchar(128)                                  not null comment '角色名称',
    role_code   varchar(128)                                  not null comment '角色CODE',
    remark      varchar(255)        default null comment '备注',
    state       tinyint(1) unsigned default 1                 not null comment '状态：0-禁用，1-启用',
    create_by   varchar(32)                                   null comment '创建人ID',
    create_time datetime            default CURRENT_TIMESTAMP null comment '创建时间',
    update_by   varchar(32)                                   null comment '更新人ID',
    update_time datetime            default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    del_flag    tinyint(1)          default 0                 not null comment '删除标记：0-否，1-是'
) comment '角色信息表';