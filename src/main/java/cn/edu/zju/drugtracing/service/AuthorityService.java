package cn.edu.zju.drugtracing.service;

import cn.edu.zju.drugtracing.common.ServerResponse;

/**
 * @author Xinkang Wu
 * @date 2020/8/12 10:37 下午
 */
public interface AuthorityService {

    ServerResponse trace(String packageID);

    ServerResponse getFeedBack();
}
