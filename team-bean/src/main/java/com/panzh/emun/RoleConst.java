package com.panzh.emun;

//角色常量
public enum RoleConst {

    //数字表示等级
    ROLE_TYPE_TOMADMIN(2),
    ROLE_TYPE_ADMIN(1),
    ROLE_TYPE_USER(0);

    private Integer value;

    private RoleConst(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}
