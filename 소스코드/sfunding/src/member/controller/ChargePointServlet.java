package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import message.model.service.MessageService;

/**
 * Servlet implementation class AddPointServlet
 */
@WebServlet("/pointCharge")
public class ChargePointServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChargePointServlet() {
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

		String userId = request.getParameter("userId");
		int point = Integer.parseInt(request.getParameter("point"));

		int result = new MemberService().chargePoint(userId, point);
		if (result > 0) {
			// 포인트 충전에 성공하면 포인트 충전했다는 알림을 보내준다
			// 충전후 잔여 포인트
			int totalPoint = new MemberService().selectMemberOne(userId).getPoint();
			// 충전포인트, 잔여포인트를 알람으로 보내준다.
			int resultMsg = new MessageService().insertMessageAddPoint(userId, point, totalPoint);

			if (resultMsg > 0) {
				response.sendRedirect("/myinfo?userId=" + userId);
			} else {
				System.out.println("포인트 증가 메세지 전송 에러");
			}
		} else {
			System.out.println("메롱");
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
