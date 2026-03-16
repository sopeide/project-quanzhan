package com.lantu.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lantu.sys.entity.DroneData;

public interface DroneDataService extends IService<DroneData> {
    void saveDroneData(DroneData data);
}
