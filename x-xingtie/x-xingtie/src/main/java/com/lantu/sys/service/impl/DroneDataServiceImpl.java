package com.lantu.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lantu.sys.entity.DroneData;
import com.lantu.sys.mapper.DroneDataMapper;
import com.lantu.sys.service.DroneDataService;
import org.springframework.stereotype.Service;

@Service
public class DroneDataServiceImpl extends ServiceImpl<DroneDataMapper, DroneData> implements DroneDataService {

    @Override
    public void saveDroneData(DroneData data) {
        this.save(data); // 使用 MyBatis-Plus 插入数据
    }
}
