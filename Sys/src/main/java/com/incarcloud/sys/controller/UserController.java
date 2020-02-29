package com.incarcloud.sys.controller;


import com.incarcloud.sys.entity.User;
import com.incarcloud.sys.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户基础信息表 前端控制器
 * </p>
 *
 * @author bzheng
 * @since 2019-11-28
 */
@RestController
@Api(value = "用户管理 Client Restful API ", description = "用户管理 Client Restful API ", protocols = "application/json")
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;


    /**
     * 查询用户信息管理分页
     *
     * @return
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户信息管理分页", notes = "查询用户信息管理分页")
    @ApiResponse(code = 200, message = "查询用户信息管理分页")
    public String page(
            @ApiParam(value = "用户名")
            @RequestParam(value = "username", required = false) String username,
            @ApiParam(value = "分页参数(页数)", example = "1")
            @RequestParam(value = "pageNum", required = false) Integer pageNum,
            @ApiParam(value = "分页参数(页大小)", example = "20")
            @RequestParam(value = "pageSize", required = false) Integer pageSize) {

        return userService.test(username);
    }

    /**
     * 查询用户信息管理分页
     *
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "新增用户信息管理", notes = "新增用户信息管理")
    @ApiResponse(code = 200, message = "新增用户信息管理")
    public String save(@RequestBody User user) {
        userService.saveUser(user);
        return "success";
    }
}
