package com.johar.commonlib.error;

import com.johar.commonlib.api.BaseResponse;
import com.johar.commonlib.api.ResultCode;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * @ClassName: GlobalExceptionTranslator
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-08-30 11:31
 * @Since: 1.0.0
 */
@RestControllerAdvice
public class GlobalExceptionTranslator {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionTranslator.class);

    @ExceptionHandler(ConversionFailedException.class)
    public BaseResponse handleError(ConversionFailedException e){
        logger.error("Conversion Failed:", e);
        String message = String.format("%s:%s -> %s", e.getSourceType(), e.getTargetType(), e.getMessage());
        return BaseResponse
                .builder()
                .message(message)
                .code(ResultCode.NOT_FOUND.getCode())
                .build();
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public BaseResponse handlerError(MissingServletRequestParameterException e){
        logger.error("Missing Servlet Request", e);
        String message = String.format("Missing Servlet Request: %s", e.getParameterName());
        return BaseResponse
                .builder()
                .message(message)
                .code(ResultCode.PARAM_MISS.getCode())
                .build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public BaseResponse handleError(MethodArgumentTypeMismatchException e){
        logger.error("Method Argument Type Mismatch", e);
        String message = String.format("Method Argument Type Mismatch: %s", e.getName());
        return  BaseResponse
                .builder()
                .message(message)
                .code(ResultCode.PARAM_TYPE_ERROR.getCode())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse handleError(MethodArgumentNotValidException e){
        logger.error("Method Argument Not Valid", e);
        BindingResult bindingResult = e.getBindingResult();
        FieldError error = bindingResult.getFieldError();
        String message = String.format("Method Argument Not Valid: %s:%s", error.getField(), error.getDefaultMessage());
        return BaseResponse
                .builder()
                .message(message)
                .code(ResultCode.PARAM_VALID_ERROR.getCode())
                .build();
    }

    @ExceptionHandler(BindException.class)
    public BaseResponse handleError(BindException e) {
        logger.warn("Bind Exception", e);
        FieldError error = e.getFieldError();
        String message = String.format("%s:%s", error.getField(), error.getDefaultMessage());
        return BaseResponse
                .builder()
                .code(ResultCode.PARAM_BIND_ERROR.getCode())
                .message(message)
                .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public BaseResponse handleError(ConstraintViolationException e) {
        logger.warn("Constraint Violation", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String path = ((PathImpl) violation.getPropertyPath()).getLeafNode().getName();
        String message = String.format("%s:%s", path, violation.getMessage());
        return BaseResponse
                .builder()
                .code(ResultCode.PARAM_VALID_ERROR.getCode())
                .message(message)
                .build();
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public BaseResponse handleError(NoHandlerFoundException e) {
        logger.error("404 Not Found", e);
        return BaseResponse
                .builder()
                .code(ResultCode.NOT_FOUND.getCode())
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public BaseResponse handleError(HttpMessageNotReadableException e) {
        logger.error("Message Not Readable", e);
        return BaseResponse
                .builder()
                .code(ResultCode.MSG_NOT_READABLE.getCode())
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public BaseResponse handleError(HttpRequestMethodNotSupportedException e) {
        logger.error("Request Method Not Supported", e);
        return BaseResponse
                .builder()
                .code(ResultCode.METHOD_NOT_SUPPORTED.getCode())
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public BaseResponse handleError(HttpMediaTypeNotSupportedException e) {
        logger.error("Media Type Not Supported", e);
        return BaseResponse
                .builder()
                .code(ResultCode.MEDIA_TYPE_NOT_SUPPORTED.getCode())
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(ServiceException.class)
    public BaseResponse handleError(ServiceException e) {
        logger.error("Service Exception", e);
        return BaseResponse
                .builder()
                .code(e.getResultCode().getCode())
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(Throwable.class)
    public BaseResponse handleError(Throwable e) {
        logger.error("Internal Server Error", e);
        return BaseResponse
                .builder()
                .code(ResultCode.INTERNAL_SERVER_ERROR.getCode())
                .message(e.getMessage())
                .build();
    }
}
