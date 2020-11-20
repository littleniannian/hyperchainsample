package org.example.constant;

public enum ResConstants {

    OK(20000,"请求成功,老铁"),

    ACCOUNT_NOT_EXIST(40004,"用户不存在!"),

    ACCOUNT_HAS_EXIST(40005,"用户已存在"),

    BALANCE_NOT_ENOUGH(40006,"余额不足");

    private Integer code;
    private String message;

    ResConstants() {
    }

    ResConstants(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
