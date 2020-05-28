package member.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SignUpServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Member member = new Member();
		member.setUserId(request.getParameter("userId"));
		member.setUserPwd(request.getParameter("userPwd"));
		member.setUserName(request.getParameter("userName"));
		member.setPhone(request.getParameter("phone"));
		member.setNickname(request.getParameter("nickName"));
		member.setAddress(request.getParameter("address"));
		member.setEmail(request.getParameter("email"));
		/*member.setPoint(Integer.parseInt(request.getParameter("point")));
		member.setEnabled(Integer.parseInt(request.getParameter("enabled")));*/
		member.setInterest(request.getParameter("interest"));

	
		int result = new MemberService().insertMember(member);
		
		
		if(result >0) {
			response.sendRedirect("/index.jsp");
		}else {
			response.sendRedirect("/views/member/memberError.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
