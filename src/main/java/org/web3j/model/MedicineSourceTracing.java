package org.web3j.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
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
    private static final String BINARY = "608060405234801561001057600080fd5b506125fd806100206000396000f3006080604052600436106100b95763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166308e9356081146100be578063122e1330146101295780631325666f146103d2578063194f1023146103f757806329997426146104265780633e60acec146104cc5780636419492e146105455780639067f9ff146105ac5780639c700eb6146105e6578063bf33afa51461065f578063e13992a314610690578063e26d82ac146106b7575b600080fd5b3480156100ca57600080fd5b5060408051602060046024803582810135601f8101859004850286018501909652858552610127958335600160a060020a0316953695604494919390910191908190840183828082843750949750509335945061071e9350505050565b005b34801561013557600080fd5b5061014b600160b860020a031960043516610894565b6040518080602001806020018060200180602001898152602001806020018881526020018060200187810387528f818151815260200191508051906020019080838360005b838110156101a8578181015183820152602001610190565b50505050905090810190601f1680156101d55780820380516001836020036101000a031916815260200191505b5087810386528e818151815260200191508051906020019080838360005b8381101561020b5781810151838201526020016101f3565b50505050905090810190601f1680156102385780820380516001836020036101000a031916815260200191505b5087810385528d5181528d516020918201918f019080838360005b8381101561026b578181015183820152602001610253565b50505050905090810190601f1680156102985780820380516001836020036101000a031916815260200191505b5087810384528c5181528c516020918201918e019080838360005b838110156102cb5781810151838201526020016102b3565b50505050905090810190601f1680156102f85780820380516001836020036101000a031916815260200191505b5087810383528a5181528a516020918201918c019080838360005b8381101561032b578181015183820152602001610313565b50505050905090810190601f1680156103585780820380516001836020036101000a031916815260200191505b5087810382528851815288516020918201918a019080838360005b8381101561038b578181015183820152602001610373565b50505050905090810190601f1680156103b85780820380516001836020036101000a031916815260200191505b509e50505050505050505050505050505060405180910390f35b3480156103de57600080fd5b50610127600160d060020a031960043516602435610e05565b34801561040357600080fd5b50610127600160b860020a031960043516600160d060020a03196024351661109f565b34801561043257600080fd5b5060408051602060046024803582810135601f8101859004850286018501909652858552610127958335600160e860020a03191695369560449491939091019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a9998810197919650918201945092508291508401838280828437509497506114069650505050505050565b3480156104d857600080fd5b50604080516020601f60643560048181013592830184900484028501840190955281845261012794600160d060020a031981351694600160a060020a0360248035919091169560443595369560849493019181908401838280828437509497506116839650505050505050565b34801561055157600080fd5b5060408051602060046024803582810135601f8101859004850286018501909652858552610127958335600160a060020a031695369560449491939091019190819084018382808284375094975061192e9650505050505050565b3480156105b857600080fd5b50610127600160b860020a031960043516602435600160a060020a0360443581169060643516608435611a99565b3480156105f257600080fd5b50604080516020601f60643560048181013592830184900484028501840190955281845261012794600160b860020a031981351694600160a060020a036024803591909116956044359536956084949301918190840183828082843750949750611da69650505050505050565b34801561066b57600080fd5b50610127600160d060020a031960043516602435600160a060020a0360443516611f82565b34801561069c57600080fd5b50610127600160a060020a03600435166024356044356121fc565b3480156106c357600080fd5b5060408051602060046024803582810135601f8101859004850286018501909652858552610127958335600160a060020a03169536956044949193909101919081908401838280828437509497506123499650505050505050565b6000600160a060020a0384161515610734573393505b600160a060020a0384811660009081526006602052604090205416151561075d57506000610761565b5060015b600160a060020a03841660008181526006602090815260409091208054600160a060020a0319169092178255845161079f92600101918601906124b6565b50600160a060020a03841660009081526006602052604090206002810183905560016003909101558015156108395760408051338152602081018290526012818301527fe6b3a8e5868ce794a8e688b7e68890e58a9f0000000000000000000000000000606082015290517f836d55c39f69ec3edcbd65dfdd397491063b2f49844b9c70468094fa6d6932809181900360800190a161088e565b60408051338152602081018290526018818301526000805160206125b2833981519152606082015290517f836d55c39f69ec3edcbd65dfdd397491063b2f49844b9c70468094fa6d6932809181900360800190a15b50505050565b600160e860020a031981166000908152600160208181526040808420830180548251600295821615610100026000190190911694909404601f810184900484028501840190925281845260609485948594859492938593859385938c9384938793928301828280156109475780601f1061091c57610100808354040283529160200191610947565b820191906000526020600020905b81548152906001019060200180831161092a57829003601f168201915b50505050509a5060016000847cffffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167cffffffffffffffffffffffffffffffffffffffffffffffffffffffffff191681526020019081526020016000206002018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610a385780601f10610a0d57610100808354040283529160200191610a38565b820191906000526020600020905b815481529060010190602001808311610a1b57829003601f168201915b50505050600160d060020a031984166000908152600360209081526040918290206002908101805484516001821615610100026000190190911692909204601f8101849004840283018401909452838252949e50939250830182828015610ae05780601f10610ab557610100808354040283529160200191610ae0565b820191906000526020600020905b815481529060010190602001808311610ac357829003601f168201915b50505050600160d060020a0319841660009081526003602090815260408083205466010000000000009004600160a060020a031680845283835292819020600190810180548351600261010094831615949094026000190190911692909204601f8101859004850283018501909352828252959e5092955091939250830182828015610bad5780601f10610b8257610100808354040283529160200191610bad565b820191906000526020600020905b815481529060010190602001808311610b9057829003601f168201915b50505050600160d060020a0319841660009081526005602090815260408083206003810154600191820154600160a060020a03168086526004855294839020820180548451600261010095831615959095026000190190911693909304601f8101869004860284018601909452838352969e509c5092955091939250830182828015610c7a5780601f10610c4f57610100808354040283529160200191610c7a565b820191906000526020600020905b815481529060010190602001808311610c5d57829003601f168201915b50505050509550600560008379ffffffffffffffffffffffffffffffffffffffffffffffffffff191679ffffffffffffffffffffffffffffffffffffffffffffffffffff19168152602001908152602001600020600401549450600760008d76ffffffffffffffffffffffffffffffffffffffffffffff191676ffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060020160009054906101000a9004600160a060020a031690506006600082600160a060020a0316600160a060020a031681526020019081526020016000206001018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610df05780601f10610dc557610100808354040283529160200191610df0565b820191906000526020600020905b815481529060010190602001808311610dd357829003601f168201915b50505050509350505050919395975091939597565b336000908152600460205260409020600201541515610eb757604080513381526000602082015260608183018190526027908201527fe5bd93e5898de794a8e688b7e697a0e4b88ae4bca0e58f96e8b4a7e4bfa1e68160808201527fafe69d83e999900000000000000000000000000000000000000000000000000060a082015290517fd156dc9e38c2e6173a4862f6563ea573ab87269a917bdabe0a5d9d7fdb2b32e99181900360c00190a161109b565b600160d060020a031982166000908152600360205260409020600101541515610f735760408051338152600060208201526060818301819052602a908201527fe88dafe59381e5a4a7e58c85e4bfa1e681afe4b88de5ad98e59ca8efbc8ce69760808201527fa0e6b395e58f96e8b4a70000000000000000000000000000000000000000000060a082015290517fd156dc9e38c2e6173a4862f6563ea573ab87269a917bdabe0a5d9d7fdb2b32e99181900360c00190a161109b565b801515610f7d5750425b600160d060020a031982166000908152600560208181526040808420805465ffffffffffff19167a0100000000000000000000000000000000000000000000000000008804178082556003808552958390205494845279ffffffffffffffffffffffffffffffffffffffff0000000000001916660100000000000094859004600160a060020a03169094029390931783556001808401805433600160a060020a03199091168117909155949093018590558051938452908301919091526060828201819052600c908301527fe58f96e8b4a7e68890e58a9f00000000000000000000000000000000000000006080830152517fd156dc9e38c2e6173a4862f6563ea573ab87269a917bdabe0a5d9d7fdb2b32e99160a0908290030190a15b5050565b33600090815260208190526040812060020154151561113f57604080513381526000602082015260608183018190526027908201527fe5bd93e5898de794a8e688b7e697a0e58c85e8a385e4bfa1e681afe4b88ae4bc60808201527fa0e69d83e999900000000000000000000000000000000000000000000000000060a082015290516000805160206125928339815191529181900360c00190a1611401565b600160d060020a0319821660009081526003602052604090206001015415156111e957604080513381526000602082015260608183018190526027908201527fe5a4a7e58c85e4bfa1e681afe4b88de5ad98e59ca8efbc8ce8afb7e9878de69660808201527fb0e8be93e585a50000000000000000000000000000000000000000000000000060a082015290516000805160206125928339815191529181900360c00190a1611401565b5060005b60068110156113305781816006811061120257fe5b1a7f0100000000000000000000000000000000000000000000000000000000000000027effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916838260098110151561125557fe5b1a7f0100000000000000000000000000000000000000000000000000000000000000027effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff191614151561132857604080513381526000602082015260608183018190526024908201527fe4bfa1e681afe8be93e585a5e69c89e8afafefbc8ce8afb7e9878de696b0e8be60808201527f93e585a50000000000000000000000000000000000000000000000000000000060a082015290516000805160206125928339815191529181900360c00190a1611401565b6001016111ed565b600160d060020a031982166000908152600260209081526040808320805460018082018355918552938390206003808604909101805468ffffffffffffffffff929096066009026101000a91820219909516770100000000000000000000000000000000000000000000008904919091021790935580513381529182019290925260608183018190526018908201527fe58c85e8a385e4bfa1e681afe4b88ae4bca0e68890e58a9f0000000000000000608082015290516000805160206125928339815191529160a0908290030190a15b505050565b3360009081526020819052604081206002015415156114b857604080513381526000602082015260608183018190526021908201527fe5bd93e5898de794a8e688b7e697a0e4b88ae4bca0e9858de696b9e69d83e99960808201527f900000000000000000000000000000000000000000000000000000000000000060a082015290517fb23107efcd4a03b0c166bccb64837204df67666dd3f6a92a687d81babf37fb449181900360c00190a161088e565b600160e860020a0319841660009081526001602052604090206003015415156114e3575060006114e7565b5060015b600160e860020a031984166000908152600160208181526040909220805462ffffff19167d01000000000000000000000000000000000000000000000000000000000088041781558551611543939190920191908601906124b6565b50600160e860020a0319841660009081526001602090815260409091208351611574926002909201918501906124b6565b50600160e860020a0319841660009081526001602081905260409091206003015580151561160f57604080513381526001602082015260608183018190526015908201527fe696b0e9858de696b9e4b88ae4bca0e68890e58a9f0000000000000000000000608082015290517fb23107efcd4a03b0c166bccb64837204df67666dd3f6a92a687d81babf37fb449181900360a00190a161088e565b60408051338152600160208201526060818301819052601e908201527fe9858de696b9e5b7b2e5ad98e59ca8efbc8ce4bfaee694b9e68890e58a9f0000608082015290517fb23107efcd4a03b0c166bccb64837204df67666dd3f6a92a687d81babf37fb449181900360a00190a150505050565b3360009081526020819052604081206002015415156117355760408051338152600060208201526060818301819052602d908201527fe5bd93e5898de794a8e688b7e697a0e4b88ae4bca0e88dafe59381e7949fe4ba60808201527fa7e4bfa1e681afe69d83e999900000000000000000000000000000000000000060a082015290517fcd6492bfa0acf0ab9b77b50b67482bb23aa3f1283c1d3672ea243305baec16609181900360c00190a1611927565b600160a060020a0384161515611749573393505b821515611754574292505b600160d060020a03198516600090815260036020526040902060010154151561177f57506000611783565b5060015b600160d060020a031985166000908152600360209081526040909120805465ffffffffffff19167a01000000000000000000000000000000000000000000000000000088041779ffffffffffffffffffffffffffffffffffffffff00000000000019166601000000000000600160a060020a03881602178155600181018590558351611817926002909201918501906124b6565b508015156118925760408051338152600160208201526060818301819052601e908201527fe88dafe59381e7949fe4baa7e4bfa1e681afe4b88ae4bca0e68890e58a9f0000608082015290517fcd6492bfa0acf0ab9b77b50b67482bb23aa3f1283c1d3672ea243305baec16609181900360a00190a1611927565b60408051338152600160208201526060818301819052602a908201527fe88dafe59381e4bfa1e681afe5b7b2e5ad98e59ca8efbc8ce4bfa1e681afe69b60808201527fb4e696b0e68890e58a9f0000000000000000000000000000000000000000000060a082015290517fcd6492bfa0acf0ab9b77b50b67482bb23aa3f1283c1d3672ea243305baec16609181900360c00190a15b5050505050565b6000600160a060020a0383161515611944573392505b600160a060020a0383811660009081526020819052604090205416151561196d57506000611971565b5060015b600160a060020a0383166000818152602081815260409091208054600160a060020a031916909217825583516119ad92600101918501906124b6565b50600160a060020a03831660009081526020819052604090206001600290910155801515611a405760408051338152602081018290526012818301527fe794a8e688b7e6b3a8e5868ce68890e58a9f0000000000000000000000000000606082015290517f8d61f84d8015a8bc5d655672779571797f897ed10d3c4f75305db701ea94cb539181900360800190a1611401565b60408051338152602081018290526018818301526000805160206125b2833981519152606082015290517f8d61f84d8015a8bc5d655672779571797f897ed10d3c4f75305db701ea94cb539181900360800190a1505050565b336000908152600660205260408120600301541515611b3957604080513381526000602082015260608183018190526027908201527fe5bd93e5898de794a8e688b7e697a0e99bb6e594aee4bfa1e681afe4b88ae4bc60808201527fa0e69d83e999900000000000000000000000000000000000000000000000000060a082015290516000805160206125528339815191529181900360c00190a1611d9e565b600160a060020a0383166000908152600860205260409020600301541515611be257604080513381526000602082015260608183018190526033908201527fe6b688e8b4b9e88085e4bfa1e681afe4b88de5ad98e59ca8efbc8ce99bb6e59460808201527faee4bfa1e681afe4b88ae4bca0e5a4b1e8b4a50000000000000000000000000060a082015290516000805160206125528339815191529181900360c00190a1611d9e565b50600160d060020a031985166000908152600560205260409020600201548590600160a060020a03163314611c985760408051338152600060208201526060818301819052602d908201527fe88dafe59381e4bfa1e681afe99499e8afafefbc8ce99bb6e594aee4bfa1e68160808201527fafe4b88ae4bca0e5a4b1e8b4a50000000000000000000000000000000000000060a082015290516000805160206125528339815191529181900360c00190a1611d9e565b841515611ca3574294505b600160a060020a0384161515611cb7573393505b600160b860020a031986166000908152600760209081526040918290208054770100000000000000000000000000000000000000000000008a0468ffffffffffffffffff199091161781556001808201899055600282018054600160a060020a03808b16600160a060020a031992831617909255600384018054928a1692909116919091179055600490910185905582513381529182015260608183018190526018908201527fe99bb6e594aee4bfa1e681afe4b88ae4bca0e68890e58a9f0000000000000000608082015290516000805160206125528339815191529181900360a00190a15b505050505050565b600160a060020a0383161515611dba573392505b600160b860020a03198416600090815260076020526040902060030154600160a060020a03848116911614611e825760408051338152600060208201526060818301819052602d908201527fe99bb6e594aee4bfa1e681afe99499e8afafefbc8ce58f8de9a688e4bfa1e68160808201527fafe4b88ae4bca0e5a4b1e8b4a50000000000000000000000000000000000000060a082015290517faab2a701489dfe9445e8df0a11e4e2a21b562b5da6568d8ceee124df14f6d7c39181900360c00190a161088e565b811515611e8d574291505b600160b860020a03198416600090815260096020908152604090912060028101805468ffffffffffffffffff19167701000000000000000000000000000000000000000000000088041790558054600160a060020a031916600160a060020a038616178155600181018490558251611f0d926003909201918401906124b6565b50604080513381526001602082015260608183018190526012908201527fe4bfa1e681afe58f8de9a688e68890e58a9f0000000000000000000000000000608082015290517faab2a701489dfe9445e8df0a11e4e2a21b562b5da6568d8ceee124df14f6d7c39181900360a00190a150505050565b336000908152600460205260409020600201541515611ffc5760408051338152600060208201526060818301819052601b908201527fe5bd93e5898de794a8e688b7e697a0e98081e8bebee69d83e999900000000000608082015290516000805160206125728339815191529181900360a00190a1611401565b600160d060020a0319831660009081526005602052604090206003015415156120a657604080513381526000602082015260608183018190526024908201527fe697a0e79bb8e5ba94e58f96e8b4a7e4bfa1e681afefbc8ce98081e8bebee5a460808201527fb1e8b4a50000000000000000000000000000000000000000000000000000000060a082015290516000805160206125728339815191529181900360c00190a1611401565b600160d060020a031983166000908152600560205260409020600401541561214f57604080513381526000602082015260608183018190526024908201527fe88dafe59381e5b7b2e98081e8bebeefbc8ce697a0e6b395e9878de5a48de98060808201527f81e8bebe0000000000000000000000000000000000000000000000000000000060a082015290516000805160206125728339815191529181900360c00190a1611401565b81151561215a574291505b600160d060020a03198316600090815260056020908152604091829020600481018590556002018054600160a060020a031916600160a060020a03851617905581513381526001918101919091526060818301819052600c908201527fe98081e8bebee68890e58a9f0000000000000000000000000000000000000000608082015290516000805160206125728339815191529160a0908290030190a1505050565b6000600160a060020a0384161515612212573393505b600160a060020a0384811660009081526008602052604090205416151561223b5750600061223f565b5060015b600160a060020a03841660008181526008602052604090208054600160a060020a03191690911781556001808201859055600282018490556003909101558015156122ef5760408051338152602081018290526012818301527fe6b3a8e5868ce794a8e688b7e68890e58a9f0000000000000000000000000000606082015290517f9df22cde4061d380418e4e207ef95c22c561c7c58bb56a988919dca8e8b209479181900360800190a161088e565b60408051338152602081018290526018818301526000805160206125b2833981519152606082015290517f9df22cde4061d380418e4e207ef95c22c561c7c58bb56a988919dca8e8b209479181900360800190a150505050565b6000600160a060020a038316151561235f573392505b600160a060020a038381166000908152600460205260409020541615156123885750600061238c565b5060015b600160a060020a03831660008181526004602090815260409091208054600160a060020a031916909217825583516123ca92600101918501906124b6565b50600160a060020a0383166000908152600460205260409020600160029091015580151561245d5760408051338152602081018290526012818301527fe6b3a8e5868ce794a8e688b7e68890e58a9f0000000000000000000000000000606082015290517f5746aa2026b6cce5ab18d1dd958f355630be00088fe639c090e6c252cc720c359181900360800190a1611401565b60408051338152602081018290526018818301526000805160206125b2833981519152606082015290517f5746aa2026b6cce5ab18d1dd958f355630be00088fe639c090e6c252cc720c359181900360800190a1505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106124f757805160ff1916838001178555612524565b82800160010185558215612524579182015b82811115612524578251825591602001919060010190612509565b50612530929150612534565b5090565b61254e91905b80821115612530576000815560010161253a565b905600ace5772e69c84ff1991781222b7d1ec8786e1b5c2d9402f3a05e7d50cd28d68bd09dc7ea68a0c7daceeb1e462a4cbb94e4d2426c084cdb70a90787b5c7470b77b9ed22041e016db14768f807c15b0339719e13911da48e643021140af7dc0049e794a8e688b7e4bfa1e681afe4bfaee694b9e68890e58a9f0000000000000000a165627a7a72305820673054af7339fb5b441b5df58bf07f64851e4d9f9bc35d87c791b892992b8dde0029";

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
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bool>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event NEWBOXINFO_EVENT = new Event("newBoxInfo", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bool>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event NEWPICKINFO_EVENT = new Event("newPickInfo", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bool>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event NEWDROPINFO_EVENT = new Event("newDropInfo", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bool>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event NEWSELLINFO_EVENT = new Event("newSellInfo", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bool>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event NEWFEEDBACK_EVENT = new Event("newFeedBack", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bool>() {}, new TypeReference<Utf8String>() {}));
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
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(sellerAddr), 
                new org.web3j.abi.datatypes.DynamicBytes(sellerName), 
                new org.web3j.abi.datatypes.generated.Uint256(sellerType)), 
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
                new org.web3j.abi.datatypes.generated.Uint256(time)), 
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
                new org.web3j.abi.datatypes.DynamicBytes(drugName), 
                new org.web3j.abi.datatypes.DynamicBytes(material)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setBoxInfo(byte[] boxID, String manufacturerAddr, BigInteger time, byte[] materialID) {
        final Function function = new Function(
                FUNC_SETBOXINFO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes6(boxID), 
                new org.web3j.abi.datatypes.Address(manufacturerAddr), 
                new org.web3j.abi.datatypes.generated.Uint256(time), 
                new org.web3j.abi.datatypes.DynamicBytes(materialID)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setManufacturer(String manufacturerAddr, byte[] manufacturerName) {
        final Function function = new Function(
                FUNC_SETMANUFACTURER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(manufacturerAddr), 
                new org.web3j.abi.datatypes.DynamicBytes(manufacturerName)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setSellInfo(byte[] packageID, BigInteger time, String sellerAddr, String consumerAddr, BigInteger price) {
        final Function function = new Function(
                FUNC_SETSELLINFO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes9(packageID), 
                new org.web3j.abi.datatypes.generated.Uint256(time), 
                new org.web3j.abi.datatypes.Address(sellerAddr), 
                new org.web3j.abi.datatypes.Address(consumerAddr), 
                new org.web3j.abi.datatypes.generated.Uint256(price)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> feedBack(byte[] packageID, String consumerAddr, BigInteger time, byte[] information) {
        final Function function = new Function(
                FUNC_FEEDBACK, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes9(packageID), 
                new org.web3j.abi.datatypes.Address(consumerAddr), 
                new org.web3j.abi.datatypes.generated.Uint256(time), 
                new org.web3j.abi.datatypes.DynamicBytes(information)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> drop(byte[] boxID, BigInteger time, String sellerAddr) {
        final Function function = new Function(
                FUNC_DROP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes6(boxID), 
                new org.web3j.abi.datatypes.generated.Uint256(time), 
                new org.web3j.abi.datatypes.Address(sellerAddr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setConsumer(String consumerAddr, BigInteger gender, BigInteger age) {
        final Function function = new Function(
                FUNC_SETCONSUMER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(consumerAddr), 
                new org.web3j.abi.datatypes.generated.Uint256(gender), 
                new org.web3j.abi.datatypes.generated.Uint256(age)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setTransporter(String transporterAddr, byte[] transporterName) {
        final Function function = new Function(
                FUNC_SETTRANSPORTER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(transporterAddr), 
                new org.web3j.abi.datatypes.DynamicBytes(transporterName)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public List<NewManufacturerEventResponse> getNewManufacturerEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NEWMANUFACTURER_EVENT, transactionReceipt);
        ArrayList<NewManufacturerEventResponse> responses = new ArrayList<NewManufacturerEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
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
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(NEWMANUFACTURER_EVENT, log);
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
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NEWTRANSPORTER_EVENT, transactionReceipt);
        ArrayList<NewTransporterEventResponse> responses = new ArrayList<NewTransporterEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
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
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(NEWTRANSPORTER_EVENT, log);
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
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NEWSELLER_EVENT, transactionReceipt);
        ArrayList<NewSellerEventResponse> responses = new ArrayList<NewSellerEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
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
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(NEWSELLER_EVENT, log);
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
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NEWCONSUMER_EVENT, transactionReceipt);
        ArrayList<NewConsumerEventResponse> responses = new ArrayList<NewConsumerEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
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
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(NEWCONSUMER_EVENT, log);
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
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NEWPACKINFO_EVENT, transactionReceipt);
        ArrayList<NewPackInfoEventResponse> responses = new ArrayList<NewPackInfoEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
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
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(NEWPACKINFO_EVENT, log);
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
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NEWFORMULATION_EVENT, transactionReceipt);
        ArrayList<NewFormulationEventResponse> responses = new ArrayList<NewFormulationEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NewFormulationEventResponse typedResponse = new NewFormulationEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.isSuccess = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.message = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<NewFormulationEventResponse> newFormulationEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, NewFormulationEventResponse>() {
            @Override
            public NewFormulationEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(NEWFORMULATION_EVENT, log);
                NewFormulationEventResponse typedResponse = new NewFormulationEventResponse();
                typedResponse.log = log;
                typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.isSuccess = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.message = (String) eventValues.getNonIndexedValues().get(2).getValue();
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
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NEWBOXINFO_EVENT, transactionReceipt);
        ArrayList<NewBoxInfoEventResponse> responses = new ArrayList<NewBoxInfoEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NewBoxInfoEventResponse typedResponse = new NewBoxInfoEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.isSuccess = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.message = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<NewBoxInfoEventResponse> newBoxInfoEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, NewBoxInfoEventResponse>() {
            @Override
            public NewBoxInfoEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(NEWBOXINFO_EVENT, log);
                NewBoxInfoEventResponse typedResponse = new NewBoxInfoEventResponse();
                typedResponse.log = log;
                typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.isSuccess = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.message = (String) eventValues.getNonIndexedValues().get(2).getValue();
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
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NEWPICKINFO_EVENT, transactionReceipt);
        ArrayList<NewPickInfoEventResponse> responses = new ArrayList<NewPickInfoEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NewPickInfoEventResponse typedResponse = new NewPickInfoEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.isSuccess = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.message = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<NewPickInfoEventResponse> newPickInfoEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, NewPickInfoEventResponse>() {
            @Override
            public NewPickInfoEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(NEWPICKINFO_EVENT, log);
                NewPickInfoEventResponse typedResponse = new NewPickInfoEventResponse();
                typedResponse.log = log;
                typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.isSuccess = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.message = (String) eventValues.getNonIndexedValues().get(2).getValue();
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
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NEWDROPINFO_EVENT, transactionReceipt);
        ArrayList<NewDropInfoEventResponse> responses = new ArrayList<NewDropInfoEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NewDropInfoEventResponse typedResponse = new NewDropInfoEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.isSuccess = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.message = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<NewDropInfoEventResponse> newDropInfoEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, NewDropInfoEventResponse>() {
            @Override
            public NewDropInfoEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(NEWDROPINFO_EVENT, log);
                NewDropInfoEventResponse typedResponse = new NewDropInfoEventResponse();
                typedResponse.log = log;
                typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.isSuccess = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.message = (String) eventValues.getNonIndexedValues().get(2).getValue();
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
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NEWSELLINFO_EVENT, transactionReceipt);
        ArrayList<NewSellInfoEventResponse> responses = new ArrayList<NewSellInfoEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NewSellInfoEventResponse typedResponse = new NewSellInfoEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.isSuccess = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.message = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<NewSellInfoEventResponse> newSellInfoEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, NewSellInfoEventResponse>() {
            @Override
            public NewSellInfoEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(NEWSELLINFO_EVENT, log);
                NewSellInfoEventResponse typedResponse = new NewSellInfoEventResponse();
                typedResponse.log = log;
                typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.isSuccess = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.message = (String) eventValues.getNonIndexedValues().get(2).getValue();
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
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NEWFEEDBACK_EVENT, transactionReceipt);
        ArrayList<NewFeedBackEventResponse> responses = new ArrayList<NewFeedBackEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NewFeedBackEventResponse typedResponse = new NewFeedBackEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.isSuccess = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.message = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<NewFeedBackEventResponse> newFeedBackEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, NewFeedBackEventResponse>() {
            @Override
            public NewFeedBackEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(NEWFEEDBACK_EVENT, log);
                NewFeedBackEventResponse typedResponse = new NewFeedBackEventResponse();
                typedResponse.log = log;
                typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.isSuccess = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.message = (String) eventValues.getNonIndexedValues().get(2).getValue();
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

        public Boolean isSuccess;

        public String message;
    }

    public static class NewBoxInfoEventResponse {
        public Log log;

        public String sender;

        public Boolean isSuccess;

        public String message;
    }

    public static class NewPickInfoEventResponse {
        public Log log;

        public String sender;

        public Boolean isSuccess;

        public String message;
    }

    public static class NewDropInfoEventResponse {
        public Log log;

        public String sender;

        public Boolean isSuccess;

        public String message;
    }

    public static class NewSellInfoEventResponse {
        public Log log;

        public String sender;

        public Boolean isSuccess;

        public String message;
    }

    public static class NewFeedBackEventResponse {
        public Log log;

        public String sender;

        public Boolean isSuccess;

        public String message;
    }
}
