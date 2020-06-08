package message.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import message.model.service.MessageService;
import message.model.vo.MessagePageData;

/**
 * Servlet implementation class MessageServlet
 */
@WebServlet("/messageList")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MessageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 현재 페이지 정보
		int currentPage = 0;
		if (request.getParameter("currentPage") == null) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		// 현재 로그인한 유저 정보
		HttpSession session = request.getSession();
		String userId = "";
		if (session != null & (session.getAttribute("member") != null)) {
			// 로그인 했을때
			userId = ((Member) session.getAttribute("member")).getUserId(); // userId
		}
		MessagePageData pageData = new MessageService().messageSelectList(currentPage, userId);
		RequestDispatcher view = request.getRequestDispatcher("/views/message/messageList.jsp");
		request.setAttribute("pageData", pageData);
		request.setAttribute("size", pageData.getPageList().size());
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("userId",pageData.getPageList().get(0).getUserId());
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
