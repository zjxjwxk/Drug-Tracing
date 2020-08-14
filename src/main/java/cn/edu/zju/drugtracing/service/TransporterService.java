package cn.edu.zju.drugtracing.service;

import cn.edu.zju.drugtracing.common.ServerResponse;

/**
 * @author Xinkang Wu
 * @date 2020/8/12 10:41 下午
 */
public interface TransporterService {

    ServerResponse get();

    ServerResponse set();

    ServerResponse pick(String boxID, String time, String orderID);

    ServerResponse drop(String boxID, String time, String orderID);
}
