package web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.EthereumSponService;


@WebServlet("/main")
public class MainServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String sign=request.getParameter("sign");
		if(sign != null) {
			if(sign.equals("spon")) {
				String amount=request.getParameter("amount");
				EthereumSponService spon=new EthereumSponService();
				spon.service(amount);
			}
			
		}else {
			RequestDispatcher disp=request.getRequestDispatcher("error.jsp");
			request.setAttribute("errMsg", "잘못된 요청입니다");
			disp.forward(request, response);
		}
	}

}
