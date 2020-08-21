package cn.edu.zju.drugtracing.service.impl;

import cn.edu.zju.drugtracing.common.ServerResponse;
import cn.edu.zju.drugtracing.contract.MedicineSourceTracing;
import cn.edu.zju.drugtracing.service.SellerService;
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
@Service("SellerService")
public class DefaultSellerServiceImpl implements SellerService {

    private static final Logger log = LoggerFactory.getLogger(DefaultSellerServiceImpl.class);

    @Value("${client.url}")
    private String clientUrl;
    @Value("${wallet.seller.password}")
    private String walletPassword;
    @Value("${wallet.seller.key-path}")
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
    public ServerResponse<String> setSeller(String sellerName, Integer sellerType) {
        try {
            TransactionReceipt transactionReceipt = medicineSourceTracing.setSeller(sellerName, BigInteger.valueOf(sellerType)).send();
            MedicineSourceTracing.NewSellerEventResponse response = medicineSourceTracing.getNewSellerEvents(transactionReceipt).get(0);
            return ServerResponse.createBySuccessMessage(response.message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.createByError();
    }

    @Override
    public ServerResponse<String> setSellInfo(String packageID, Integer time, String consumerAddr, Integer price) {
        try {
            TransactionReceipt transactionReceipt = medicineSourceTracing.setSellInfo(packageID.getBytes(), BigInteger.valueOf(time), consumerAddr, BigInteger.valueOf(price)).send();
            MedicineSourceTracing.NewSellInfoEventResponse response = medicineSourceTracing.getNewSellInfoEvents(transactionReceipt).get(0);
            if (response.isSuccess) {
                return ServerResponse.createBySuccessMessage(response.message);
            } else {
                return ServerResponse.createByErrorMessage(response.message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.createByError();
    }
}
