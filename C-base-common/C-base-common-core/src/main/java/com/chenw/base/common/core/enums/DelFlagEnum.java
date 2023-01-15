package com.chenw.base.common.core.enums;

/**
 * del_flag 删除标记枚举
 * @author chenw
 */

public enum DelFlagEnum {
    /**
     * 否
     */
    NO(false),
    /**
     * 是
     */
    YES(true);

    private Boolean delFlag;

    public Boolean getDelFlag() {
        return delFlag;
    }

    DelFlagEnum(Boolean delFlag){
        this.delFlag = delFlag;
    }
}
