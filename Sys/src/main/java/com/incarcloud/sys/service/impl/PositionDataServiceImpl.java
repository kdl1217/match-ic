package com.incarcloud.sys.service.impl;

import com.incarcloud.sys.entity.Point;
import com.incarcloud.sys.entity.PositionData;
import com.incarcloud.sys.service.IPositionDataService;
import com.incarcloud.sys.utils.TrajectoryCompressionUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by bzheng on 2020/2/29.
 */
@Service
public class PositionDataServiceImpl implements IPositionDataService {

    @Autowired
    private MongoTemplate mongoTemplate;

    // MongoDB的集合名称
    private static String collectionName = "deviceData";

    @Override
    public Long getCount() {

        return mongoTemplate.count(new Query(), collectionName);
    }

    @Override
    public Double getTotalMileage(String deviceCode, Date startTime, Date endTime) {
        Double total = 0D;
        Query query=new Query(Criteria.where("deviceId").is(deviceCode)).addCriteria(Criteria.where("collectTime").lte(endTime).gte(startTime));
        List<PositionData> positionData = mongoTemplate.find(query, PositionData.class, collectionName);
        if (!org.springframework.util.CollectionUtils.isEmpty(positionData)) {
            total = positionData.stream().filter(data -> Objects.nonNull(data) && Objects.nonNull(data.getDistance())).mapToDouble(PositionData::getDistance).sum();
        }
        return total;
    }

    @Override
    public List<Point> query(String deviceCode, Date startTime, Date endTime) {
        Query query=new Query(Criteria.where("deviceId").is(deviceCode)).addCriteria(Criteria.where("collectTime").lte(endTime).gte(startTime));
        List<PositionData> positionData = mongoTemplate.find(query, PositionData.class, collectionName);
        if (CollectionUtils.isEmpty(positionData)) {
            return Collections.emptyList();
        }
        List<Point> points = positionData.stream().map(data -> {
            String longitudeStr = data.getLongitude();
            String latitudeStr = data.getLatitude();
            double longitude = 0D;
            double latitude = 0D;
            if (!StringUtils.isEmpty(longitudeStr) && !StringUtils.isEmpty(latitudeStr)) {
                try {
                    longitude = Double.parseDouble(longitudeStr.replace("E", ""));
                    latitude = Double.valueOf(latitudeStr.replace("N", ""));
                } catch (Exception e) {
                    return null;
                }
            }
            return new Point(data.getCollectTime(), longitude, latitude);
        }).filter(Objects::nonNull).collect(Collectors.toList());

        // 最大误差距离
        double dMax = 20d;
        // 压缩经纬度
        return TrajectoryCompressionUtil.TrajectoryOptimize(points, dMax);
    }
}
