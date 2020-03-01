package com.incarcloud.match.data;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 请求响应数据对象
 *
 * @param <T> 支持Object泛型
 * @author Aaric, created on 2020-02-25T11:42.
 * @version 0.2.0-SNAPSHOT
 */
@Getter
@Setter
@ToString(callSuper = true)
@Accessors(chain = true)
@NoArgsConstructor
public final class ResponseData<T extends Object> implements ResponseSuccessState, ResponseFailureState {

    @ApiModelProperty(value = "状态码(0000-请求成功，其它异常)")
    private String code;

    @ApiModelProperty(value = "附加信息")
    private String message;

    @ApiModelProperty(value = "数据结果")
    private T data;

    private ResponseData(String code, T data) {
        this.code = code;
        this.data = data;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Object> ResponseData<T> ok() {
        return ok(null);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Object> ResponseData<T> ok(T data) {
        return new ResponseData(DEFAULT_SUCCESS, data);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Object> ResponseData<T> error(String errorCode) {
        return error(errorCode, null);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Object> ResponseData<T> error(String errorCode, T data) {
        return new ResponseData(errorCode, data);
    }

    @SuppressWarnings("unchecked")
    public <T extends Object> ResponseData<T> extraMsg(String msg) {
        this.setMessage(msg);
        return (ResponseData<T>) this;
    }
}
