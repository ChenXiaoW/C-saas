package com.chenw.base.common.core.enums;

/**
 * state 枚举
 * @author chenw
 */

public enum StateEnum {
    /**
     * 禁用
     */
    DISABLE(false),
    /**
     * 启用
     */
    ENABLE(true);

    private Boolean state;

    public Boolean getState() {
        return state;
    }

    StateEnum (Boolean state){
        this.state = state;
    }
}
