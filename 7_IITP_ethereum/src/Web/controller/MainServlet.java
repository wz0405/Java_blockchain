
package Web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.web3j.*;

import Web.dao.UserDAO;
import Web.controller.Team1Service;
import Web.controller.Team2Service;
import Web.controller.Team3Service;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	Team1Service service1;
	Team2Service service2;
	Team3Service service3;
	
    public void init() {
        try {
			service1=new Team1Service();
			service2=new Team2Service();
			service3=new Team3Service();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public  void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
       	System.out.println("process....");
		request.setCharacterEncoding("UTF-8");
      	response.setContentType("application/json;charset=utf-8");
      	PrintWriter out=response.getWriter();
      	BufferedReader br=request.getReader();
      	JSONObject obj=(JSONObject)JSONValue.parse(br);     
      	JSONObject result_obj=new JSONObject();
        System.out.println(obj);
     	String sign=(String) obj.get("sign");
     	System.out.println(sign);
     	if(sign==null){
                	System.out.println("sign is null");
     	}else{ 
     		if(sign.equals("createUser")){
     			UserDAO t=new UserDAO();
     			String id=(String) obj.get("id");
     			String pw=(String) obj.get("p_w");
     			String name=(String) obj.get("name");
     			String wallet_path=getServletContext().getRealPath("/");
     			String result=t.createUser(id,pw,name,wallet_path);

     			System.out.println(result);                	
               		
               	if(result!=null){ 
               		setResultMsg(out, result_obj, result, "User가 생성되었습니다");                                                                      	
               	}else{
               		setResultMsg(out, result_obj, 0, "회원 가입 처리 오류");                                       	
               	}               	
        	}else if(sign.equals("login")){
     			UserDAO t=new UserDAO();
     			String id=(String) obj.get("id");
     			String pw=(String) obj.get("p_w");                 			
     			String result=t.readUser(id, pw);
     			System.out.println(result);
               	
               	if(result!=null){ 
               		setResultMsg(out, result_obj, result, "로그인되었습니다");                      	                                                                     	
               	}else{
               		setResultMsg(out, result_obj, 0, "다시 로그인 해주세요");                      	                                      	
               	}
                   	
     		}else if(sign.equals("team1")){
               	String gye=(String) obj.get("name");                                                       	
               	System.out.println(gye);
               	
               	service1.saveGyeddon(gye);
               	Object result=service1.totalGyeddon(gye);
               		
               	if(result!=null){ 
               		setResultMsg(out, result_obj, result, gye+"를 선택하셨습니다");                		                                                                     	
               	}else{
               		setResultMsg(out, result_obj, 0, "처리오류");                                   	
               	}               	
        	}else if(sign.equals("team2")){
               	String candidate=(String) obj.get("name");                                                       	
               	System.out.println(candidate);
               	
               	service2.voteForCandidate(candidate);
               	Object result=service2.totalVotesFor(candidate);
               		
               	if(result!=null){ 
               		setResultMsg(out, result_obj, result, candidate+"를 후원하셨습니다");                     	                                                                    	
               	}else{
               		setResultMsg(out, result_obj, 0, "처리오류");                                        	
               	}               	
        	}else if(sign.equals("team3")){
               	String token=(String) obj.get("name");                                                       	
               	System.out.println(token);
               	
               	service3.setNumber(token);
               	Object result=service3.getNumber();
               	
               	obj=new JSONObject();	
               	if(result!=null){ 
               		setResultMsg(out, result_obj, result, token+"이 전달되었습니다");                  	                                                                  	
               	}else{
               		setResultMsg(out, result_obj, 0, "처리오류");                   	                                    	
               	}               	
        	}
     	}            
	}
	
	private void setResultMsg(PrintWriter out, JSONObject obj, Object result, String message) {
		obj.put("result", result);
      	obj.put("message", message); 
      	out.print(obj);
	}
}
  
