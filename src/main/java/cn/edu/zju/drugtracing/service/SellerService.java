package cn.edu.zju.drugtracing.service;

import cn.edu.zju.drugtracing.common.ServerResponse;

/**
 * @author Xinkang Wu
 * @date 2020/8/12 10:41 下午
 */
public interface SellerService {

    ServerResponse get();

    ServerResponse set();

    ServerResponse setSellInfo(String packageID, String time, String sellerAddr, String consumerAddr, String price);
}
