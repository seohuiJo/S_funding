package ajax.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class AjaxTest9Servlet
 */
@WebServlet("/ajaxTest9")
public class AjaxTest9Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxTest9Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<User> userList=new ArrayList<User>();
		userList.add(new User(1,"일용자","서울"));
		userList.add(new User(2,"이용자","경기"));
		userList.add(new User(3,"삼용자","강원"));
		userList.add(new User(4,"사용자","전남"));
		userList.add(new User(5,"오용자","경북"));
		// HashMap<String, User> map=new HashMap<String, User>();
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		new Gson().toJson(userList, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
