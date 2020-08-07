package Web.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.PersonalListAccounts;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;
import org.web3j.utils.Convert.Unit;
import org.xml.sax.SAXException;


public class UserDAO {
	
	
	public void transferEther(String address) throws Exception {
		/*
	  Web3j web3 = Web3j.build(new HttpService("HTTP://127.0.0.1:8545"));
	  System.out.println("Successfuly connected to Ethereum");
	  System.out.println("Balance: " + Convert.fromWei(web3.ethGetBalance(address, DefaultBlockParameterName.LATEST).send().getBalance().toString(), Unit.ETHER));
	  
	  Admin admin= Admin.build(new HttpService("HTTP://127.0.0.1:8545"));
	  PersonalListAccounts personalListAccounts = admin.personalListAccounts().send();
	  List<String> addressList = personalListAccounts.getAccountIds();
	  
	  // Get the latest nonce of Sender
	  String senderAddress=addressList.get(0);
      EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(senderAddress, DefaultBlockParameterName.LATEST).send();
      BigInteger nonce =  ethGetTransactionCount.getTransactionCount();

      // Recipient address
      String recipientAddress = address;

      // Value to transfer (in wei)
      BigInteger value = Convert.toWei("10", Unit.ETHER).toBigInteger();

      // Gas Parameters
      BigInteger gasLimit = BigInteger.valueOf(21000);
      BigInteger gasPrice = Convert.toWei("1", Unit.GWEI).toBigInteger();

      // Prepare the rawTransaction
      RawTransaction rawTransaction  = RawTransaction.createEtherTransaction(
                 nonce,
                 gasPrice,
                 gasLimit,
                 recipientAddress,
                 value);

      // Sign the transaction
      byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
      String hexValue = Numeric.toHexString(signedMessage);

      // Send transaction
      EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(hexValue).send();
      String transactionHash = ethSendTransaction.getTransactionHash();
      System.out.println("transactionHash: " + transactionHash);

      // Wait for transaction to be mined
      Optional<TransactionReceipt> transactionReceipt = null;
      do {
        System.out.println("checking if transaction " + transactionHash + " is mined....");
            EthGetTransactionReceipt ethGetTransactionReceiptResp = web3.ethGetTransactionReceipt(transactionHash).send();
            transactionReceipt = ethGetTransactionReceiptResp.getTransactionReceipt();
            Thread.sleep(3000); // Wait 3 sec
      } while(!transactionReceipt.isPresent());

      System.out.println("Transaction " + transactionHash + " was mined in block # " + transactionReceipt.get().getBlockNumber());
      System.out.println("Balance: " + Convert.fromWei(web3.ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST).send().getBalance().toString(), Unit.ETHER));

	  */
	}
	
	
	public  String createUser(String id, String pw, String name, String wallet_path) {
		String pri_key="",pub_key="",address="";
		try {
		
		  String str=WalletUtils.generateNewWalletFile(pw, new File(wallet_path), true);
		  System.out.println(str);
		  Credentials credentials = WalletUtils.loadCredentials(pw, wallet_path+str);
		  
		  ECKeyPair keyPair=credentials.getEcKeyPair();
		  pri_key=keyPair.getPrivateKey().toString()+"";
		  pub_key=keyPair.getPublicKey().toString()+"";
		  address=credentials.getAddress();
		  
		  System.out.println("pri_key:"+pri_key);		  
		  System.out.println("pub_key:"+pub_key);
		  System.out.println("address:"+address);
		  
		  transferEther(address);
		}catch(Exception e) {
			e.printStackTrace();
		}
		  
		StringBuffer  result=new StringBuffer();
		Connection con=null;
		PreparedStatement st=null;
		int rs=0;
		try {
			con = EConnection.getConnection(this,wallet_path);	
			st = con.prepareStatement("insert into Users(id,pw,name,pri_key,pub_key,address) values ( ?, ? , ? , ?, ?, ?) ");
			st.setString(1, id);
			st.setString(2, pw);
			st.setString(3, name);
			st.setString(4, pri_key);
			st.setString(5, pub_key);
			st.setString(6, address);
			rs = st.executeUpdate();
   		    
			result.append("insert row: "+rs);			
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			result.append("요청처리에러발생");
		}
	
		
		if ( st != null ) try { st.close(); }catch(SQLException e){}
		if ( con != null ) try { con.close(); }catch(SQLException e){}
        
		return result.toString();
	}
	

	
	public  String readUser(String id, String pw) {
		StringBuffer  result=new StringBuffer();
		Connection con=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		try {
			con = EConnection.getConnection(this);	
			st = con.prepareStatement("select * from users where id=? and pw=? ");
			st.setString(1, id);
			st.setString(2, pw);
			rs = st.executeQuery();
   		    if ( rs.next() ) {
			       result.append("pri_key: "+rs.getString("pri_key"));
			       result.append("\npub_key: "+rs.getString("pub_key"));
			       result.append("\naddress: "+rs.getString("address"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			result.append("요청처리에러발생");
		}
	
			if ( rs != null ) try { rs.close(); }catch(SQLException e){}
			if ( st != null ) try { st.close(); }catch(SQLException e){}
			if ( con != null ) try { con.close(); }catch(SQLException e){}
            
			return result.toString();
	}
	
	
	
}

