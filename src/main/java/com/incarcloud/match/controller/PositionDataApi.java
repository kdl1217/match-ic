package com.incarcloud.match.controller;

import com.incarcloud.match.data.ResponseData;
import com.incarcloud.match.entity.DeviceInfo;
import com.incarcloud.match.entity.Point;
import com.incarcloud.match.entity.PositionData;
import com.incarcloud.match.mongoDB.page.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * 位置数据服务API
 *
 * @author bzheng, created on 2020-03-01T15:21.
 * @version 0.2.0-SNAPSHOT
 */
@Api(tags = "位置数据查询接口 Client Restful API ", protocols = "application/json")
public interface PositionDataApi {
    /**
     * 统计数据总行数
     *
     * @return
     */
    @ApiOperation(value = "统计数据总行数", notes = "统计数据总行数")
    @ApiResponse(code = 200, message = "统计数据总行数")
    ResponseData<Long> getCount();

    /**
     * 查询指定车辆指定日期的行驶总里程
     *
     * @return
     */
    @ApiOperation(value = "查询指定车辆指定日期的行驶总里程", notes = "查询指定车辆指定日期的行驶总里程")
    @ApiResponse(code = 200, message = "查询指定车辆指定日期的行驶总里程")
    ResponseData<Double> getTotalMileage(@ApiParam(value = "设备号", required = true)
                                         @RequestParam(value = "deviceCode") String deviceCode,
                                         @ApiParam(value = "采集开始时间", required = true)
                                         @RequestParam(value = "startTime") Date startTime,
                                         @ApiParam(value = "采集结束时间", required = true)
                                         @RequestParam(value = "endTime") Date endTime);


    /**
     * 查询指定车辆指定时间段(不短于72小时)的轨迹
     *
     * @return
     */
    @ApiOperation(value = "查询指定车辆指定时间段(不短于72小时)的轨迹", notes = "查询指定车辆指定时间段(不短于72小时)的轨迹")
    @ApiResponse(code = 200, message = "查询指定车辆指定时间段(不短于72小时)的轨迹")
    ResponseData<List<Point>> query(@ApiParam(value = "设备号", required = true)
                                    @RequestParam(value = "deviceCode") String deviceCode,
                                    @ApiParam(value = "采集开始时间", required = true)
                                    @RequestParam(value = "startTime") Date startTime,
                                    @ApiParam(value = "采集结束时间", required = true)
                                    @RequestParam(value = "endTime") Date endTime);

    /**
     * 分页查询
     *
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @ApiResponse(code = 200, message = "分页查询")
    ResponseData<PageResult<PositionData>> page(@ApiParam(value = "设备号", required = true)
                                                @RequestParam(value = "deviceCode") String deviceCode,
                                                @ApiParam(value = "采集开始时间", required = true)
                                                @RequestParam(value = "startTime") Date startTime,
                                                @ApiParam(value = "采集结束时间", required = true)
                                                @RequestParam(value = "endTime") Date endTime,
                                                @ApiParam(value = "分页参数(页数)", required = true, example = "1")
                                                @RequestParam(value = "pageNum") Integer pageNum,
                                                @ApiParam(value = "分页参数(页大小)", required = true, example = "20")
                                                @RequestParam(value = "pageSize") Integer pageSize,
                                                @ApiParam(value = "最后一条记录的id", required = false)
                                                @RequestParam(value = "lastId") String lastId);


    @ApiOperation(value = "查询指定车辆指定日期的行驶总里程", notes = "查询指定车辆指定日期的行驶总里程")
    @ApiResponse(code = 200, message = "查询指定车辆指定日期的行驶总里程")
    ResponseData<DeviceInfo> totalMileage(@ApiParam(value = "设备号", required = true)
                                          @RequestParam(value = "deviceCode") String deviceCode,
                                          @ApiParam(value = "指定日期", required = true)
                                          @RequestParam(value = "specified") Date specified);

}
