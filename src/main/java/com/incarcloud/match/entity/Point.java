package com.incarcloud.match.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by bzheng on 2020/2/29.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Point {

    @ApiModelProperty(value = "采集时间")
    public Date time;

    @ApiModelProperty(value = "经度")
    public double longitude;

    @ApiModelProperty(value = "纬度")
    public double latitude;

}
