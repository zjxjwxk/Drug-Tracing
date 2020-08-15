package cn.edu.zju.drugtracing.service.impl;

import cn.edu.zju.drugtracing.common.ServerResponse;
import cn.edu.zju.drugtracing.service.AuthorityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 * @author Xinkang Wu
 * @date 2020/8/12 10:43 下午
 */
@Service("AuthorityService")
public class DefaultAuthorityServiceImpl implements AuthorityService {

    private static final Logger log = LoggerFactory.getLogger(GreeterServiceImpl.class);

    @Value("${client.url}")
    private String clientUrl;
    @Value("${wallet.password}")
    private String walletPassword;
    @Value("${wallet.key-path}")
    private String walletKeyPath;
    @Value("${contract.address.greeter}")
    private String greeterAddress;

    private Web3j web3j;
    private Credentials credentials;
    private ContractGasProvider contractGasProvider;

    @PostConstruct
    public void init() throws Exception {
        web3j = Web3j.build(new HttpService(clientUrl));
        File walletKey = new File(walletKeyPath);
        credentials = WalletUtils.loadCredentials(walletPassword, walletKey);
        log.info("Credentials loaded");
        contractGasProvider = new DefaultGasProvider();
    }

    @Override
    public ServerResponse trace(String packageID) {
        return null;
    }

    @Override
    public ServerResponse getFeedBack() {
        return null;
    }
}
