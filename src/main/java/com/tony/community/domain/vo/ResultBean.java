package com.tony.community.domain.vo;

import com.tony.community.exception.CustomizeErrorCode;
import com.tony.community.exception.CustomizeException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultBean<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

    public static ResultBean errorOf(Integer code, String message) {
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(code);
        resultBean.setMessage(message);
        return resultBean;
    }

    public static ResultBean errorOf(CustomizeErrorCode errorCode) {
        return errorOf(errorCode.getCode(), errorCode.getMessage());
    }

    public static ResultBean errorOf(CustomizeException e) {
        return errorOf(e.getCode(), e.getMessage());
    }

    public static ResultBean okOf() {
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(200);
        resultBean.setMessage("请求成功");
        return resultBean;
    }

    public static <T> ResultBean okOf(T t) {
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(200);
        resultBean.setMessage("请求成功");
        resultBean.setData(t);
        return resultBean;
    }

}
