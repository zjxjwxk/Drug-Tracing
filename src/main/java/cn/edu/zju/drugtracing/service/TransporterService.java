package cn.edu.zju.drugtracing.service;

import cn.edu.zju.drugtracing.common.ServerResponse;

/**
 * @author Xinkang Wu
 * @date 2020/8/12 10:41 下午
 */
public interface TransporterService {

    ServerResponse get();

    ServerResponse<String> setTransporter(String transporterAddr, String transporterName);

    ServerResponse<String> pick(String boxID, String time);

    ServerResponse<String> drop(String boxID, String time, String sellerAddr);
}
