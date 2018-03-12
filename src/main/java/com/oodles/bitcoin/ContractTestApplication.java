package com.oodles.bitcoin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.oodles.bitcoin.contract.SmartContract;

@SpringBootApplication
public class ContractTestApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ContractTestApplication.class, args);
		SmartContract smartContract=new SmartContract();
		smartContract.deployContract();
	}
}
