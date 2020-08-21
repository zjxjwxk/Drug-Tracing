package cn.edu.zju.drugtracing.vo;

import lombok.Data;

/**
 * @author Xinkang Wu
 * @date 2020/8/21 6:28 下午
 */
@Data
public class TransporterVO {

    String transporterAddr;
    String transporterName;

    public TransporterVO(String transporterAddr, String transporterName) {
        this.transporterAddr = transporterAddr;
        this.transporterName = transporterName;
    }
}
