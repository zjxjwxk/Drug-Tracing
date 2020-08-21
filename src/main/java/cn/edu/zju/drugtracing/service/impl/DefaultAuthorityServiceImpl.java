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
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tuples.generated.Tuple9;
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
            Tuple9<String, List<String>, List<byte[]>, String, BigInteger, String, BigInteger, String, BigInteger> tuple9 = medicineSourceTracing.trace(packageID.getBytes()).send();
            List<String> materialIDStrList = new ArrayList<>();
            List<byte[]> materialIDBytesList = new ArrayList<>();
            for (byte[] materialIDBytes : materialIDBytesList) {
                materialIDStrList.add(new String(materialIDBytes));
            }
            return ServerResponse.createBySuccess(new TraceVO(
                    tuple9.getValue1(), tuple9.getValue2(),
                    materialIDStrList, tuple9.getValue4(),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(tuple9.getValue5().longValue() * 1000)), tuple9.getValue6(),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(tuple9.getValue7().longValue() * 1000)), tuple9.getValue8(),
                    tuple9.getValue9().intValue()
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.createByError();
    }

    @Override
    public ServerResponse<List<ManufacturerVO>> getManufacturers() {
        try {
            Tuple2<List<String>, List<String>> tuple2 = medicineSourceTracing.getManufacturers().send();
            List<String> manufacturerAddrList = tuple2.getValue1();
            List<String> manufacturerNameList = tuple2.getValue2();
            List<ManufacturerVO> manufacturerVOList = new ArrayList<>();
            for (int i = 0; i < manufacturerAddrList.size(); ++i) {
                manufacturerVOList.add(new ManufacturerVO(manufacturerAddrList.get(i), manufacturerNameList.get(i)));
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
            Tuple3<List<byte[]>, List<String>, List<List<String>>> tuple3 = medicineSourceTracing.getFormulations().send();
            List<byte[]> drugIDList = tuple3.getValue1();
            List<String> drugNameList = tuple3.getValue2();
            List<List<String>> materialList = tuple3.getValue3();
            List<FormulationVO> formulationVOList = new ArrayList<>();
            for (int i = 0; i < drugIDList.size(); ++i) {
                formulationVOList.add(new FormulationVO(new String(drugIDList.get(i)), drugNameList.get(i), materialList.get(i)));
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
            Tuple2<List<String>, List<String>> tuple2 = medicineSourceTracing.getTransporters().send();
            List<String> transporterAddrList = tuple2.getValue1();
            List<String> transporterNameList = tuple2.getValue2();
            List<TransporterVO> transporterVOList = new ArrayList<>();
            for (int i = 0; i < transporterAddrList.size(); ++i) {
                transporterVOList.add(new TransporterVO(transporterAddrList.get(i), transporterNameList.get(i)));
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
            Tuple3<List<String>, List<String>, List<BigInteger>> tuple3 = medicineSourceTracing.getSellers().send();
            List<String> sellerAddrList = tuple3.getValue1();
            List<String> sellerNameList = tuple3.getValue2();
            List<BigInteger> sellerTypeList = tuple3.getValue3();
            List<SellerVO> sellerVOList = new ArrayList<>();
            for (int i = 0; i < sellerAddrList.size(); ++i) {
                sellerVOList.add(new SellerVO(sellerAddrList.get(i), sellerNameList.get(i), sellerTypeList.get(i).intValue()));
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
            List<String> packageIDList = medicineSourceTracing.getPackInfo(boxID.getBytes()).send();
            return ServerResponse.createBySuccess(packageIDList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.createByError();
    }

    @Override
    public ServerResponse<List<FeedBackVO>> getFeedBacks(String drugID) {
        try {
            Tuple5<List<byte[]>, List<String>, List<BigInteger>, List<BigInteger>, List<BigInteger>> tuple5 = medicineSourceTracing.getFeedBacks(drugID.getBytes()).send();
            List<byte[]> packageIDList = tuple5.getValue1();
            List<String> informationList = tuple5.getValue2();
            List<BigInteger> ageList = tuple5.getValue3();
            List<BigInteger> genderList = tuple5.getValue4();
            List<BigInteger> timeList = tuple5.getValue5();
            List<FeedBackVO> feedBackVOList = new ArrayList<>();
            for (int i = 0; i < packageIDList.size(); ++i) {
                feedBackVOList.add(new FeedBackVO(
                        new String(packageIDList.get(i)),
                        informationList.get(i),
                        ageList.get(i).intValue(),
                        genderList.get(i).intValue(),
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timeList.get(i).longValue() * 1000))));
            }
            return ServerResponse.createBySuccess(feedBackVOList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.createByError();
    }
}
