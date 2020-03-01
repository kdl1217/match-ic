package com.incarcloud.match.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by bzheng on 2020/2/29.
 */
@Setter
@Getter
public class PositionData implements Serializable {

    private String id;

    private String deviceId;

    private Long tripId;

    private Double speed;

    private Double distance;

    private String longitude;

    private String latitude;

    private Long angle;

    //@JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date collectTime;
}
