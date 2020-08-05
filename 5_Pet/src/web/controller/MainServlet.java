package web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.EthereumPetService;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	EthereumPetService vote;

	public void init() { // 한번만 호출되는 메서드
		try {
			vote = new EthereumPetService();
			System.out.println("EthereumPetService객체 생성됨");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 생성될때만 호출

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			String sign = request.getParameter("sign");
			if (sign != null) {
				if (sign.equals("pet")) {
					String change = request.getParameter("change");
					
					RequestDispatcher disp = request.getRequestDispatcher("result.jsp");
					
					
				
					// 투표하기
					
					vote.PetForChange(change);
					// 득표수 보기
					Object result=vote.totalPetFor(change);
					request.setAttribute("name", change);
					request.setAttribute("result", result);
					disp.forward(request, response);
					// 요청시 많은 객체가 생성됨
					// 데이터는 없지만 메서드 영역에 부담을 준다.

				}

			} else {
				RequestDispatcher disp = request.getRequestDispatcher("error.jsp");
				request.setAttribute("errMsg", "잘못된 요청입니다");
				disp.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
