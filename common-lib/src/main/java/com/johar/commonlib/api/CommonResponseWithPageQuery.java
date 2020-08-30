package com.johar.commonlib.api;

import lombok.Data;
import lombok.ToString;

/**
 * @ClassName: CommonResponseWithPageQuery
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-08-30 11:19
 * @Since: 1.0.0
 */
@Data
@ToString(callSuper = true)
public class CommonResponseWithPageQuery<T> extends CommonResponse<T> {
    private long pageSize;
    private long totalNum;
    private long currentPage;

    public CommonResponseWithPageQuery(T data){
        super(data);
        setPageParam(0, 0, 0);
    }

    public CommonResponseWithPageQuery(T data, long pageSize, long totalNum, long currentPage){
        super(data);
        setPageParam(pageSize, totalNum, currentPage);
    }

    public CommonResponseWithPageQuery(T data, ResultCode resultCode, long pageSize, long totalNum, long currentPage){
        super(resultCode, data);
        setPageParam(pageSize, totalNum, currentPage);
    }

    public CommonResponseWithPageQuery(T data, ResultCode resultCode, String message, long pageSize, long totalNum, long currentPage){
        super(resultCode, message, data);
        setPageParam(pageSize, totalNum, currentPage);
    }

    private void setPageParam(long pageSize, long totalNum, long currentPage){
        setTotalNum(totalNum);
        setPageSize(pageSize);
        setCurrentPage(currentPage);
    }
}
