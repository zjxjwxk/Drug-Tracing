package cn.edu.zju.drugtracing.service.impl;

import cn.edu.zju.drugtracing.common.ServerResponse;
import cn.edu.zju.drugtracing.contract.MedicineSourceTracing;
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
import java.math.BigInteger;

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
    public ServerResponse<String> setManufacturer(String manufacturerAddr, String manufacturerName) {
        try {
            TransactionReceipt transactionReceipt = medicineSourceTracing.setManufacturer(manufacturerAddr, manufacturerName.getBytes()).send();
            MedicineSourceTracing.NewManufacturerEventResponse response = medicineSourceTracing.getNewManufacturerEvents(transactionReceipt).get(0);
            return ServerResponse.createBySuccessMessage(response.message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.createByError();
    }

    @Override
    public ServerResponse<String> pack(String packageID, String boxID) {
        try {
            TransactionReceipt transactionReceipt = medicineSourceTracing.pack(packageID.getBytes(), boxID.getBytes()).send();
            MedicineSourceTracing.NewPackInfoEventResponse response = medicineSourceTracing.getNewPackInfoEvents(transactionReceipt).get(0);
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

    @Override
    public ServerResponse<String> setFormulation(String drugID, String drugName, String material) {
        try {
            TransactionReceipt transactionReceipt = medicineSourceTracing.setFormulation(drugID.getBytes(), drugName.getBytes(), material.getBytes()).send();
            MedicineSourceTracing.NewFormulationEventResponse response = medicineSourceTracing.getNewFormulationEvents(transactionReceipt).get(0);
            return ServerResponse.createBySuccessMessage(response.message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.createByError();
    }

    @Override
    public ServerResponse<String> setBoxInfo(String boxID, String manufacturerAddr, String time, String materialID) {
        try {
            TransactionReceipt transactionReceipt = medicineSourceTracing.setBoxInfo(boxID.getBytes(), manufacturerAddr, BigInteger.valueOf(Long.parseLong(time)), materialID.getBytes()).send();
            MedicineSourceTracing.NewBoxInfoEventResponse response = medicineSourceTracing.getNewBoxInfoEvents(transactionReceipt).get(0);
            return ServerResponse.createBySuccessMessage(response.message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.createByError();
    }
}
