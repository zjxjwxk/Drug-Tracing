package cn.edu.zju.drugtracing.service.impl;

import cn.edu.zju.drugtracing.common.ServerResponse;
import cn.edu.zju.drugtracing.contract.MedicineSourceTracing;
import cn.edu.zju.drugtracing.service.ConsumerService;
import cn.edu.zju.drugtracing.vo.TraceVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple8;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import javax.annotation.PostConstruct;
import java.io.File;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Xinkang Wu
 * @date 2020/8/12 10:43 下午
 */
@Service("ConsumerService")
public class DefaultConsumerServiceImpl implements ConsumerService {

    private static final Logger log = LoggerFactory.getLogger(DefaultConsumerServiceImpl.class);

    @Value("${client.url}")
    private String clientUrl;
    @Value("${wallet.consumer.password}")
    private String walletPassword;
    @Value("${wallet.consumer.key-path}")
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
    public ServerResponse<String> setConsumer(Integer gender, Integer age) {
        try {
            TransactionReceipt transactionReceipt = medicineSourceTracing.setConsumer(BigInteger.valueOf(gender), BigInteger.valueOf(age)).send();
            MedicineSourceTracing.NewConsumerEventResponse response = medicineSourceTracing.getNewConsumerEvents(transactionReceipt).get(0);
            return ServerResponse.createBySuccessMessage(response.message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.createByError();
    }

    @Override
    public ServerResponse<TraceVO> trace(String packageID) {
        try {
            Tuple8<String, String, byte[], String, BigInteger, String, BigInteger, String> tuple8 = medicineSourceTracing.trace(packageID.getBytes()).send();
            return ServerResponse.createBySuccess(new TraceVO(
                    tuple8.getValue1(), tuple8.getValue2(),
                    new String(tuple8.getValue3()), tuple8.getValue4(),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(tuple8.getValue5().longValue() * 1000)), tuple8.getValue6(),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(tuple8.getValue7().longValue() * 1000)), tuple8.getValue8()
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.createByError();
    }

    @Override
    public ServerResponse<String> feedBack(String packageID, Integer time, String information) {
        try {
            TransactionReceipt transactionReceipt = medicineSourceTracing.feedBack(packageID.getBytes(), BigInteger.valueOf(time), information).send();
            MedicineSourceTracing.NewFeedBackEventResponse response = medicineSourceTracing.getNewFeedBackEvents(transactionReceipt).get(0);
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
