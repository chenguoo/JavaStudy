package com.cheney.study.enums;

/**
 * 是否可用枚举类
 * @author Cheney
 * @date 2020-11-03 19:56
 */
public enum EnabledEnum implements Enumerator{
    /**
     * Disable status enum.
     */
    DISABLE(0, "不可用"),
    /**
     * Enable status enum.
     */
    ENABLE(1, "可用");

    private final int value;
    private final String description;

    EnabledEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public int value() {
        return this.value;
    }

    @Override
    public String description() {
        return this.description;
    }
}
