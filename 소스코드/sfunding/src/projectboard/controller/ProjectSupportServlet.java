package projectboard.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import message.model.service.MessageService;
import projectboard.model.service.ProjectService;

/**
 * Servlet implementation class ProjectSupportServlet
 */
@WebServlet("/projectSupport")
public class ProjectSupportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProjectSupportServlet() {
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

		HttpSession session = request.getSession();
		String userId = ((Member) session.getAttribute("member")).getUserId();
		int projectNo = Integer.parseInt(request.getParameter("projectNo"));
		int optionNo = Integer.parseInt(request.getParameter("exampleRadios"));

		// 포인트가 모자라지 않는지 확인
		int point = ((Member) session.getAttribute("member")).getPoint();
		int price = new ProjectService().getPrice(projectNo, optionNo);
		if (point < price) { // 포인트가 모자라면
			RequestDispatcher rd = request.getRequestDispatcher("common/msg.jsp");
			request.setAttribute("msg", "포인트가 모자랍니다.");
			request.setAttribute("loc", "/");
			rd.forward(request, response);
		}

		// project, product, member 업데이트, supportlist 인서트
		int result = new ProjectService().supportProject(projectNo, optionNo, userId, price);

		RequestDispatcher rd = request.getRequestDispatcher("common/msg.jsp");
		request.setAttribute("loc", "/");
		if (result > 0) {
			// 후원해준것에 대한 감사 알림
			String projectTitle = new ProjectService().projectSelect(projectNo).getProjectTitle();
			int resultMsgThanks = new MessageService().insertMessageFundingThanks(userId, projectTitle);

			// 후원 성공하고, 프로젝트 마감일에, 구매할 상품 포인트가 차감될 것이라는 알림을 보내준다.
			// 프로젝트 마감일
			Date projectEndDate = new ProjectService().projectSelect(projectNo).getEndDate();
			String endDate = new SimpleDateFormat("yyyy년 MM월 dd일").format(projectEndDate);
			int resultMsgRemind = new MessageService().insertMessagePointRemind(userId, projectTitle, endDate, price);

			if (resultMsgThanks > 0 && resultMsgRemind > 0) {
				request.setAttribute("msg", "후원 성공!");
			} else {
				request.setAttribute("msg", "후원 메시지 전송 실패!");
			}
		} else {
			request.setAttribute("msg", "후원 실패!");
		}
		rd.forward(request, response);
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
