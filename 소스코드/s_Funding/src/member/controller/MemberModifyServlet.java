package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberModifyServlet
 */
@WebServlet("/mUpdate")
public class MemberModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// view에서 보낸 전송값을 꺼내서 변 수저장
		Member member = new Member();
		member.setUserId(request.getParameter("userId"));
		member.setUserPwd(request.getParameter("userPwd"));
		member.setPhone(request.getParameter("phone"));
		member.setAddress(request.getParameter("address"));
		member.setEmail(request.getParameter("email"));
		member.setGender(request.getParameter("gender"));
		member.setHobby(request.getParameter("hobby"));
		
		//비즈니스 로직
		int result = new MemberService().updateMember(member);
		
		if(result >0) {
			response.sendRedirect("/index.jsp");
		}else {
			response.sendRedirect("/views/member/memberError.html");
		}
		
		// 받은 결과에 따라서 성공 실패 메시지 내보내기
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
