package cn.edu.zju.drugtracing.service.impl;

import cn.edu.zju.drugtracing.common.ServerResponse;
import cn.edu.zju.drugtracing.model.MedicineSourceTracing;
import cn.edu.zju.drugtracing.service.ManufacturerService;
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
import java.util.concurrent.CompletableFuture;

/**
 * @author Xinkang Wu
 * @date 2020/8/12 10:44 下午
 */
@Service("ManufacturerService")
public class DefaultManufacturerServiceImpl implements ManufacturerService {

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
    public ServerResponse<String> setManufacturer(String manufacturerAddr, String manufacturerName) {
        try {
            CompletableFuture<TransactionReceipt> transactionReceiptCompletableFuture = medicineSourceTracing.setManufacturer(manufacturerAddr, manufacturerName.getBytes()).sendAsync();
            transactionReceiptCompletableFuture.get();
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.createByError();
    }

    @Override
    public ServerResponse setFormulation(String drugID, String drugName, String[] material) {
        return null;
    }

    @Override
    public ServerResponse setBoxInfo(String boxID, String manufacturerAddr, String time, String materialID) {
        return null;
    }
}
