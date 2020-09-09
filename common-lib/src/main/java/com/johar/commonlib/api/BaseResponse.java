package com.johar.commonlib.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: BaseResponse
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-08-30 10:56
 * @Since: 1.0.0
 */
@Data
@Builder
public class BaseResponse {
    @Builder.Default
    private int code = ResultCode.SUCCESS.getCode();

    @Builder.Default
    private String message = ResultCode.SUCCESS.getMsg();

    public boolean isSuccess(){
        return code == ResultCode.SUCCESS.getCode();
    }

    public static BaseResponse Ok(){
        return BaseResponse.builder().code(ResultCode.SUCCESS.getCode()).message(ResultCode.SUCCESS.getMsg()).build();
    }

    public BaseResponse(){
        this(ResultCode.SUCCESS);
    }

    public BaseResponse(ResultCode resultCode){
        this.setCode(resultCode.getCode());
        this.setMessage(resultCode.getMsg());
    }

    public BaseResponse(int resultCode, String message){
        this.setCode(resultCode);
        this.setMessage(message);
    }
}
