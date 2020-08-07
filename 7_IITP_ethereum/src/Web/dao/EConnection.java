package Web.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;



public class EConnection {
	static String wallet_path;
	public static Connection getConnection(Object o) {
		return getConnection(o,wallet_path);
	}
     public static Connection getConnection(Object o, String wallet_path) {
    	 EConnection.wallet_path=wallet_path;
    	 Connection con=null;
    	 Properties properties = new Properties();
    	 
    	 try {   	
    		  String str=wallet_path+"WEB-INF/config/dbconn.properties";
    		  System.out.println(str);
    		  properties.load(new FileReader(str));
    		  String driver = properties.getProperty("driver");
    		  System.out.println(driver);
    	      //properties.load( o.getClass().getClassLoader().getResourceAsStream(str));
    	 } catch (IOException e) {
    		System.out.println("DB 정보 로드 실패");
    	    return null;
    	 }
    	 String driver = properties.getProperty("driver");
    	 String url = properties.getProperty("url");
    	 String username = properties.getProperty("username");
    	 String password = properties.getProperty("password");
    
    	 try {
    	    Class.forName(driver); 
    	    con=DriverManager.getConnection(url,username,password);
    	 }catch(Exception e){
    		 e.printStackTrace();
    		 System.out.println("DB 연결 오류");
    		 return null;
    	 } 
    	 return con;
     }
}

