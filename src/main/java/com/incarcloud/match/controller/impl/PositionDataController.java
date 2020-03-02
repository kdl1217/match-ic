package com.incarcloud.match.controller.impl;

import com.incarcloud.match.controller.PositionDataApi;
import com.incarcloud.match.data.ResponseData;
import com.incarcloud.match.entity.DeviceInfo;
import com.incarcloud.match.entity.Point;
import com.incarcloud.match.entity.PositionData;
import com.incarcloud.match.mongoDB.page.PageResult;
import com.incarcloud.match.service.IPositionDataService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * 位置数据服务实现
 *
 * @author bzheng, created on 2020-03-01T15:21.
 * @version 0.2.0-SNAPSHOT
 */
@RestController
@RequestMapping("/api/position")
public class PositionDataController implements PositionDataApi {

    @Autowired
    private IPositionDataService positionDataService;

    /**
     * 统计数据总行数
     *
     * @return
     */
    @GetMapping("/getCount")
    public ResponseData<Long> getCount() {
        return ResponseData.ok(positionDataService.getCount()).extraMsg("请求成功");
    }


    /**
     * 查询指定车辆指定日期的行驶总里程
     *
     * @return
     */
    @GetMapping("/getTotalMileage")
    public ResponseData<Double> getTotalMileage(@ApiParam(value = "设备号", required = true)
                                                @RequestParam(value = "deviceCode") String deviceCode,
                                                @ApiParam(value = "采集开始时间", required = true)
                                                @RequestParam(value = "startTime") Date startTime,
                                                @ApiParam(value = "采集结束时间", required = true)
                                                @RequestParam(value = "endTime") Date endTime) {
        return ResponseData.ok(positionDataService.getTotalMileage(deviceCode, startTime, endTime)).extraMsg("请求成功");
    }


    /**
     * 查询指定车辆指定时间段(不短于72小时)的轨迹
     *
     * @return
     */
    @GetMapping("/query")
    public ResponseData<List<Point>> query(@ApiParam(value = "设备号", required = true)
                                           @RequestParam(value = "deviceCode") String deviceCode,
                                           @ApiParam(value = "采集开始时间", required = true)
                                           @RequestParam(value = "startTime") Date startTime,
                                           @ApiParam(value = "采集结束时间", required = true)
                                           @RequestParam(value = "endTime") Date endTime) {
        return ResponseData.ok(positionDataService.query(deviceCode, startTime, endTime)).extraMsg("请求成功");
    }

    /**
     * 查询指定车辆指定时间段(不短于72小时)的轨迹
     *
     * @return
     */
    @GetMapping("/page")
    public ResponseData<PageResult<PositionData>> page(@ApiParam(value = "设备号")
                                                       @RequestParam(value = "deviceCode",required = false) String deviceCode,
                                                       @ApiParam(value = "采集开始时间", required = false)
                                                       @RequestParam(value = "startTime",required = false) Date startTime,
                                                       @ApiParam(value = "采集结束时间", required = false)
                                                       @RequestParam(value = "endTime",required = false) Date endTime,
                                                       @ApiParam(value = "分页参数(页数)", required = false, example = "1")
                                                       @RequestParam(value = "pageNum",required = false) Integer pageNum,
                                                       @ApiParam(value = "分页参数(页大小)", required = false, example = "20")
                                                       @RequestParam(value = "pageSize",required = false) Integer pageSize,
                                                       @RequestParam(value = "lastId",required = false) String lastId) {
        return ResponseData.ok(positionDataService.page(deviceCode, startTime, endTime, pageNum, pageSize, lastId)).extraMsg("请求成功");
    }



    @GetMapping("/totalMileage")
    public ResponseData<DeviceInfo> totalMileage(@ApiParam(value = "设备号", required = false)
                                                 @RequestParam(value = "deviceCode", required = false) String deviceCode,
                                                 @ApiParam(value = "指定日期", required = false)
                                                 @RequestParam(value = "specified", required = false) Date specified) {

        return ResponseData.ok(positionDataService.totalMileage(deviceCode, specified)).extraMsg("请求成功");
    }
}
