package com.lantu.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("drone_data")
public class DroneData {

    @TableId(type = IdType.AUTO)
    private Long id;

    private double pitch;
    private double roll;
    private double yaw;
    private double latitude;
    private double longitude;
    private double altitude;
}
