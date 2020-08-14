package cn.edu.zju.drugtracing.service.impl;

import cn.edu.zju.drugtracing.common.ServerResponse;
import cn.edu.zju.drugtracing.service.ConsumerService;
import org.springframework.stereotype.Service;

/**
 * @author Xinkang Wu
 * @date 2020/8/12 10:43 下午
 */
@Service("ConsumerService")
public class DefaultConsumerServiceImpl implements ConsumerService {

    @Override
    public ServerResponse trace(String packageID) {
        return null;
    }

    @Override
    public ServerResponse feedBack(String packageID, String consumerAddr, String time, String information) {
        return null;
    }
}
