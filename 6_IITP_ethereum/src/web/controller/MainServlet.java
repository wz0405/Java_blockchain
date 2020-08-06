package web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import web.service.Team1Service;
import web.service.Team2Service;

@WebServlet("/main")
public class MainServlet extends HttpServlet {	
	Team1Service service;
	Team2Service service2;
	public void init() throws ServletException {	
		try {
			service = new Team1Service();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			service2=new Team2Service();
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
       	request.setCharacterEncoding("UTF-8");
      	response.setContentType("application/json;charset=utf-8");
      	PrintWriter out=response.getWriter();
      	BufferedReader br=request.getReader();
      	if(br==null){
                  	System.out.println("BufferedReader is null");
      	}else{
                  	JSONObject obj=(JSONObject)JSONValue.parse(br);         	       	
                  	if(obj==null){
                             	System.out.println("JSONObject is null");            	                  	
                  	}else{
                             	System.out.println(obj);
                             	String sign=(String) obj.get("sign");
                             	if(sign==null){
                                        	System.out.println("sign is null");
                             	}else{                                                         	                                         	
                                        	if(sign.equals("team1")){
                                                   	String gye=(String) obj.get("name");                                                       	
                                                   	System.out.println(gye);
                                                   	
                                                   	service.voteForCandidate(gye);
                                                   	Object result=service.totalVotesFor(gye);
                                                   	
                                                   	obj=new JSONObject();	
                                                   	if(result!=null){ 
                                                              	obj.put("result", result);
                                                              	obj.put("message", gye+"를 선택하셨습니다");                                                                     	
                                                   	}else{
                                                              	obj.put("result", 0);
                                                              	obj.put("message", "다시 로그인 해주세요");                                       	
                                                   	}
                                                   	out.print(obj);
                                        	}else if(sign.equals("team2")){
                                               	String candidate=(String) obj.get("name");                                                       	
                                               	System.out.println(candidate);
                                               	
                                               	service2.voteForCandidate(candidate);
                                               	Object result=service2.totalVotesFor(candidate);
                                               	
                                               	obj=new JSONObject();	
                                               	if(result!=null){ 
                                                          	obj.put("result", result);
                                                          	obj.put("message", candidate+"를 후원하셨습니다");                                                                     	
                                               	}else{
                                                          	obj.put("result", 0);
                                                          	obj.put("message", "다시 로그인 해주세요");                                       	
                                               	}
                                               	out.print(obj);
                                    	}
                             	}                                        	
                  	}
      	}     
	}
}


