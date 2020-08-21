package cn.edu.zju.drugtracing.vo;

import lombok.Data;

/**
 * @author Xinkang Wu
 * @date 2020/8/21 6:29 下午
 */
@Data
public class SellerVO {

    String sellerAddr;
    String sellerName;
    Integer sellerType;

    public SellerVO(String sellerAddr, String sellerName, Integer sellerType) {
        this.sellerAddr = sellerAddr;
        this.sellerName = sellerName;
        this.sellerType = sellerType;
    }
}
