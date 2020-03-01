package com.incarcloud.match.data;

/**
 * 请求响应数据失败状态码（公共部分）
 *
 * @author Aaric, created on 2020-02-25T11:44.
 * @version 0.2.0-SNAPSHOT
 */
public interface ResponseFailureState {

    /**
     * 0001 - 0020: 定义系统错误吗
     */
    // 未知错误
    String ERROR_0001 = "0001";

    // 非法请求
    String ERROR_0002 = "0002";

    // 系统繁忙，请稍候重试
    String ERROR_0003 = "0003";
}
