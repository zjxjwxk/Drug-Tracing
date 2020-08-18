package cn.edu.zju.drugtracing.service;

import cn.edu.zju.drugtracing.common.ServerResponse;

/**
 * @author Xinkang Wu
 * @date 2020/8/12 10:41 下午
 */
public interface SellerService {

    ServerResponse get();

    ServerResponse<String> setSeller(String sellerAddr, String sellerName, Integer sellerType);

    ServerResponse<String> setSellInfo(String packageID, Integer time, String sellerAddr, String consumerAddr, Integer price);
}
