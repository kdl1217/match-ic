package com.incarcloud.match.service.impl;

import com.incarcloud.match.entity.DeviceInfo;
import com.incarcloud.match.entity.Point;
import com.incarcloud.match.entity.PositionData;
import com.incarcloud.match.mongoDB.page.MongoPageHelper;
import com.incarcloud.match.mongoDB.page.PageResult;
import com.incarcloud.match.service.IPositionDataService;
import com.incarcloud.match.utils.TrajectoryCompressionUtil;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by bzheng on 2020/2/29.
 */
@Service
public class PositionDataServiceImpl implements IPositionDataService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    MongoPageHelper mongoPageHelper;

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

    @Override
    public PageResult<PositionData> page(String deviceCode, Date startTime, Date endTime, Integer pageNum, Integer pageSize, String lastId) {
        Query query=new Query();
        if (!StringUtils.isEmpty(deviceCode)) {
            query.addCriteria(Criteria.where("deviceId").is(deviceCode));
        }
        if (Objects.nonNull(startTime) && Objects.nonNull(endTime) ) {
            query.addCriteria(Criteria.where("collectTime").gte(startTime).lte(endTime));
        }



        return mongoPageHelper.pageQuery(query, PositionData.class, collectionName, Function.identity(), pageSize, pageNum, lastId);
    }

    @Override
    public DeviceInfo totalMileage(String deviceCode, Date specified) {

        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("deviceId").is(deviceCode).and("collectTime").lte(specified)),
                Aggregation.group("tripId").max("distance").as("maxDistance"),
                Aggregation.group("tripId").sum("maxDistance").as("totalDistance")
        );

        AggregationResults<PositionData> results = mongoTemplate.aggregate(agg, collectionName, PositionData.class);

        Document document = results.getRawResults();
        Object object = document.get("results");

        double mileage = 0d;
        if (null != object) {
            try {
                if (object instanceof ArrayList<?>) {
                    Document result = (Document) ((ArrayList) object).get(0);
                    mileage = result.getDouble("totalDistance");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return new DeviceInfo(deviceCode, mileage);
    }
}
