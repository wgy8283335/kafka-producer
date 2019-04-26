package com.sensetime.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sensetime.entity.Device;
import com.sensetime.mapper.DeviceMapper;
import com.sensetime.service.DeviceService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jason wang
 * @since 2019-01-04
 */
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements DeviceService {

}
