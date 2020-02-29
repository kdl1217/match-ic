package com.incarcloud.sys.service;

import com.incarcloud.sys.entity.Point;

import java.util.Date;
import java.util.List;

/**
 * Created by bzheng on 2020/2/29.
 */
public interface IPositionDataService {

    Long getCount();

    Double getTotalMileage(String deviceCode, Date startTime, Date endTime);

    List<Point> query(String deviceCode, Date startTime, Date endTime);
}

