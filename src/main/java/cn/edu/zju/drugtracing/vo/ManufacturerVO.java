package cn.edu.zju.drugtracing.vo;

import lombok.Data;

/**
 * @author Xinkang Wu
 * @date 2020/8/21 5:59 下午
 */
@Data
public class ManufacturerVO {

    String manufacturerAddr;
    String manufacturerName;

    public ManufacturerVO(String manufacturerAddr, String manufacturerName) {
        this.manufacturerAddr = manufacturerAddr;
        this.manufacturerName = manufacturerName;
    }
}
