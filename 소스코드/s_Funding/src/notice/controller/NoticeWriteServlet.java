package notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import notice.model.service.NoticeService;

/**
 * Servlet implementation class NoticeWriteServlet
 */
@WebServlet("/NoticeWriteServlet")
public class NoticeWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		HttpSession session=request.getSession();
		if(session !=null && (session.getAttribute("member")!=null)) {
			String userId=((Member)session.getAttribute("member")).getUserId();
			int result=new NoticeService().insertNotice(subject,content,userId);
			if(result>0) {
				response.sendRedirect("/notice");
			}else {
				response.sendRedirect("/views/notice/noticeError.html");
			}
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
