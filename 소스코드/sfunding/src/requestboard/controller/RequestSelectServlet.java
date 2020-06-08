package requestboard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import requestboard.model.service.RequestService;
import requestboard.model.vo.RequestBoard;

/**
 * Servlet implementation class RequestListSelect
 */
@WebServlet("/requestSelect")
public class RequestSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RequestSelectServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 요청글 번호 가져옴
		int requestNo = Integer.parseInt(request.getParameter("requestNo"));
		RequestBoard requestBoard = new RequestService().requestSelect(requestNo); // 글 정보 불러옴
		// 과거 공감/ 비공감 기록 불러오기
		int goodYN = 0;
		int badYN = 0;
		HttpSession session = request.getSession();
		if (session != null & (session.getAttribute("member") != null)) {
			// 로그인 했을때
			String userId = ((Member) session.getAttribute("member")).getUserId(); // userId
			goodYN = new RequestService().checkStatusGood(requestNo, userId); // 공감 기록 가져오기
			badYN = new RequestService().checkStatusBad(requestNo, userId); // 비공감 기록 가져오기
		} else {
			// 로그인 안했을때
		}

		// 글정보 있으면
		if (requestBoard != null) {
			RequestDispatcher view = request.getRequestDispatcher("/views/requestboard/requestContent.jsp");
			request.setAttribute("requestBoard", requestBoard);
			// 공감, 비공감 정보
			request.setAttribute("goodYN", goodYN);
			request.setAttribute("badYN", badYN);
			// 화면에 데이터 전송
			view.forward(request, response);
		} else {
			// 에러 페이지로 보내기
			response.sendRedirect("/views/requestboard/requestError.html");
		}
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
