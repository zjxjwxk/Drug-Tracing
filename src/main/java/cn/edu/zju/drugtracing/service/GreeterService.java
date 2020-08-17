package cn.edu.zju.drugtracing.service;

import cn.edu.zju.drugtracing.common.ServerResponse;

/**
 * @author Xinkang Wu
 * @date 2020/8/14 9:45 下午
 */
public interface GreeterService {

    ServerResponse<String> greet();

    ServerResponse<String> newGreet(String newGreet);
}
