package com.whitebear.digitalfarmbackend.response;

import lombok.Data;

@Data
public class ResponseResult<T> {
    private int code;
    private String message;
    private T data;

    public ResponseResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(200, "Success", data);
    }

    public static <T> ResponseResult<T> success() {
        return new ResponseResult<>(200, "Success", null);
    }

    public static <T> ResponseResult<T> fail(String message) {
        return new ResponseResult<>(500, message, null);
    }

    public static <T> ResponseResult<T> fail(int code, String message) {
        return new ResponseResult<>(code, message, null);
    }
} 