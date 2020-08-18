package cn.edu.zju.drugtracing.contract;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple8;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import rx.Observable;
import rx.functions.Func1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.6.0.
 */
public class MedicineSourceTracing extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50611d50806100206000396000f3006080604052600436106100b95763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166308e9356081146100be578063122e1330146101295780631325666f146103d2578063194f1023146103f757806329997426146104265780633e60acec146104cc5780636419492e146105455780639067f9ff146105ac5780639c700eb6146105e6578063bf33afa51461065f578063e13992a314610690578063e26d82ac146106b7575b600080fd5b3480156100ca57600080fd5b5060408051602060046024803582810135601f8101859004850286018501909652858552610127958335600160a060020a0316953695604494919390910191908190840183828082843750949750509335945061071e9350505050565b005b34801561013557600080fd5b5061014b600160b860020a03196004351661088b565b6040518080602001806020018060200180602001898152602001806020018881526020018060200187810387528f818151815260200191508051906020019080838360005b838110156101a8578181015183820152602001610190565b50505050905090810190601f1680156101d55780820380516001836020036101000a031916815260200191505b5087810386528e818151815260200191508051906020019080838360005b8381101561020b5781810151838201526020016101f3565b50505050905090810190601f1680156102385780820380516001836020036101000a031916815260200191505b5087810385528d5181528d516020918201918f019080838360005b8381101561026b578181015183820152602001610253565b50505050905090810190601f1680156102985780820380516001836020036101000a031916815260200191505b5087810384528c5181528c516020918201918e019080838360005b838110156102cb5781810151838201526020016102b3565b50505050905090810190601f1680156102f85780820380516001836020036101000a031916815260200191505b5087810383528a5181528a516020918201918c019080838360005b8381101561032b578181015183820152602001610313565b50505050905090810190601f1680156103585780820380516001836020036101000a031916815260200191505b5087810382528851815288516020918201918a019080838360005b8381101561038b578181015183820152602001610373565b50505050905090810190601f1680156103b85780820380516001836020036101000a031916815260200191505b509e50505050505050505050505050505060405180910390f35b3480156103de57600080fd5b50610127600160d060020a031960043516602435610dfc565b34801561040357600080fd5b50610127600160b860020a031960043516600160d060020a031960243516610f1c565b34801561043257600080fd5b5060408051602060046024803582810135601f8101859004850286018501909652858552610127958335600160e860020a03191695369560449491939091019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a9998810197919650918201945092508291508401838280828437509497506112109650505050505050565b3480156104d857600080fd5b50604080516020601f60643560048181013592830184900484028501840190955281845261012794600160d060020a031981351694600160a060020a0360248035919091169560443595369560849493019181908401838280828437509497506113cb9650505050505050565b34801561055157600080fd5b5060408051602060046024803582810135601f8101859004850286018501909652858552610127958335600160a060020a03169536956044949193909101919081908401838280828437509497506115b69650505050505050565b3480156105b857600080fd5b50610127600160b860020a031960043516602435600160a060020a0360443581169060643516608435611702565b3480156105f257600080fd5b50604080516020601f60643560048181013592830184900484028501840190955281845261012794600160b860020a031981351694600160a060020a0360248035919091169560443595369560849493019181908401838280828437509497506118179650505050505050565b34801561066b57600080fd5b50610127600160d060020a031960043516602435600160a060020a0360443516611923565b34801561069c57600080fd5b50610127600160a060020a03600435166024356044356119d7565b3480156106c357600080fd5b5060408051602060046024803582810135601f8101859004850286018501909652858552610127958335600160a060020a0316953695604494919390910191908190840183828082843750949750611b1c9650505050505050565b6000600160a060020a0384161515610734573393505b600160a060020a0384811660009081526006602052604090205416151561075d57506000610761565b5060015b600160a060020a03841660008181526006602090815260409091208054600160a060020a0319169092178255845161079f9260010191860190611c69565b50600160a060020a03841660009081526006602052604090206002018290558015156108305760408051338152602081018290526012818301527fe6b3a8e5868ce794a8e688b7e68890e58a9f0000000000000000000000000000606082015290517f836d55c39f69ec3edcbd65dfdd397491063b2f49844b9c70468094fa6d6932809181900360800190a1610885565b6040805133815260208101829052601881830152600080516020611d05833981519152606082015290517f836d55c39f69ec3edcbd65dfdd397491063b2f49844b9c70468094fa6d6932809181900360800190a15b50505050565b600160e860020a031981166000908152600160208181526040808420830180548251600295821615610100026000190190911694909404601f810184900484028501840190925281845260609485948594859492938593859385938c93849387939283018282801561093e5780601f106109135761010080835404028352916020019161093e565b820191906000526020600020905b81548152906001019060200180831161092157829003601f168201915b50505050509a5060016000847cffffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167cffffffffffffffffffffffffffffffffffffffffffffffffffffffffff191681526020019081526020016000206002018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610a2f5780601f10610a0457610100808354040283529160200191610a2f565b820191906000526020600020905b815481529060010190602001808311610a1257829003601f168201915b50505050600160d060020a031984166000908152600360209081526040918290206002908101805484516001821615610100026000190190911692909204601f8101849004840283018401909452838252949e50939250830182828015610ad75780601f10610aac57610100808354040283529160200191610ad7565b820191906000526020600020905b815481529060010190602001808311610aba57829003601f168201915b50505050600160d060020a0319841660009081526003602090815260408083205466010000000000009004600160a060020a031680845283835292819020600190810180548351600261010094831615949094026000190190911692909204601f8101859004850283018501909352828252959e5092955091939250830182828015610ba45780601f10610b7957610100808354040283529160200191610ba4565b820191906000526020600020905b815481529060010190602001808311610b8757829003601f168201915b50505050600160d060020a0319841660009081526005602090815260408083206003810154600191820154600160a060020a03168086526004855294839020820180548451600261010095831615959095026000190190911693909304601f8101869004860284018601909452838352969e509c5092955091939250830182828015610c715780601f10610c4657610100808354040283529160200191610c71565b820191906000526020600020905b815481529060010190602001808311610c5457829003601f168201915b50505050509550600560008379ffffffffffffffffffffffffffffffffffffffffffffffffffff191679ffffffffffffffffffffffffffffffffffffffffffffffffffff19168152602001908152602001600020600401549450600760008d76ffffffffffffffffffffffffffffffffffffffffffffff191676ffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060020160009054906101000a9004600160a060020a031690506006600082600160a060020a0316600160a060020a031681526020019081526020016000206001018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610de75780601f10610dbc57610100808354040283529160200191610de7565b820191906000526020600020905b815481529060010190602001808311610dca57829003601f168201915b50505050509350505050919395975091939597565b801515610e065750425b600160d060020a031982166000908152600560208181526040808420805465ffffffffffff19167a0100000000000000000000000000000000000000000000000000008804178082556003808552958390205494845279ffffffffffffffffffffffffffffffffffffffff0000000000001916660100000000000094859004600160a060020a031690940293909317835560018301805433600160a060020a031990911681179091559390920184905581519283528201819052600c828201527fe58f96e8b4a7e68890e58a9f00000000000000000000000000000000000000006060830152517f10c619fde6fbf408e12064b3449937d39dad8bd183b33e648a13f8b66e426482916080908290030190a15050565b600160d060020a031981166000908152600360205260408120600101541515610fd457604080513381526000602082015260608183018190526027908201527fe5a4a7e58c85e4bfa1e681afe4b88de5ad98e59ca8efbc8ce8afb7e9878de69660808201527fb0e8be93e585a50000000000000000000000000000000000000000000000000060a082015290517fb9ed22041e016db14768f807c15b0339719e13911da48e643021140af7dc00499181900360c00190a15b5060005b600681101561112957818160068110610fed57fe5b1a7f0100000000000000000000000000000000000000000000000000000000000000027effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916838260098110151561104057fe5b1a7f0100000000000000000000000000000000000000000000000000000000000000027effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff191614151561112157604080513381526000602082015260608183018190526024908201527fe4bfa1e681afe8be93e585a5e69c89e8afafefbc8ce8afb7e9878de696b0e8be60808201527f93e585a50000000000000000000000000000000000000000000000000000000060a082015290517fb9ed22041e016db14768f807c15b0339719e13911da48e643021140af7dc00499181900360c00190a15b600101610fd8565b600160d060020a031982166000908152600260209081526040808320805460018082018355918552938390206003808604909101805468ffffffffffffffffff929096066009026101000a91820219909516770100000000000000000000000000000000000000000000008904919091021790935580513381529182019290925260608183018190526018908201527fe58c85e8a385e4bfa1e681afe4b88ae4bca0e68890e58a9f0000000000000000608082015290517fb9ed22041e016db14768f807c15b0339719e13911da48e643021140af7dc00499160a0908290030190a1505050565b600160e860020a03198316600090815260016020526040812060030154151561123b5750600061123f565b5060015b600160e860020a031984166000908152600160208181526040909220805462ffffff19167d0100000000000000000000000000000000000000000000000000000000008804178155855161129b93919092019190860190611c69565b50600160e860020a03198416600090815260016020908152604090912083516112cc92600290920191850190611c69565b50600160e860020a0319841660009081526001602081905260409091206003015580151561135f5760408051338152602081018290526015818301527fe696b0e9858de696b9e4b88ae4bca0e68890e58a9f0000000000000000000000606082015290517f40e944dfe91e2b6e9a24304248cf1170577ed48092278dd53c8f51df48c945369181900360800190a1610885565b6040805133815260208101829052601e818301527fe9858de696b9e5b7b2e5ad98e59ca8efbc8ce4bfaee694b9e68890e58a9f0000606082015290517f40e944dfe91e2b6e9a24304248cf1170577ed48092278dd53c8f51df48c945369181900360800190a150505050565b6000600160a060020a03841615156113e1573393505b8215156113ec574292505b600160d060020a0319851660009081526003602052604090206001015415156114175750600061141b565b5060015b600160d060020a031985166000908152600360209081526040909120805465ffffffffffff19167a01000000000000000000000000000000000000000000000000000088041779ffffffffffffffffffffffffffffffffffffffff00000000000019166601000000000000600160a060020a038816021781556001810185905583516114af92600290920191850190611c69565b50801515611522576040805133815260208101829052601e818301527fe88dafe59381e7949fe4baa7e4bfa1e681afe4b88ae4bca0e68890e58a9f0000606082015290517f12959ddadb0791d3d9525fb191508347ecd579d8d81710c0b8c8101ff916d9619181900360800190a16115af565b6040805133815260208101829052602a818301527fe88dafe59381e4bfa1e681afe5b7b2e5ad98e59ca8efbc8ce4bfa1e681afe69b60608201527fb4e696b0e68890e58a9f00000000000000000000000000000000000000000000608082015290517f12959ddadb0791d3d9525fb191508347ecd579d8d81710c0b8c8101ff916d9619181900360a00190a15b5050505050565b6000600160a060020a03831615156115cc573392505b600160a060020a038381166000908152602081905260409020541615156115f5575060006115f9565b5060015b600160a060020a0383166000818152602081815260409091208054600160a060020a031916909217825583516116359260010191850190611c69565b508015156116a85760408051338152602081018290526012818301527fe794a8e688b7e6b3a8e5868ce68890e58a9f0000000000000000000000000000606082015290517f8d61f84d8015a8bc5d655672779571797f897ed10d3c4f75305db701ea94cb539181900360800190a16116fd565b6040805133815260208101829052601881830152600080516020611d05833981519152606082015290517f8d61f84d8015a8bc5d655672779571797f897ed10d3c4f75305db701ea94cb539181900360800190a15b505050565b83151561170d574293505b600160a060020a0383161515611721573392505b600160b860020a03198516600090815260076020908152604091829020805477010000000000000000000000000000000000000000000000890468ffffffffffffffffff1990911617815560018101879055600281018054600160a060020a03808916600160a060020a0319928316179092556003830180549288169290911691909117905560040183905581513381529081018290526018818301527fe99bb6e594aee4bfa1e681afe4b88ae4bca0e68890e58a9f0000000000000000606082015290517fc4c9aee1ab8b04db189df658309a09c8f08ad250e0e054849c41493b1bbf074b9181900360800190a15050505050565b811515611822574291505b600160a060020a0383161515611836573392505b600160b860020a03198416600090815260096020908152604090912060028101805468ffffffffffffffffff19167701000000000000000000000000000000000000000000000088041790558054600160a060020a031916600160a060020a0386161781556001810184905582516118b692600390920191840190611c69565b5060408051338152602081018290526012818301527fe4bfa1e681afe58f8de9a688e68890e58a9f0000000000000000000000000000606082015290517f9803f731331353fd1c6d22494bcae3304ec4c90f9d03a1b8cc90d71c7e34fcdd9181900360800190a150505050565b81151561192e574291505b600160d060020a03198316600090815260056020908152604091829020600481018590556002018054600160a060020a031916600160a060020a0385161790558151338152908101829052600c818301527fe98081e8bebee68890e58a9f0000000000000000000000000000000000000000606082015290517f658735442677b91bc6ddae2fd4122d062219166d13c6c0eb2a3f7bdf656b6226916080908290030190a1505050565b6000600160a060020a03841615156119ed573393505b600160a060020a03848116600090815260086020526040902054161515611a1657506000611a1a565b5060015b600160a060020a03841660008181526008602052604090208054600160a060020a031916909117815560018101849055600201829055801515611ac25760408051338152602081018290526012818301527fe6b3a8e5868ce794a8e688b7e68890e58a9f0000000000000000000000000000606082015290517f9df22cde4061d380418e4e207ef95c22c561c7c58bb56a988919dca8e8b209479181900360800190a1610885565b6040805133815260208101829052601881830152600080516020611d05833981519152606082015290517f9df22cde4061d380418e4e207ef95c22c561c7c58bb56a988919dca8e8b209479181900360800190a150505050565b6000600160a060020a0383161515611b32573392505b600160a060020a03838116600090815260046020526040902054161515611b5b57506000611b5f565b5060015b600160a060020a03831660008181526004602090815260409091208054600160a060020a03191690921782558351611b9d9260010191850190611c69565b50801515611c105760408051338152602081018290526012818301527fe6b3a8e5868ce794a8e688b7e68890e58a9f0000000000000000000000000000606082015290517f5746aa2026b6cce5ab18d1dd958f355630be00088fe639c090e6c252cc720c359181900360800190a16116fd565b6040805133815260208101829052601881830152600080516020611d05833981519152606082015290517f5746aa2026b6cce5ab18d1dd958f355630be00088fe639c090e6c252cc720c359181900360800190a1505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10611caa57805160ff1916838001178555611cd7565b82800160010185558215611cd7579182015b82811115611cd7578251825591602001919060010190611cbc565b50611ce3929150611ce7565b5090565b611d0191905b80821115611ce35760008155600101611ced565b905600e794a8e688b7e4bfa1e681afe4bfaee694b9e68890e58a9f0000000000000000a165627a7a7230582092dabfcc765659fb2f805e7f4ceb114e770b416a64b95a918c641515cab8c0b40029";

    public static final String FUNC_SETSELLER = "setSeller";

    public static final String FUNC_TRACE = "trace";

    public static final String FUNC_PICK = "pick";

    public static final String FUNC_PACK = "pack";

    public static final String FUNC_SETFORMULATION = "setFormulation";

    public static final String FUNC_SETBOXINFO = "setBoxInfo";

    public static final String FUNC_SETMANUFACTURER = "setManufacturer";

    public static final String FUNC_SETSELLINFO = "setSellInfo";

    public static final String FUNC_FEEDBACK = "feedBack";

    public static final String FUNC_DROP = "drop";

    public static final String FUNC_SETCONSUMER = "setConsumer";

    public static final String FUNC_SETTRANSPORTER = "setTransporter";

    public static final Event NEWMANUFACTURER_EVENT = new Event("newManufacturer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event NEWTRANSPORTER_EVENT = new Event("newTransporter", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event NEWSELLER_EVENT = new Event("newSeller", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event NEWCONSUMER_EVENT = new Event("newConsumer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event NEWPACKINFO_EVENT = new Event("newPackInfo", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bool>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event NEWFORMULATION_EVENT = new Event("newFormulation", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event NEWBOXINFO_EVENT = new Event("newBoxInfo", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event NEWPICKINFO_EVENT = new Event("newPickInfo", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event NEWDROPINFO_EVENT = new Event("newDropInfo", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event NEWSELLINFO_EVENT = new Event("newSellInfo", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event NEWFEEDBACK_EVENT = new Event("newFeedBack", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected MedicineSourceTracing(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected MedicineSourceTracing(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected MedicineSourceTracing(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected MedicineSourceTracing(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> setSeller(String sellerAddr, byte[] sellerName, BigInteger sellerType) {
        final Function function = new Function(
                FUNC_SETSELLER, 
                Arrays.<Type>asList(new Address(sellerAddr),
                new DynamicBytes(sellerName),
                new Uint256(sellerType)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple8<byte[], byte[], byte[], byte[], BigInteger, byte[], BigInteger, byte[]>> trace(byte[] packageID) {
        final Function function = new Function(FUNC_TRACE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes9(packageID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicBytes>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicBytes>() {}));
        return new RemoteCall<Tuple8<byte[], byte[], byte[], byte[], BigInteger, byte[], BigInteger, byte[]>>(
                new Callable<Tuple8<byte[], byte[], byte[], byte[], BigInteger, byte[], BigInteger, byte[]>>() {
                    @Override
                    public Tuple8<byte[], byte[], byte[], byte[], BigInteger, byte[], BigInteger, byte[]> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple8<byte[], byte[], byte[], byte[], BigInteger, byte[], BigInteger, byte[]>(
                                (byte[]) results.get(0).getValue(), 
                                (byte[]) results.get(1).getValue(), 
                                (byte[]) results.get(2).getValue(), 
                                (byte[]) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (byte[]) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue(), 
                                (byte[]) results.get(7).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> pick(byte[] boxID, BigInteger time) {
        final Function function = new Function(
                FUNC_PICK, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes6(boxID), 
                new Uint256(time)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> pack(byte[] packageID, byte[] boxID) {
        final Function function = new Function(
                FUNC_PACK, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes9(packageID), 
                new org.web3j.abi.datatypes.generated.Bytes6(boxID)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setFormulation(byte[] drugID, byte[] drugName, byte[] material) {
        final Function function = new Function(
                FUNC_SETFORMULATION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes3(drugID), 
                new DynamicBytes(drugName),
                new DynamicBytes(material)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setBoxInfo(byte[] boxID, String manufacturerAddr, BigInteger time, byte[] materialID) {
        final Function function = new Function(
                FUNC_SETBOXINFO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes6(boxID), 
                new Address(manufacturerAddr),
                new Uint256(time),
                new DynamicBytes(materialID)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setManufacturer(String manufacturerAddr, byte[] manufacturerName) {
        final Function function = new Function(
                FUNC_SETMANUFACTURER, 
                Arrays.<Type>asList(new Address(manufacturerAddr),
                new DynamicBytes(manufacturerName)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setSellInfo(byte[] packageID, BigInteger time, String sellerAddr, String consumerAddr, BigInteger price) {
        final Function function = new Function(
                FUNC_SETSELLINFO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes9(packageID), 
                new Uint256(time),
                new Address(sellerAddr),
                new Address(consumerAddr),
                new Uint256(price)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> feedBack(byte[] packageID, String consumerAddr, BigInteger time, byte[] information) {
        final Function function = new Function(
                FUNC_FEEDBACK, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes9(packageID), 
                new Address(consumerAddr),
                new Uint256(time),
                new DynamicBytes(information)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> drop(byte[] boxID, BigInteger time, String sellerAddr) {
        final Function function = new Function(
                FUNC_DROP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes6(boxID), 
                new Uint256(time),
                new Address(sellerAddr)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setConsumer(String consumerAddr, BigInteger gender, BigInteger age) {
        final Function function = new Function(
                FUNC_SETCONSUMER, 
                Arrays.<Type>asList(new Address(consumerAddr),
                new Uint256(gender),
                new Uint256(age)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setTransporter(String transporterAddr, byte[] transporterName) {
        final Function function = new Function(
                FUNC_SETTRANSPORTER, 
                Arrays.<Type>asList(new Address(transporterAddr),
                new DynamicBytes(transporterName)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public List<NewManufacturerEventResponse> getNewManufacturerEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(NEWMANUFACTURER_EVENT, transactionReceipt);
        ArrayList<NewManufacturerEventResponse> responses = new ArrayList<NewManufacturerEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            NewManufacturerEventResponse typedResponse = new NewManufacturerEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.message = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<NewManufacturerEventResponse> newManufacturerEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, NewManufacturerEventResponse>() {
            @Override
            public NewManufacturerEventResponse call(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(NEWMANUFACTURER_EVENT, log);
                NewManufacturerEventResponse typedResponse = new NewManufacturerEventResponse();
                typedResponse.log = log;
                typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.message = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<NewManufacturerEventResponse> newManufacturerEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWMANUFACTURER_EVENT));
        return newManufacturerEventObservable(filter);
    }

    public List<NewTransporterEventResponse> getNewTransporterEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(NEWTRANSPORTER_EVENT, transactionReceipt);
        ArrayList<NewTransporterEventResponse> responses = new ArrayList<NewTransporterEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            NewTransporterEventResponse typedResponse = new NewTransporterEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.message = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<NewTransporterEventResponse> newTransporterEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, NewTransporterEventResponse>() {
            @Override
            public NewTransporterEventResponse call(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(NEWTRANSPORTER_EVENT, log);
                NewTransporterEventResponse typedResponse = new NewTransporterEventResponse();
                typedResponse.log = log;
                typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.message = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<NewTransporterEventResponse> newTransporterEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWTRANSPORTER_EVENT));
        return newTransporterEventObservable(filter);
    }

    public List<NewSellerEventResponse> getNewSellerEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(NEWSELLER_EVENT, transactionReceipt);
        ArrayList<NewSellerEventResponse> responses = new ArrayList<NewSellerEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            NewSellerEventResponse typedResponse = new NewSellerEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.message = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<NewSellerEventResponse> newSellerEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, NewSellerEventResponse>() {
            @Override
            public NewSellerEventResponse call(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(NEWSELLER_EVENT, log);
                NewSellerEventResponse typedResponse = new NewSellerEventResponse();
                typedResponse.log = log;
                typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.message = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<NewSellerEventResponse> newSellerEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWSELLER_EVENT));
        return newSellerEventObservable(filter);
    }

    public List<NewConsumerEventResponse> getNewConsumerEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(NEWCONSUMER_EVENT, transactionReceipt);
        ArrayList<NewConsumerEventResponse> responses = new ArrayList<NewConsumerEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            NewConsumerEventResponse typedResponse = new NewConsumerEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.message = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<NewConsumerEventResponse> newConsumerEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, NewConsumerEventResponse>() {
            @Override
            public NewConsumerEventResponse call(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(NEWCONSUMER_EVENT, log);
                NewConsumerEventResponse typedResponse = new NewConsumerEventResponse();
                typedResponse.log = log;
                typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.message = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<NewConsumerEventResponse> newConsumerEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWCONSUMER_EVENT));
        return newConsumerEventObservable(filter);
    }

    public List<NewPackInfoEventResponse> getNewPackInfoEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(NEWPACKINFO_EVENT, transactionReceipt);
        ArrayList<NewPackInfoEventResponse> responses = new ArrayList<NewPackInfoEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            NewPackInfoEventResponse typedResponse = new NewPackInfoEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.isSuccess = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.message = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<NewPackInfoEventResponse> newPackInfoEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, NewPackInfoEventResponse>() {
            @Override
            public NewPackInfoEventResponse call(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(NEWPACKINFO_EVENT, log);
                NewPackInfoEventResponse typedResponse = new NewPackInfoEventResponse();
                typedResponse.log = log;
                typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.isSuccess = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.message = (String) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<NewPackInfoEventResponse> newPackInfoEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWPACKINFO_EVENT));
        return newPackInfoEventObservable(filter);
    }

    public List<NewFormulationEventResponse> getNewFormulationEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(NEWFORMULATION_EVENT, transactionReceipt);
        ArrayList<NewFormulationEventResponse> responses = new ArrayList<NewFormulationEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            NewFormulationEventResponse typedResponse = new NewFormulationEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.message = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<NewFormulationEventResponse> newFormulationEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, NewFormulationEventResponse>() {
            @Override
            public NewFormulationEventResponse call(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(NEWFORMULATION_EVENT, log);
                NewFormulationEventResponse typedResponse = new NewFormulationEventResponse();
                typedResponse.log = log;
                typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.message = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<NewFormulationEventResponse> newFormulationEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWFORMULATION_EVENT));
        return newFormulationEventObservable(filter);
    }

    public List<NewBoxInfoEventResponse> getNewBoxInfoEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(NEWBOXINFO_EVENT, transactionReceipt);
        ArrayList<NewBoxInfoEventResponse> responses = new ArrayList<NewBoxInfoEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            NewBoxInfoEventResponse typedResponse = new NewBoxInfoEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.message = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<NewBoxInfoEventResponse> newBoxInfoEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, NewBoxInfoEventResponse>() {
            @Override
            public NewBoxInfoEventResponse call(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(NEWBOXINFO_EVENT, log);
                NewBoxInfoEventResponse typedResponse = new NewBoxInfoEventResponse();
                typedResponse.log = log;
                typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.message = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<NewBoxInfoEventResponse> newBoxInfoEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWBOXINFO_EVENT));
        return newBoxInfoEventObservable(filter);
    }

    public List<NewPickInfoEventResponse> getNewPickInfoEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(NEWPICKINFO_EVENT, transactionReceipt);
        ArrayList<NewPickInfoEventResponse> responses = new ArrayList<NewPickInfoEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            NewPickInfoEventResponse typedResponse = new NewPickInfoEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.message = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<NewPickInfoEventResponse> newPickInfoEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, NewPickInfoEventResponse>() {
            @Override
            public NewPickInfoEventResponse call(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(NEWPICKINFO_EVENT, log);
                NewPickInfoEventResponse typedResponse = new NewPickInfoEventResponse();
                typedResponse.log = log;
                typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.message = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<NewPickInfoEventResponse> newPickInfoEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWPICKINFO_EVENT));
        return newPickInfoEventObservable(filter);
    }

    public List<NewDropInfoEventResponse> getNewDropInfoEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(NEWDROPINFO_EVENT, transactionReceipt);
        ArrayList<NewDropInfoEventResponse> responses = new ArrayList<NewDropInfoEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            NewDropInfoEventResponse typedResponse = new NewDropInfoEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.message = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<NewDropInfoEventResponse> newDropInfoEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, NewDropInfoEventResponse>() {
            @Override
            public NewDropInfoEventResponse call(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(NEWDROPINFO_EVENT, log);
                NewDropInfoEventResponse typedResponse = new NewDropInfoEventResponse();
                typedResponse.log = log;
                typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.message = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<NewDropInfoEventResponse> newDropInfoEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWDROPINFO_EVENT));
        return newDropInfoEventObservable(filter);
    }

    public List<NewSellInfoEventResponse> getNewSellInfoEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(NEWSELLINFO_EVENT, transactionReceipt);
        ArrayList<NewSellInfoEventResponse> responses = new ArrayList<NewSellInfoEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            NewSellInfoEventResponse typedResponse = new NewSellInfoEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.message = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<NewSellInfoEventResponse> newSellInfoEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, NewSellInfoEventResponse>() {
            @Override
            public NewSellInfoEventResponse call(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(NEWSELLINFO_EVENT, log);
                NewSellInfoEventResponse typedResponse = new NewSellInfoEventResponse();
                typedResponse.log = log;
                typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.message = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<NewSellInfoEventResponse> newSellInfoEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWSELLINFO_EVENT));
        return newSellInfoEventObservable(filter);
    }

    public List<NewFeedBackEventResponse> getNewFeedBackEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(NEWFEEDBACK_EVENT, transactionReceipt);
        ArrayList<NewFeedBackEventResponse> responses = new ArrayList<NewFeedBackEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            NewFeedBackEventResponse typedResponse = new NewFeedBackEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.message = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<NewFeedBackEventResponse> newFeedBackEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, NewFeedBackEventResponse>() {
            @Override
            public NewFeedBackEventResponse call(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(NEWFEEDBACK_EVENT, log);
                NewFeedBackEventResponse typedResponse = new NewFeedBackEventResponse();
                typedResponse.log = log;
                typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.message = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<NewFeedBackEventResponse> newFeedBackEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWFEEDBACK_EVENT));
        return newFeedBackEventObservable(filter);
    }

    public static RemoteCall<MedicineSourceTracing> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(MedicineSourceTracing.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<MedicineSourceTracing> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(MedicineSourceTracing.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<MedicineSourceTracing> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(MedicineSourceTracing.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<MedicineSourceTracing> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(MedicineSourceTracing.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static MedicineSourceTracing load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new MedicineSourceTracing(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static MedicineSourceTracing load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new MedicineSourceTracing(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static MedicineSourceTracing load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new MedicineSourceTracing(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static MedicineSourceTracing load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new MedicineSourceTracing(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static class NewManufacturerEventResponse {
        public Log log;

        public String sender;

        public String message;
    }

    public static class NewTransporterEventResponse {
        public Log log;

        public String sender;

        public String message;
    }

    public static class NewSellerEventResponse {
        public Log log;

        public String sender;

        public String message;
    }

    public static class NewConsumerEventResponse {
        public Log log;

        public String sender;

        public String message;
    }

    public static class NewPackInfoEventResponse {
        public Log log;

        public String sender;

        public Boolean isSuccess;

        public String message;
    }

    public static class NewFormulationEventResponse {
        public Log log;

        public String sender;

        public String message;
    }

    public static class NewBoxInfoEventResponse {
        public Log log;

        public String sender;

        public String message;
    }

    public static class NewPickInfoEventResponse {
        public Log log;

        public String sender;

        public String message;
    }

    public static class NewDropInfoEventResponse {
        public Log log;

        public String sender;

        public String message;
    }

    public static class NewSellInfoEventResponse {
        public Log log;

        public String sender;

        public String message;
    }

    public static class NewFeedBackEventResponse {
        public Log log;

        public String sender;

        public String message;
    }
}
