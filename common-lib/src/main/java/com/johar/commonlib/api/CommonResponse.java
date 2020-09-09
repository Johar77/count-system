package com.johar.commonlib.api;

import lombok.*;

/**
 * @ClassName: CommonResponse
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-08-30 11:01
 * @Since: 1.0.0
 */
@Data
@ToString(callSuper = true)
public class CommonResponse<T> extends BaseResponse {
    private T data;

    public CommonResponse(T data){
        super(ResultCode.SUCCESS);
        this.setData(data);
    }

    public CommonResponse(ResultCode resultCode, T data){
        super(resultCode);
        setData(data);
    }

    public CommonResponse(ResultCode resultCode, String message, T data){
        super(resultCode.getCode(), message);
        setData(data);
    }
}

