package web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/main")
public class MainServlet extends HttpServlet {
	


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
		
	}

protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String sign=request.getParameter("sign");
		if(sign !=null)
		{  if(sign.equals("login")) {
			String id=request.getParameter("id");
			String pw=request.getParameter("pw");
			System.out.println(id+":"+pw);
			
			RequestDispatcher disp=request.getRequestDispatcher("spon.jsp");
			request.setAttribute("id", id);
			disp.forward(request, response);
		}else if(sign.equals("spon")) {
			String amount=request.getParameter("amount");
			//이더리움 네트웍 접속...
			
		}
			
		}
		else {
			//침해대응코드 해킹처리
			RequestDispatcher disp=request.getRequestDispatcher("error.jsp");
			request.setAttribute("errMsg", "올바른 요청이 아닙니다");
			disp.forward(request, response);
		}
	}
}
