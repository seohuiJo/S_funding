package file.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import file.model.service.FileService;
import file.model.vo.FileData;
import member.model.vo.Member;

/**
 * Servlet implementation class UpFileServlet
 */
@WebServlet("/upload")
public class UpFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String upUserId=((Member)session.getAttribute("member")).getUserId();
		int uploadFileSizeLimit=5*1024*1024; // 최대 업로드 파일크기 5KB
		String encType="UTF-8";
		String uploadFilePath="D:\\home\\"+upUserId; // 실제 업로드 경로
		
		MultipartRequest multi=new MultipartRequest(request, uploadFilePath, uploadFileSizeLimit, encType);
		String fileName=multi.getFilesystemName("upfile");
		File file=new File(uploadFilePath+"/"+fileName);
		String filepath=file.getPath();
		long fileSize=file.length();
		String userName=upUserId;
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		Timestamp upTime=Timestamp.valueOf(formatter.format(Calendar.getInstance().getTimeInMillis()));
		
		FileData data=new FileData();
		data.setFileName(fileName);
		data.setFilePath(filepath);
		data.setFileSize(fileSize);
		data.setFileUser(userName);
		data.setUploadTime(upTime);
		int result=new FileService().uploadFile(data);
		
		if(fileName==null && result>0) {
			System.out.println("업로드 실패!");
		}else {
			RequestDispatcher view=request.getRequestDispatcher("/views/file/uploadSuccess.jsp");
			request.setAttribute("fileData", data);
			view.forward(request, response);
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
