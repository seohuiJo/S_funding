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
 * Servlet implementation class FindPwdServlet
 */
@WebServlet("/findPwd")
public class FindPwdServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindPwdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //1. 전송값에 한글이 있는 경우 인코딩 처리
            request.setCharacterEncoding("utf-8");
            
            //2. view에서 보낸 전송값을 꺼내어 변수 저장
            String userId = request.getParameter("userId");
            String email= request.getParameter("email");
            String phone= request.getParameter("phone");
            //3. 비즈니스 로직을 처리할 서비스 클래스 메소드로 값을 전달 및 결과 받기
            Member member = new MemberService().findPwd(userId,email,phone);
            response.setContentType("text/html;charset=utf-8");
            if(member !=null ) {
               
               request.setAttribute("member", member);
               request.getRequestDispatcher("/views/member/AfterFindPwd.jsp").forward(request, response);
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