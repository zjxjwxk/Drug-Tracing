package cn.edu.zju.drugtracing.service.impl;

import cn.edu.zju.drugtracing.common.ServerResponse;
import cn.edu.zju.drugtracing.model.MedicineSourceTracing;
import cn.edu.zju.drugtracing.service.TransporterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.ContractGasProvider;

import javax.annotation.PostConstruct;

/**
 * @author Xinkang Wu
 * @date 2020/8/12 10:45 下午
 */
@Service("TransporterService")
public class DefaultTransporterServiceImpl implements TransporterService {

    private static final Logger log = LoggerFactory.getLogger(GreeterServiceImpl.class);

    @Value("${client.url}")
    private String clientUrl;
    @Value("${wallet.password}")
    private String walletPassword;
    @Value("${wallet.key-path}")
    private String walletKeyPath;
    @Value("${contract.address}")
    private String contractAddress;

    private Web3j web3j;
    private Credentials credentials;
    private ContractGasProvider contractGasProvider;
    private MedicineSourceTracing medicineSourceTracing;

    @PostConstruct
    public void init() throws Exception {
//        web3j = Web3j.build(new HttpService(clientUrl));
//        log.info("Connected to Ethereum client");
//        File walletKey = new File(walletKeyPath);
//        credentials = WalletUtils.loadCredentials(walletPassword, walletKey);
//        log.info("Credentials loaded");
//        contractGasProvider = new DefaultGasProvider();
//        log.info("Loading MedicineSourceTracing smart contract at address: " + contractAddress);
//        medicineSourceTracing = MedicineSourceTracing.load(
//                contractAddress,
//                web3j,
//                credentials,
//                contractGasProvider
//        );
//        log.info("View contract at https://rinkeby.etherscan.io/address/" + contractAddress);
    }

    @Override
    public ServerResponse get() {
        return null;
    }

    @Override
    public ServerResponse set() {
        return null;
    }

    @Override
    public ServerResponse pick(String boxID, String time, String orderID) {
        return null;
    }

    @Override
    public ServerResponse drop(String boxID, String time, String orderID) {
        return null;
    }
}
