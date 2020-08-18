package cn.edu.zju.drugtracing.service.impl;

import cn.edu.zju.drugtracing.common.ServerResponse;
import cn.edu.zju.drugtracing.contract.MedicineSourceTracing;
import cn.edu.zju.drugtracing.service.TransporterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import javax.annotation.PostConstruct;
import java.io.File;
import java.math.BigInteger;

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

    private MedicineSourceTracing medicineSourceTracing;

    @PostConstruct
    public void init() throws Exception {
        Web3j web3j = Web3j.build(new HttpService(clientUrl));
        log.info("Connected to Ethereum client");
        File walletKey = new File(walletKeyPath);
        Credentials credentials = WalletUtils.loadCredentials(walletPassword, walletKey);
        log.info("Credentials loaded");
        ContractGasProvider contractGasProvider = new DefaultGasProvider();
        log.info("Loading MedicineSourceTracing smart contract at address: " + contractAddress);
        medicineSourceTracing = MedicineSourceTracing.load(
                contractAddress,
                web3j,
                credentials,
                contractGasProvider
        );
        log.info("View contract at https://rinkeby.etherscan.io/address/" + contractAddress);
    }

    @Override
    public ServerResponse get() {
        return null;
    }

    @Override
    public ServerResponse<String> setTransporter(String transporterAddr, String transporterName) {
        try {
            TransactionReceipt transactionReceipt = medicineSourceTracing.setTransporter(transporterAddr, transporterName.getBytes()).send();
            MedicineSourceTracing.NewTransporterEventResponse response = medicineSourceTracing.getNewTransporterEvents(transactionReceipt).get(0);
            return ServerResponse.createBySuccessMessage(response.message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.createByError();
    }

    @Override
    public ServerResponse<String> pick(String boxID, Integer time) {
        try {
            TransactionReceipt transactionReceipt = medicineSourceTracing.pick(boxID.getBytes(), BigInteger.valueOf(time)).send();
            MedicineSourceTracing.NewPickInfoEventResponse response = medicineSourceTracing.getNewPickInfoEvents(transactionReceipt).get(0);
            return ServerResponse.createBySuccessMessage(response.message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.createByError();
    }

    @Override
    public ServerResponse<String> drop(String boxID, Integer time, String sellerAddr) {
        try {
            TransactionReceipt transactionReceipt = medicineSourceTracing.drop(boxID.getBytes(), BigInteger.valueOf(time), sellerAddr).send();
            MedicineSourceTracing.NewDropInfoEventResponse response = medicineSourceTracing.getNewDropInfoEvents(transactionReceipt).get(0);
            return ServerResponse.createBySuccessMessage(response.message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.createByError();
    }
}
