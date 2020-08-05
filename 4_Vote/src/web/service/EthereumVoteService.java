package web.service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Uint;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.PersonalListAccounts;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;

public class EthereumVoteService {
	// 동일한 코드는 객체를 계속해서 만들 필요가 없음...멤버변수로 처리
	// 계약 주소 세팅
	String contract = "0x4A682114cF56Ea9872dFD6f1CCB1e06D1eD2Cbb1";
	Web3j web3j;
	Admin admin;
	List<String> addressList;

	public EthereumVoteService() throws Exception { // 생성자로 한번만 호출

		// 이더리움 네트웍 접속
		web3j = Web3j.build(new HttpService("HTTP://127.0.0.1:7545"));
		// accounts 얻기
		admin = Admin.build(new HttpService("HTTP://127.0.0.1:7545"));
		PersonalListAccounts personalListAccounts = admin.personalListAccounts().send();
		addressList = personalListAccounts.getAccountIds();
	}
//	public void service(String amount) {
//		try {
//
//			
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public Object totalVotesFor(String candidate) throws Exception {
		// get function 호출(block생성 안함)
		Function function = new Function("totalVotesFor", Arrays.asList(new Utf8String(candidate)),
				Arrays.asList(new TypeReference<Uint256>() {
				}));

		Transaction transaction = Transaction.createEthCallTransaction(addressList.get(0), contract,
				FunctionEncoder.encode(function));

		EthCall ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).send();

		List<Type> decode = FunctionReturnDecoder.decode(ethCall.getResult(), function.getOutputParameters());

		System.out.println(candidate+"[득표수 보기]");
		System.out.println("getValue = " + decode.get(0).getValue());
		System.out.println("=========================================");

		return decode.get(0).getValue();
	}

	public void voteForCandidate(String candidate) throws Exception {
		// set function 호출

		Function function2 = new Function("voteForCandidate", Arrays.asList(new Utf8String(candidate)), Collections.emptyList());

		EthGetTransactionCount ethGetTransactionCount = web3j
				.ethGetTransactionCount(addressList.get(0), DefaultBlockParameterName.LATEST).sendAsync().get();

		BigInteger nonce = ethGetTransactionCount.getTransactionCount();
		Transaction transaction2 = Transaction.createFunctionCallTransaction(addressList.get(0), nonce,
				Transaction.DEFAULT_GAS, null, contract, FunctionEncoder.encode(function2));

		EthSendTransaction ethSendTransaction = web3j.ethSendTransaction(transaction2).send();

		String transactionHash = ethSendTransaction.getTransactionHash();

		// ledger에 쓰여지기 까지 기다리기.

		Thread.sleep(2000);

		System.out.println("transactionHash = " + transactionHash);

	}

}
