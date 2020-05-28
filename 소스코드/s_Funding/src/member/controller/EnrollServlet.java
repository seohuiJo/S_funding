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
 * Servlet implementation class EnrollServlet
 */
@WebServlet("/enroll")
public class EnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 전송값에 한글이 있을 경우 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		//2. view에서 보낸 전송값을 꺼내서 변수 저장
		Member member = new Member();
		member.setUserId(request.getParameter("userId"));
		member.setUserPwd(request.getParameter("userPwd"));
		member.setUserName(request.getParameter("userName"));
		member.setAge(Integer.parseInt(request.getParameter("age")));
		member.setPhone(request.getParameter("phone"));
		member.setAddress(request.getParameter("address"));
		member.setEmail(request.getParameter("email"));
		member.setGender(request.getParameter("gender"));
		member.setHobby(request.getParameter("hobby"));
		//날짜가 없는 이유 -> DB에서 처리함
		
		
		//3. 비즈니스 로직 처리
		int result = new MemberService().insertMember(member);
		
		// 4. 받은 결과에 따라 성공 / 실패
		
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
