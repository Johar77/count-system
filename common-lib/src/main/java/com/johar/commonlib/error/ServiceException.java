package com.johar.commonlib.error;

import com.johar.commonlib.api.ResultCode;
import lombok.Getter;

/**
 * @ClassName: ServiceException
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-08-30 11:30
 * @Since: 1.0.0
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 2359767895161832954L;

    @Getter
    private final ResultCode resultCode;

    public ServiceException(String messsage){
        super(messsage);
        this.resultCode = ResultCode.FAILURE;
    }

    public ServiceException(ResultCode resultCode){
        super(resultCode.getMsg());
        this.resultCode = resultCode;
    }

    public ServiceException(ResultCode resultCode, String msg){
        super(msg);
        this.resultCode = resultCode;
    }

    public ServiceException(ResultCode resultCode, Throwable cause){
        super(cause);
        this.resultCode = resultCode;
    }

    public ServiceException(String msg, Throwable cause){
        super(msg, cause);
        this.resultCode = ResultCode.FAILURE;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

    public Throwable doFillInStackTrace() {
        return super.fillInStackTrace();
    }
}
