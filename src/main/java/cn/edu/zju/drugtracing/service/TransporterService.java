package cn.edu.zju.drugtracing.service;

import cn.edu.zju.drugtracing.common.ServerResponse;

/**
 * @author Xinkang Wu
 * @date 2020/8/12 10:41 下午
 */
public interface TransporterService {

    ServerResponse<String> setTransporter(String transporterName);

    ServerResponse<String> pick(String boxID, Integer time);

    ServerResponse<String> drop(String boxID, Integer time, String sellerAddr);
}
