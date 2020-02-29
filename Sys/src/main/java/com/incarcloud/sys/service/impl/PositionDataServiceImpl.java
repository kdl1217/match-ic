package com.incarcloud.sys.service.impl;

import com.incarcloud.sys.entity.Point;
import com.incarcloud.sys.service.IPositionDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by bzheng on 2020/2/29.
 */
@Service
public class PositionDataServiceImpl implements IPositionDataService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Long getCount() {
        return null;
    }

    @Override
    public Double getTotalMileage(String deviceCode, Date startTime, Date endTime) {
        return null;
    }

    @Override
    public List<Point> query(String deviceCode, Date startTime, Date endTime) {
        return null;
    }
}
