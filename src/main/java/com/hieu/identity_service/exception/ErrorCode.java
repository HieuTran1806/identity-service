package com.hieu.identity_service.exception;

public enum ErrorCode {
    USER_EXISTED(1001, "User existed"),
    USER_NOT_FOUND(102, "User not found"),
    UNCATEGORIZED_EXCEPTION(9999, "UNCATEGORIZE_EXCEPTION"),
    USER_INVALID(103, "Username must be at least 3 characters"),
    INVALID_KEY(1002, "Invalid message key"),
    UNAUTHENTICATED(1003, "UnAuthenticated"),
    PASSWORD_INVALID(104, "Password must be at least 8 characters")

    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
