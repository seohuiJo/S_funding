package requestboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import requestboard.model.service.RequestService;

/**
 * Servlet implementation class RequestCommentDeleteServlet
 */
@WebServlet("/deleteRequestComment")
public class RequestCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestCommentDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		int requestNo = Integer.parseInt(request.getParameter("requestNo"));
		int result = new RequestService().deleteRequestComment(commentNo);
		if(result>0) {
			response.sendRedirect("/requestSelect?requestNo="+requestNo);
		} else {
			response.sendRedirect("/views/requestboard/requestError.html");
			System.out.println("댓글 삭제 실패");
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
