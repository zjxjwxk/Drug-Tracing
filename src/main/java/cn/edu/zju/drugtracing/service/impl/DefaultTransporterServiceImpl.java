package cn.edu.zju.drugtracing.service.impl;

import cn.edu.zju.drugtracing.common.ServerResponse;
import cn.edu.zju.drugtracing.service.TransporterService;
import org.springframework.stereotype.Service;

/**
 * @author Xinkang Wu
 * @date 2020/8/12 10:45 下午
 */
@Service("TransporterService")
public class DefaultTransporterServiceImpl implements TransporterService {

    @Override
    public ServerResponse get() {
        return null;
    }

    @Override
    public ServerResponse set() {
        return null;
    }

    @Override
    public ServerResponse pick(String boxID, String time, String orderID) {
        return null;
    }

    @Override
    public ServerResponse drop(String boxID, String time, String orderID) {
        return null;
    }
}
