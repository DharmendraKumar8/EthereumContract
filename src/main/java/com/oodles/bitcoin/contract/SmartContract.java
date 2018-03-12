package com.oodles.bitcoin.contract;

/*import com.example.Ethereum.Controller.ContractController;
import com.example.Ethereum.Wrapper.ERC20;
*/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class SmartContract {

	Logger LOGGER = LoggerFactory.getLogger(SmartContract.class);

	public String newWallet() throws Exception {
		return WalletUtils.generateNewWalletFile("root", new File("/home/dharmendra/my-eth-chain/firstserver/keystore"),
				true);
	}

	public void deployContract() throws Exception {

		Web3j web3j = Web3j.build(new HttpService("http://localhost:8000"));

		System.err.println(
				"Connected to Ethereum client version: " + web3j.web3ClientVersion().send().getWeb3ClientVersion());

		String walletSource = "/home/dharmendra/my-eth-chain/firstserver/keystore/UTC--2018-03-12T12-16-46.142794474Z--be58deee0a6ecb0bad1e62c3eedf14f05303267a";
		Credentials credentials = WalletUtils.loadCredentials("root", new File(walletSource));

		LOGGER.info("Credentials loaded");

		FixedSupplyToken fixedSupplyToken = FixedSupplyToken.deploy(web3j, credentials, new BigInteger("1000000"),
				new BigInteger("210000"), new BigInteger("100000000"), "FST", BigInteger.TEN, "FST").send();
		LOGGER.info("Contract deployed");
		FixedSupplyToken load1 = FixedSupplyToken.load(fixedSupplyToken.getContractAddress(), web3j, credentials,
				new BigInteger("10000"), new BigInteger("100000000"));
		LOGGER.info("Contract deployed" + load1.balanceOf(credentials.getAddress()));

	}

}
