package com.incarcloud.match.service;

import com.incarcloud.match.entity.DeviceInfo;
import com.incarcloud.match.entity.Point;
import com.incarcloud.match.entity.PositionData;
import com.incarcloud.match.mongoDB.page.PageResult;

import java.util.Date;
import java.util.List;

/**
 * Created by bzheng on 2020/2/29.
 */
public interface IPositionDataService {

    Long getCount();

    Double getTotalMileage(String deviceCode, Date startTime, Date endTime);

    List<Point> query(String deviceCode, Date startTime, Date endTime);

    PageResult<PositionData> page(String deviceCode, Date startTime, Date endTime, Integer pageNum, Integer pageSize, String lastId);

    DeviceInfo totalMileage(String deviceCode, Date specified);
}

