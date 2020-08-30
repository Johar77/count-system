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
    private ResultCode code = ResultCode.SUCCESS;

    private String message;

    public boolean isSuccess(){
        return code == ResultCode.SUCCESS;
    }

    public BaseResponse(){
        this.setCode(ResultCode.SUCCESS);
        this.setMessage(ResultCode.SUCCESS.getMsg());
    }

    public BaseResponse(ResultCode resultCode){
        this.setCode(resultCode);
        this.setMessage(resultCode.getMsg());
    }

    public BaseResponse(ResultCode resultCode, String message){
        this.setCode(resultCode);
        this.setMessage(message);
    }
}
