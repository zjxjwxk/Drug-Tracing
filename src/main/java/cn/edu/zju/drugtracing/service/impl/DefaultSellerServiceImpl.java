package cn.edu.zju.drugtracing.service.impl;

import cn.edu.zju.drugtracing.common.ServerResponse;
import cn.edu.zju.drugtracing.service.SellerService;
import org.springframework.stereotype.Service;

/**
 * @author Xinkang Wu
 * @date 2020/8/12 10:45 下午
 */
@Service("SellerService")
public class DefaultSellerServiceImpl implements SellerService {

    @Override
    public ServerResponse get() {
        return null;
    }

    @Override
    public ServerResponse set() {
        return null;
    }

    @Override
    public ServerResponse setSellInfo(String packageID, String time, String sellerAddr, String consumerAddr, String price) {
        return null;
    }
}
