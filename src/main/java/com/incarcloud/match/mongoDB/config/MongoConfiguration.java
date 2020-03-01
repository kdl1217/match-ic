package com.incarcloud.match.mongoDB.config;

import com.incarcloud.match.mongoDB.page.MongoPageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by bzheng on 2020/3/1.
 */
public class MongoConfiguration {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Bean
    public MongoPageHelper mongoPageHelper() {
        return new MongoPageHelper(mongoTemplate);
    }

}
