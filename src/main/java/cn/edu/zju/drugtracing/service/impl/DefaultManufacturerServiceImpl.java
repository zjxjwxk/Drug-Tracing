package cn.edu.zju.drugtracing.service.impl;

import cn.edu.zju.drugtracing.common.ServerResponse;
import cn.edu.zju.drugtracing.service.ManufacturerService;
import org.springframework.stereotype.Service;

/**
 * @author Xinkang Wu
 * @date 2020/8/12 10:44 下午
 */
@Service("ManufacturerService")
public class DefaultManufacturerServiceImpl implements ManufacturerService {

    @Override
    public ServerResponse get() {
        return null;
    }

    @Override
    public ServerResponse set() {
        return null;
    }

    @Override
    public ServerResponse setFormulation(String drugID, String drugName, String[] material) {
        return null;
    }

    @Override
    public ServerResponse setBoxInfo(String boxID, String manufacturerAddr, String time, String materialID) {
        return null;
    }
}
