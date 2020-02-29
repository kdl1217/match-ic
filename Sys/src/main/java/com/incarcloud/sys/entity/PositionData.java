package com.incarcloud.sys.entity;

import com.alibaba.fastjson.annotation.JSONField;
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

    private String deviceCode;

    private Long tripNo;

    private Double speed;

    private Double distance;

    private String longitude;

    private String latitude;

    private Long angle;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date collectTime;
}
