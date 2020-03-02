package com.incarcloud.match;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOptions;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Iterator;

/**
 * 启动类
 *
 * Created by bzheng on 2019/11/28.
 */
@SpringBootApplication(scanBasePackages = {
        "com.incarcloud.match"
})
public class App implements CommandLineRunner {

    /**
     * 缓存总里程
     */
    public static Long CACHE_DEVICE_TOTAL = 0L;

    /**
     * 缓存总里程
     */
    public static Long CACHE_DISTANCE_TOTAL = 0L;

    @Autowired
    private MongoTemplate mongoTemplate;

    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        // 统计总里程
        System.out.println("begin...");
        AggregationOptions aggregationOptions = AggregationOptions.builder().allowDiskUse(true).build();
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group(
                        Fields.from(
                                Fields.field("deviceId", "deviceId"),
                                Fields.field("tripId", "tripId")
                        )
                ).max("distance").as("distanceMax")
        ).withOptions(aggregationOptions);

        AggregationResults<TripResult> aggregationResults = mongoTemplate.aggregate(aggregation, "deviceData", TripResult.class);
        Iterator<TripResult> it = aggregationResults.iterator();
        TripResult tripResult;
        Double distanceTotal = 0.0;
        while (it.hasNext()) {
            tripResult = it.next();
            distanceTotal += tripResult.getDistanceMax();
        }
        System.out.println("distanceTotal: " + distanceTotal);
        System.out.println("end...");

        // 缓存信息
        CACHE_DISTANCE_TOTAL = distanceTotal.longValue();
    }

    @Data
    @Document
    static class TripResult {
        private String deviceId;
        private Long tripId;
        private Double distanceMax;
    }
}
