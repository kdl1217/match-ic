package com.incarcloud.match.controller;

import com.incarcloud.common.data.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 问候API接口
 *
 * @author Aaric, created on 2020-02-28T11:20.
 * @version 0.0.1-SNAPSHOT
 */
@Api(tags = "问候管理API")
public interface HelloApi {

    @ApiOperation(value = "问候")
    @GetMapping("/hproj/hello/sayHi")
    ResponseData<String> sayHi();
}
