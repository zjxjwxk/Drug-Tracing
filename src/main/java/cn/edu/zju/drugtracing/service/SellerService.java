package cn.edu.zju.drugtracing.service;

import cn.edu.zju.drugtracing.common.ServerResponse;

/**
 * @author Xinkang Wu
 * @date 2020/8/12 10:41 下午
 */
public interface SellerService {

    ServerResponse<String> setSeller(String sellerName, Integer sellerType);

    ServerResponse<String> setSellInfo(String packageID, Integer time, String consumerAddr, Integer price);
}
