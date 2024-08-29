package com.licole.common.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.licole.common.exception.enums.GlobalErrorCodeConstants;
import lombok.Data;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Objects;

/**
 * 通用返回
 *
 * @param <T> 数据泛型
 */
@Data
public class CommonResult<T> implements Serializable {

    /**
     * 错误编码
     */
    private Integer code;

    /**
     * 返回的数据
     */
    private T data;

    /**
     * 错误提示
     */
    private String msg;

    /**
     * 成功code
     */
    private static final Integer SUCCESS_CODE = 0;

    private static final String SUCCESS_MSG = "success";

    /**
     * 失败code
     */
    private static final Integer FAIL_CODE = -1;

    private static final String FAIL_MSG = "fail";


    public static <T> CommonResult<T> error(CommonResult<?> result) {
        return error(result.getCode(), result.getMsg());
    }

    public static <T> CommonResult<T> error(Integer code, String message) {
        Assert.isTrue(!(GlobalErrorCodeConstants.SUCCESS.getCode().equals(code)), "code 必须是错误的！");
        CommonResult<T> result = new CommonResult<>();
        result.code = code;
        result.msg = message;
        return result;
    }

    public static <T> CommonResult<T> error(GlobalErrorCodeConstants errorCode) {
        return error(errorCode.getCode(), errorCode.getMsg());
    }

    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> result = new CommonResult<>();
        result.code = SUCCESS_CODE;
        result.data = data;
        result.msg = "";
        return result;
    }

    public static boolean isSuccess(Integer code) {
        return Objects.equals(code, GlobalErrorCodeConstants.SUCCESS.getCode());
    }

    @JsonIgnore // 避免 jackson 序列化
    public boolean isSuccess() {
        return isSuccess(code);
    }

    @JsonIgnore // 避免 jackson 序列化
    public boolean isError() {
        return !isSuccess();
    }


}
