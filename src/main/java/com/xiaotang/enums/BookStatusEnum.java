package com.xiaotang.enums;

public enum BookStatusEnum {

    ON_SHELF("在架", "在架"),

    BEING_BORROWED("被借阅中", "被借阅中");

    private final String code;
    private final String describe;

    BookStatusEnum(String code, String describe) {
        this.code = code;
        this.describe = describe;
    }

    public String getCode() {
        return code;
    }

    public String getDescribe() {
        return describe;
    }
}
