package cn.edu.zju.drugtracing;

import cn.edu.zju.drugtracing.contract.Greeter;
import cn.edu.zju.drugtracing.contract.MedicineSourceTracing;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Numeric;

class Web3jTest {

	private static final Logger log = LoggerFactory.getLogger(Web3jTest.class);

	@Test
	public void deploy() throws Exception {

		// We start by creating a new web3j instance to connect to remote nodes on the network.
		// Note: if using web3j Android, use Web3jFactory.build(...
		Web3j web3j = Web3j.build(new HttpService(
				// FIXME: Enter your Infura token here;
				"https://rinkeby.infura.io/v3/19791ab2d9924708b087b09b8d3a7962"));
		log.info("Connected to Ethereum client version: "
				+ web3j.web3ClientVersion().send().getWeb3ClientVersion());

		// We then need to load our Ethereum wallet file
		// FIXME: Generate a new wallet file using the web3j command line tools https://docs.web3j.io/command_line.html
		Credentials credentials =
				WalletUtils.loadCredentials(
						"19981018",
						"/Users/zjxjwxk/Library/Ethereum/testnet/keystore/UTC--2020-08-11T09-33-08.11000000Z--249e0865a2f361d9bae7c4ec383beccb507e13bb.json");
		log.info("Credentials loaded");

		// FIXME: Request some Ether for the Rinkeby test network at https://www.rinkeby.io/#faucet
//        log.info("Sending 1 Wei ("
//                + Convert.fromWei("1", Convert.Unit.ETHER).toPlainString() + " Ether)");
//        TransactionReceipt transferReceipt = Transfer.sendFunds(
//                web3j, credentials,
//                // you can put any address here
//                "0x39Ff9ff6Bc23C8d328df7a215ADa592bADeFB5b5",
//                // 1 wei = 10^-18 Ether
//                BigDecimal.ONE, Convert.Unit.WEI)
//                .send();
//        log.info("Transaction complete, view it at https://rinkeby.etherscan.io/tx/"
//                + transferReceipt.getTransactionHash());

		// Now lets deploy a smart contract
		ContractGasProvider contractGasProvider = new DefaultGasProvider();
		log.info("Deploying smart contract");
		MedicineSourceTracing contract = MedicineSourceTracing.deploy(
				web3j,
				credentials,
				contractGasProvider
		).send();

		String contractAddress = contract.getContractAddress();
		log.info("Smart contract deployed to address " + contractAddress);
		log.info("View contract at https://rinkeby.etherscan.io/address/" + contractAddress);

//		log.info("Value stored in remote smart contract: " + contract.greet().send());
//
//		// Lets modify the value in our smart contract
//		TransactionReceipt transactionReceipt = contract.newGreeting("Well hello again").send();
//
//		log.info("New value stored in remote smart contract: " + contract.greet().send());
//
//		// Events enable us to log specific events happening during the execution of our smart
//		// contract to the blockchain. Index events cannot be logged in their entirety.
//		// For Strings and arrays, the hash of values is provided, not the original value.
//		// For further information, refer to https://docs.web3j.io/filters.html#filters-and-events
//		for (Greeter.ModifiedEventResponse event : contract.getModifiedEvents(transactionReceipt)) {
//			log.info("Modify event fired, previous value: " + event.oldGreeting
//					+ ", new value: " + event.newGreeting);
//			log.info("Indexed event previous value: " + Numeric.toHexString(event.oldGreetingIdx)
//					+ ", new value: " + Numeric.toHexString(event.newGreetingIdx));
//		}
	}

	@Test
	public void invoke() throws Exception {

		// Creating a new web3j instance to connect to remote nodes on the network
		Web3j web3j = Web3j.build(new HttpService(
				"https://rinkeby.infura.io/v3/19791ab2d9924708b087b09b8d3a7962"));
		log.info("Connected to Ethereum client version: "
				+ web3j.web3ClientVersion().send().getWeb3ClientVersion());

		// Load Ethereum wallet file
		Credentials credentials =
				WalletUtils.loadCredentials(
						"19981018",
						"/Users/zjxjwxk/Library/Ethereum/testnet/keystore/UTC--2020-08-11T09-33-08.11000000Z--249e0865a2f361d9bae7c4ec383beccb507e13bb.json");
		log.info("Credentials loaded");

		// Load an existing contract
		ContractGasProvider contractGasProvider = new DefaultGasProvider();
		String contractAddress = "0x7a1D53F7d063F00BD489c6A730331c051D9C5754";
		log.info("Loading existing smart contract at address: " + contractAddress);
		Greeter contract = Greeter.load(
				contractAddress,
				web3j,
				credentials,
				contractGasProvider
		);

		log.info("View contract at https://rinkeby.etherscan.io/address/" + contractAddress);

		// Invoke contract
		log.info("Value stored in remote smart contract: " + contract.greet().send());

		// Modify the value in our smart contract
		TransactionReceipt transactionReceipt = contract.newGreeting("Hello Wxk").send();
		log.info("New value stored in remote smart contract: " + contract.greet().send());

		// Log specific events happening during the execution of our smart
		for (Greeter.ModifiedEventResponse event : contract.getModifiedEvents(transactionReceipt)) {
			log.info("Modify event fired, previous value: " + event.oldGreeting
					+ ", new value: " + event.newGreeting);
			log.info("Indexed event previous value: " + Numeric.toHexString(event.oldGreetingIdx)
					+ ", new value: " + Numeric.toHexString(event.newGreetingIdx));
		}
	}

}
