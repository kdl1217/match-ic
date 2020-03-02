package com.incarcloud.match.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 统计信息
 *
 * @author Aaric, created on 2020-03-02T22:53.
 * @version 0.5.0-SNAPSHOT
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("统计信息")
public class TotalInfo {

    @ApiModelProperty(position = 1, value = "TXT记录总数")
    private Long recordFullTotal;

    @ApiModelProperty(position = 2, value = "TXT记录失败总数（导入失败）")
    private Long recordFailTotal = 3L;

    @ApiModelProperty(position = 3, value = "mongodb记录总数")
    private Long recordTotal;

    @ApiModelProperty(position = 4, value = "设备总数")
    private Long deviceTotal;

    @ApiModelProperty(position = 5, value = "总里程")
    private Long distanceTotal;
}
