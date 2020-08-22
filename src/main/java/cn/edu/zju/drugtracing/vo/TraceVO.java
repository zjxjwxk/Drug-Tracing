package cn.edu.zju.drugtracing.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Xinkang Wu
 * @date 2020/8/18 12:03 下午
 */
@Data
public class TraceVO {

    String drugName;
    String material;
    String materialID;
    String manufacturerName;
    String pickTime;
    String transporterName;
    String dropTime;
    String sellerName;

    public TraceVO(String drugName, String material, String materialID, String manufacturerName, String pickTime, String transporterName, String dropTime, String sellerName) {
        this.drugName = drugName;
        this.material = material;
        this.materialID = materialID;
        this.manufacturerName = manufacturerName;
        this.pickTime = pickTime;
        this.transporterName = transporterName;
        this.dropTime = dropTime;
        this.sellerName = sellerName;
    }
}
