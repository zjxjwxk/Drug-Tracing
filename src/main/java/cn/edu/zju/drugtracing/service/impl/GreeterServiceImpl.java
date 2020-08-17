package cn.edu.zju.drugtracing.service.impl;

import cn.edu.zju.drugtracing.common.ServerResponse;
import cn.edu.zju.drugtracing.model.Greeter;
import cn.edu.zju.drugtracing.service.GreeterService;
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
 * @date 2020/8/14 9:45 下午
 */
@Service("GreeterService")
public class GreeterServiceImpl implements GreeterService {

    private static final Logger log = LoggerFactory.getLogger(GreeterServiceImpl.class);

    @Value("${client.url}")
    private String clientUrl;
    @Value("${wallet.password}")
    private String walletPassword;
    @Value("${wallet.key-path}")
    private String walletKeyPath;
    @Value("${contract.address}")
    private String greeterAddress;

    private Web3j web3j;
    private Credentials credentials;
    private ContractGasProvider contractGasProvider;
    private Greeter greeter;

    @PostConstruct
    public void init() throws Exception {
        web3j = Web3j.build(new HttpService(clientUrl));
        File walletKey = new File(walletKeyPath);
        credentials = WalletUtils.loadCredentials(walletPassword, walletKey);
        log.info("Credentials loaded");
        contractGasProvider = new DefaultGasProvider();
        log.info("Loading greeter smart contract at address: " + greeterAddress);
        greeter = Greeter.load(
                greeterAddress,
                web3j,
                credentials,
                contractGasProvider
        );
        log.info("View contract at https://rinkeby.etherscan.io/address/" + greeterAddress);
    }

    @Override
    public ServerResponse<String> greet() {
        try {
            String value = greeter.greet().send();
            log.info("Value stored in remote smart contract: " + value);
            return ServerResponse.createBySuccess(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.createByError();
    }

    @Override
    public ServerResponse<String> newGreet(String newGreet) {
        try {
            greeter.newGreeting(newGreet).sendAsync();
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.createByError();
    }
}
