package serviceboard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import serviceboard.model.service.ServiceService;
import serviceboard.model.vo.ServiceBoard;

/**
 * Servlet implementation class ServiceModifyServlet
 */
@WebServlet("/serviceModify")
public class ServiceModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int serviceNo = Integer.parseInt(request.getParameter("serviceNo"));
		String serviceContent = request.getParameter("serviceContent");
		String serviceCategory = request.getParameter("serviceCategory");		
		
		int service = new ServiceService().serviceModify(serviceNo, serviceContent, serviceCategory);
		if (service < 0) {
			response.sendRedirect("/service");
		}else {
			response.sendRedirect("/serviceSelect?serviceNo="+serviceNo);
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
