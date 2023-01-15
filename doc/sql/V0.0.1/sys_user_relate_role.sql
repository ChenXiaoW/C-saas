create table sys_user_relate_role
(
    id          varchar(32)                          not null primary key default '主键ID',
    user_id     varchar(32)                          not null comment '用户ID',
    role_id     varchar(32)                          not null comment '角色ID',
    create_by   varchar(32)                          null comment '创建人ID',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    update_by   varchar(32)                          null comment '更新人ID',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    del_flag    tinyint(1) default 0                 not null comment '删除标记：0-否，1-是'
) comment '用户角色关联表';