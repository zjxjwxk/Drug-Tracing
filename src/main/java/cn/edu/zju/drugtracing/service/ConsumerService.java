package cn.edu.zju.drugtracing.service;

import cn.edu.zju.drugtracing.common.ServerResponse;

/**
 * @author Xinkang Wu
 * @date 2020/8/12 10:39 下午
 */
public interface ConsumerService {

    ServerResponse trace(String packageID);

    ServerResponse feedBack(String packageID, String consumerAddr, String time, String information);
}
