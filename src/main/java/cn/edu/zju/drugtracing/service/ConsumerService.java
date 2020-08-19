package cn.edu.zju.drugtracing.service;

import cn.edu.zju.drugtracing.common.ServerResponse;
import cn.edu.zju.drugtracing.vo.TraceVO;

/**
 * @author Xinkang Wu
 * @date 2020/8/12 10:39 下午
 */
public interface ConsumerService {

    ServerResponse<String> setConsumer(String consumerAddr, Integer gender, Integer age);

    ServerResponse<TraceVO> trace(String packageID);

    ServerResponse<String> feedBack(String packageID, String consumerAddr, Integer time, String information);
}
