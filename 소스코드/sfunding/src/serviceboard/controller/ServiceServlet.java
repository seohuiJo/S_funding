package serviceboard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import serviceboard.model.service.ServiceService;
import serviceboard.model.vo.ServicePageData;

/**
 * Servlet implementation class ServiceServlet
 */
@WebServlet("/service")
public class ServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServiceServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 전송값에 한글이 있을 경우 인코딩
		// 2. View에서 보낸 전송값 변수 저장
		// 3. 비즈니스 로직을 처리할 서비스 클래스 메소드로 값을 전달 및 결과 받기

		int currentPage = 0;
		// href="/notice?currentPage=1"
		if (request.getParameter("currentPage") == null) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}

		ServicePageData pd = new ServiceService().selectServiceList(currentPage);

		RequestDispatcher view = request.getRequestDispatcher("views/service/serviceList.jsp");
		request.setAttribute("pageData", pd);
		request.setAttribute("size", pd.getPageList().size());
		request.setAttribute("currentPage", currentPage);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
