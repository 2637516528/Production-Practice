package com.xiaotang.enums;

public enum BorrowStatusEnum {
    BEING_BORROWED("已借书", "已借书"),

    AUDIT("待审核", "待审核"),

    RETURNED_BOOKS("已还书", "已还书");

    private final String code;
    private final String describe;

    BorrowStatusEnum(String code, String describe) {
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
