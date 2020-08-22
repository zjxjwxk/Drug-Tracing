package cn.edu.zju.drugtracing.vo;

import lombok.Data;

/**
 * @author Xinkang Wu
 * @date 2020/8/21 6:28 下午
 */
@Data
public class FormulationVO {

    String drugID;
    String drugName;
    String material;

    public FormulationVO(String drugID, String drugName, String material) {
        this.drugID = drugID;
        this.drugName = drugName;
        this.material = material;
    }
}
