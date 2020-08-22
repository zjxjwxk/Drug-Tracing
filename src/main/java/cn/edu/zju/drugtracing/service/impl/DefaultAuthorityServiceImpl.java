package cn.edu.zju.drugtracing.service.impl;

import cn.edu.zju.drugtracing.common.ServerResponse;
import cn.edu.zju.drugtracing.contract.MedicineSourceTracing;
import cn.edu.zju.drugtracing.service.AuthorityService;
import cn.edu.zju.drugtracing.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.*;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import javax.annotation.PostConstruct;
import java.io.File;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Xinkang Wu
 * @date 2020/8/12 10:43 下午
 */
@Service("AuthorityService")
public class DefaultAuthorityServiceImpl implements AuthorityService {

    private static final Logger log = LoggerFactory.getLogger(DefaultAuthorityServiceImpl.class);

    @Value("${client.url}")
    private String clientUrl;
    @Value("${wallet.authority.password}")
    private String walletPassword;
    @Value("${wallet.authority.key-path}")
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
    public ServerResponse<String> setAuthority(String authorityAddr) {
        try {
            TransactionReceipt transactionReceipt = medicineSourceTracing.setAuthority(authorityAddr).send();
            MedicineSourceTracing.NewAuthorityEventResponse response = medicineSourceTracing.getNewAuthorityEvents(transactionReceipt).get(0);
            return ServerResponse.createBySuccessMessage(response.message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
    public ServerResponse<List<ManufacturerVO>> getManufacturers() {
        try {
            List<ManufacturerVO> manufacturerVOList = new ArrayList<>();
            int i = 0;
            Tuple3<String, String, Boolean> tuple3 = medicineSourceTracing.getManufacturers(BigInteger.valueOf(i)).send();
            while (tuple3.getValue3()) {
                manufacturerVOList.add(new ManufacturerVO(
                        tuple3.getValue1(),
                        tuple3.getValue2()
                ));
                tuple3 = medicineSourceTracing.getManufacturers(BigInteger.valueOf(++i)).send();
            }
            return ServerResponse.createBySuccess(manufacturerVOList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.createByError();
    }

    @Override
    public ServerResponse<List<FormulationVO>> getFormulations() {
        try {
            List<FormulationVO> formulationVOList = new ArrayList<>();
            int i = 0;
            Tuple4<byte[], String, String, Boolean> tuple4 = medicineSourceTracing.getFormulations(BigInteger.valueOf(i)).send();
            while (tuple4.getValue4()) {
                formulationVOList.add(new FormulationVO(
                        new String(tuple4.getValue1()),
                        tuple4.getValue2(),
                        tuple4.getValue3()
                ));
                tuple4 = medicineSourceTracing.getFormulations(BigInteger.valueOf(++i)).send();
            }
            return ServerResponse.createBySuccess(formulationVOList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.createByError();
    }

    @Override
    public ServerResponse<List<TransporterVO>> getTransporters() {
        try {
            List<TransporterVO> transporterVOList = new ArrayList<>();
            int i = 0;
            Tuple3<String, String, Boolean> tuple3 = medicineSourceTracing.getTransporters(BigInteger.valueOf(i)).send();
            while (tuple3.getValue3()) {
                transporterVOList.add(new TransporterVO(
                        tuple3.getValue1(),
                        tuple3.getValue2()
                ));
                tuple3 = medicineSourceTracing.getTransporters(BigInteger.valueOf(++i)).send();
            }
            return ServerResponse.createBySuccess(transporterVOList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.createByError();
    }

    @Override
    public ServerResponse<List<SellerVO>> getSellers() {
        try {
            List<SellerVO> sellerVOList = new ArrayList<>();
            int i = 0;
            Tuple4<String, String, BigInteger, Boolean> tuple4 = medicineSourceTracing.getSellers(BigInteger.valueOf(i)).send();
            while (tuple4.getValue4()) {
                sellerVOList.add(new SellerVO(
                        tuple4.getValue1(),
                        tuple4.getValue2(),
                        tuple4.getValue3().intValue()
                ));
                tuple4 = medicineSourceTracing.getSellers(BigInteger.valueOf(++i)).send();
            }
            return ServerResponse.createBySuccess(sellerVOList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.createByError();
    }

    @Override
    public ServerResponse<List<String>> getPackInfo(String boxID) {
        try {
            List<String> packageIDList = new ArrayList<>();
            int i = 0;
            Tuple2<byte[], Boolean> tuple2 = medicineSourceTracing.getPackInfo(boxID.getBytes(), BigInteger.valueOf(i)).send();
            while (tuple2.getValue2()) {
                packageIDList.add(new String(tuple2.getValue1()));
                tuple2 = medicineSourceTracing.getPackInfo(boxID.getBytes(), BigInteger.valueOf(++i)).send();
            }
            return ServerResponse.createBySuccess(packageIDList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.createByError();
    }

    @Override
    public ServerResponse<List<FeedBackVO>> getFeedBacks(String drugID) {
        try {
            List<FeedBackVO> feedBackVOList = new ArrayList<>();
            int i = 0;
            Tuple6<byte[], String, BigInteger, BigInteger, BigInteger, Boolean> tuple6 = medicineSourceTracing.getFeedBacks(drugID.getBytes(), BigInteger.valueOf(i)).send();
            while (tuple6.getValue6()) {
                feedBackVOList.add(new FeedBackVO(
                        new String(tuple6.getValue1()),
                        tuple6.getValue2(),
                        tuple6.getValue3().intValue(),
                        tuple6.getValue4().intValue(),
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(tuple6.getValue5().longValue() * 1000))
                ));
                tuple6 = medicineSourceTracing.getFeedBacks(drugID.getBytes(), BigInteger.valueOf(++i)).send();
            }
            return ServerResponse.createBySuccess(feedBackVOList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.createByError();
    }
}
