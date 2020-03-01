package com.incarcloud.match.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 设备信息
 *
 * @author Kong, created on 2020-03-01T20:15.
 * @since 1.0.0-SNAPSHOT
 */
@Setter
@Getter
public class DeviceInfo {

    /**
     * 设备编号
     */
    @ApiModelProperty(position = 1, value = "设备编号")
    private String deviceCode;

    /**
     * 里程数
     */
    @ApiModelProperty(position = 2, value = "里程数")
    private Double mileage;

    public DeviceInfo() {
    }

    public DeviceInfo(String deviceCode, Double mileage) {
        this.deviceCode = deviceCode;
        this.mileage = mileage;
    }
}
