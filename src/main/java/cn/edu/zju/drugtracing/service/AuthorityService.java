package cn.edu.zju.drugtracing.service;

import cn.edu.zju.drugtracing.common.ServerResponse;
import cn.edu.zju.drugtracing.vo.*;

import java.util.List;

/**
 * @author Xinkang Wu
 * @date 2020/8/12 10:37 下午
 */
public interface AuthorityService {

    ServerResponse<String> setAuthority(String authorityAddr);

    ServerResponse<TraceVO> trace(String packageID);

    ServerResponse<List<ManufacturerVO>> getManufacturers();

    ServerResponse<List<FormulationVO>> getFormulations();

    ServerResponse<List<TransporterVO>> getTransporters();

    ServerResponse<List<SellerVO>> getSellers();

    ServerResponse<List<String>> getPackInfo(String boxID);

    ServerResponse<List<FeedBackVO>> getFeedBacks(String drugID);
}
