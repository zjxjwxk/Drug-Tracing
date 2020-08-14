package cn.edu.zju.drugtracing.service.impl;

import cn.edu.zju.drugtracing.common.ServerResponse;
import cn.edu.zju.drugtracing.service.AuthorityService;
import org.springframework.stereotype.Service;

/**
 * @author Xinkang Wu
 * @date 2020/8/12 10:43 下午
 */
@Service("AuthorityService")
public class DefaultAuthorityServiceImpl implements AuthorityService {

    @Override
    public ServerResponse trace(String packageID) {
        return null;
    }

    @Override
    public ServerResponse getFeedBack() {
        return null;
    }
}
