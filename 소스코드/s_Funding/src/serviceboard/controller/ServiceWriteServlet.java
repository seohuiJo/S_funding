package serviceboard.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import serviceboard.model.service.ServiceService;

/**
 * Servlet implementation class ServiceWriteServlet
 */
@WebServlet("/serviceWrite")
public class ServiceWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceWriteServlet() {
        super();
        // TODO Auto-generated constructor stub 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String serviceCategory = request.getParameter("serviceCategory");
		String serviceContent = request.getParameter("serviceContent");
		if(serviceCategory.equals("건의사항")) {
			serviceCategory = "SUGGEST";
		}else if(serviceCategory.equals("신고")) {
			serviceCategory = "REPORT";
		}else {
			serviceCategory = "QUESTION";
		}
		String userId = "kimdul";
		int result = new ServiceService().insertService(serviceCategory, serviceContent, userId);
		if (result > 0) {
			response.sendRedirect("/service");
		}else {
			response.sendRedirect("");
		}
		
		/*if (session != null && (session.getAttribute("member") != null)) {
			String userId = ((Member)session.getAttribute("member")).getUserId();
			int result = new ServiceService().insertService(serviceCategory, serviceContent, userId);
			if (result > 0) {
				response.sendRedirect("/service");
			}else {
				response.sendRedirect("");
			}
		}else {
			response.sendRedirect("");
		}*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
