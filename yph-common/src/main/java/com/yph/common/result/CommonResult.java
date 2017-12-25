package com.yph.common.result;

import com.yph.common.constant.ResultConstant;
import lombok.Data;

import java.io.Serializable;

/**
 * 请求响应返回类
 *
 * @author : Administrator Hzhan
 * @create ：2017/12/22
 **/
@Data
public class CommonResult implements Serializable {

    private static final long serialVersionUID = -1624576716511065417L;

    /**
     *  返回状态码
     */
    private int code;

    /**
     *  返回信息
     */
    private String msg;

    /**
     *  返回时的数据
     */
    private Object data;


    public CommonResult() {
    }

    public CommonResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CommonResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     *   成功返回 无数据
     * @return
     */
    public static CommonResult SUCCESS() {
        return new CommonResult(ResultConstant.SUCCESS, ResultConstant.SUCCESS_MSG);
    }

    /**
     *  成功返回 带有数据
     * @param data
     * @return
     */
    public static CommonResult SUCCESS(Object data) {
        return new CommonResult(ResultConstant.SUCCESS, ResultConstant.SUCCESS_MSG,data);
    }

    /**
     *  操作失败
     * @return
     */
    public static CommonResult ERROR(){
        return new CommonResult(ResultConstant.ERROR,ResultConstant.ERROR_MSG);
    }

    /**
     *  操作失败
     * @return
     */
    public static CommonResult ERROR(String msg){
        return new CommonResult(ResultConstant.ERROR,msg);
    }

}
