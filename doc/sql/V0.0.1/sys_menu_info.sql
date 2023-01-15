-- auto-generated definition
create table sys_menu_info
(
    id          varchar(32)         default '主键ID'          not null
        primary key,
    parent_id   varchar(32)                                   null comment '父级ID',
    scene       tinyint(1) unsigned                           not null comment '使用场景:0-平台，1-租户，2-小程序',
    menu_code   varchar(128)                                  not null comment '菜单CODE',
    menu_name   varchar(255)                                  not null comment '菜单名称',
    page_url    varchar(255)                                  null comment '页面路径',
    menu_type   tinyint(1) unsigned                           not null comment '菜单类型：0-主菜单,1-页面,2-按钮',
    menu_icon   varchar(255)                                  not null comment '菜单ICON',
    menu_order  int                 default 1000              null comment '菜单顺序',
    remark      varchar(255)                                  null comment '备注',
    state       tinyint(1) unsigned default 1                 not null comment '状态：0-禁用，1-启用',
    create_by   varchar(32)                                   null comment '创建人ID',
    create_time datetime            default CURRENT_TIMESTAMP null comment '创建时间',
    update_by   varchar(32)                                   null comment '更新人ID',
    update_time datetime            default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    del_flag    tinyint(1)          default 0                 not null comment '删除标记：0-否，1-是'
)
    comment '菜单信息表';

