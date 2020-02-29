package com.incarcloud.sys.controller;

import com.incarcloud.sys.entity.Point;
import com.incarcloud.sys.service.IPositionDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by bzheng on 2020/2/29.
 */
@RestController
@Api(value = "位置数据查询接口 Client Restful API ", description = "位置数据查询接口 Client Restful API ", protocols = "application/json")
@RequestMapping("/api/position")
public class PositionDataController {

    @Autowired
    private IPositionDataService positionDataService;
    /**
     * 统计数据总行数
     *
     * @return
     */
    @GetMapping("/getCount")
    @ApiOperation(value = "统计数据总行数", notes = "统计数据总行数")
    @ApiResponse(code = 200, message = "统计数据总行数")
    public Long getCount() {
        return positionDataService.getCount();
    }


    /**
     * 查询指定车辆指定日期的行驶总里程
     *
     * @param deviceCode
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/getTotalMileage")
    @ApiOperation(value = "查询指定车辆指定日期的行驶总里程", notes = "查询指定车辆指定日期的行驶总里程")
    @ApiResponse(code = 200, message = "查询指定车辆指定日期的行驶总里程")
    public Double getTotalMileage(@ApiParam(value = "设备号", required = true)
                                  @RequestParam(value = "deviceCode") String deviceCode,
                                  @ApiParam(value = "采集开始时间", required = true)
                                  @RequestParam(value = "startTime") Date startTime,
                                  @ApiParam(value = "采集结束时间", required = true)
                                  @RequestParam(value = "endTime") Date endTime) {
        return positionDataService.getTotalMileage(deviceCode, startTime, endTime);
    }


    /**
     * 查询指定车辆指定时间段(不短于72小时)的轨迹
     *
     * @param deviceCode
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/query")
    @ApiOperation(value = "查询指定车辆指定时间段(不短于72小时)的轨迹", notes = "查询指定车辆指定时间段(不短于72小时)的轨迹")
    @ApiResponse(code = 200, message = "查询指定车辆指定时间段(不短于72小时)的轨迹")
    public List<Point> query(@ApiParam(value = "设备号", required = true)
                                  @RequestParam(value = "deviceCode") String deviceCode,
                                  @ApiParam(value = "采集开始时间", required = true)
                                  @RequestParam(value = "startTime") Date startTime,
                                  @ApiParam(value = "采集结束时间", required = true)
                                  @RequestParam(value = "endTime") Date endTime) {
        return positionDataService.query(deviceCode, startTime, endTime);
    }
}
