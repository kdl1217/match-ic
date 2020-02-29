package com.incarcloud.sys.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by bzheng on 2020/2/29.
 */
@Getter
@Setter
public class Point {

    @ApiModelProperty(value = "经度")
    public double longitude;

    @ApiModelProperty(value = "纬度")
    public double latitude;

}
