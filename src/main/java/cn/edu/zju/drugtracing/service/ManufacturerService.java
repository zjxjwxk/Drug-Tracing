package cn.edu.zju.drugtracing.service;

import cn.edu.zju.drugtracing.common.ServerResponse;

/**
 * @author Xinkang Wu
 * @date 2020/8/12 10:39 下午
 */
public interface ManufacturerService {

    ServerResponse get();

    ServerResponse<String> setManufacturer(String manufacturerAddr, String manufacturerName);

    ServerResponse<String> pack(String packageID, String boxID);

    ServerResponse<String> setFormulation(String drugID, String drugName, String material);

    ServerResponse<String> setBoxInfo(String boxID, String manufacturerAddr, Integer time, String materialID);
}
