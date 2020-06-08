package projectboard.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import filetbl.model.vo.File;
import member.model.vo.Member;
import product.model.vo.Product;
import projectboard.model.service.ProjectService;
import projectboard.model.vo.ProjectBoard;

/**
 * Servlet implementation class ProjectModifySuccess
 */
@WebServlet("/projectModifySuccess")
public class ProjectModifySuccess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProjectModifySuccess() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		request.setCharacterEncoding("utf-8");
		// multipart인지 확인\
		if (!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher rd = request.getRequestDispatcher("common/msg.jsp");
			request.setAttribute("msg", "[enctype]확인");
			rd.forward(request, response);
			return;
		}
		// 파일 저장 경로 설정₩
		String root = request.getSession().getServletContext().getRealPath("/");
		String saveDirectory = root + "upload/photo";
		// 파일 최대 크기 설정
		int maxSize = 10 * 1024 * 1024;
		// request -> MultipartRequest 파일업로드 진행
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());

		// user_id 가져오기
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		System.out.println(member.toString());
		String userId = ((Member) session.getAttribute("member")).getUserId();

		// projectNo 가져오기
		int projectNo = Integer.parseInt(mRequest.getParameter("projectNo"));

		// date 가져오기 (모든 데이터 수정 가능하게 하기) 글쓰기에 날짜받는거 없어서 추가하겠음.!
		String endDate = mRequest.getParameter("end_date");
		Date endDater = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			endDater = simpleDateFormat.parse(endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// project 세팅
		ProjectBoard project = new ProjectBoard();
		String project_title = mRequest.getParameter("project_title");
		project.setProjectNo(projectNo);
		project.setProjectTitle(project_title);
		project.setProjectContent(mRequest.getParameter("project_content"));
		project.setCategory(mRequest.getParameter("projectCategory"));
		project.setTargetMoney(Integer.parseInt(mRequest.getParameter("target_money")));
		int projectResult = new ProjectService().ModifyProject(project, userId, projectNo, endDate);
		if (projectResult > 0) {
			projectNo = new ProjectService().selectProjectNo(userId, project_title);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("common/msg.jsp");
			request.setAttribute("msg", "프로젝트 등록 실패");
			rd.forward(request, response);
		}

		// option 세팅
		Product product1 = new Product();
		product1.setProductName(mRequest.getParameter("option1_name"));
		product1.setOption(mRequest.getParameter("option1_product"));
		product1.setPrice(Integer.parseInt(mRequest.getParameter("option1_price")));
		product1.setOptionNo(1);
		product1.setProjectNo(projectNo);

		// option2 세팅
		Product product2 = new Product();
		product2.setProductName(mRequest.getParameter("option2_name"));
		product2.setOption(mRequest.getParameter("option2_product"));
		product2.setPrice(Integer.parseInt(mRequest.getParameter("option2_price")));
		product2.setOptionNo(2);
		product2.setProjectNo(projectNo);

		// option3 세팅
		Product product3 = new Product();
		product3.setProductName(mRequest.getParameter("option3_name"));
		product3.setOption(mRequest.getParameter("option3_product"));
		product3.setPrice(Integer.parseInt(mRequest.getParameter("option3_price")));
		product3.setOptionNo(3);
		product3.setProjectNo(projectNo);

		System.out.println(product1.toString());
		System.out.println(product2.toString());
		System.out.println(product3.toString());

		int productResult1 = new ProjectService().modifyProduct(product1);
		int productResult2 = new ProjectService().modifyProduct(product2);
		int productResult3 = new ProjectService().modifyProduct(product3);

		System.out.println("productResult1 : " + productResult1);
		System.out.println("productResult2 : " + productResult2);
		System.out.println("productResult3	 : " + productResult3);

		if (productResult1 < 0 || productResult2 < 0 || productResult3 < 0) {
			// product 하나라도 insert 실패하면
			RequestDispatcher rd = request.getRequestDispatcher("common/msg.jsp");
			request.setAttribute("msg", "옵션 등록 실패");
			rd.forward(request, response);
		}

		String image1 = mRequest.getOriginalFileName("image1"); // 사진 파일 이름
		String image2 = mRequest.getOriginalFileName("image2");
		String image3 = mRequest.getOriginalFileName("image3");

		// 기존에 사용했던 이미지 삭제
		String oldFile1 = mRequest.getParameter("oldFilename1");
		String oldFile2 = mRequest.getParameter("oldFilename2");
		String oldFile3 = mRequest.getParameter("oldFilename3");
		java.io.File fileDelete1 = new java.io.File(saveDirectory + "/" + oldFile1);
		java.io.File fileDelete2 = new java.io.File(saveDirectory + "/" + oldFile2);
		java.io.File fileDelete3 = new java.io.File(saveDirectory + "/" + oldFile3);

		String newFile1 = image1;
		String newFile2 = image2;
		String newFile3 = image3;

		System.out.println("newFile1 = " + newFile1 + "  oldFile1 = " + oldFile1);
		System.out.println("newFile2 = " + newFile2 + "  oldFile2 = " + oldFile2);
		System.out.println("newFile3 = " + newFile3 + "  oldFile3 = " + oldFile3);

		if (oldFile1 != newFile1) {
			fileDelete1.delete();
		}
		if (oldFile2 != newFile2) {
			fileDelete2.delete();
		}
		if (oldFile3 != newFile3) {
			fileDelete3.delete();
		}

		// 기존이미지와 추가 이미지를 같이 사용하기
		image1 = (image1 == null) ? mRequest.getParameter("oldFilename1") : image1;
		image2 = (image2 == null) ? mRequest.getParameter("oldFilename2") : image2;
		image3 = (image3 == null) ? mRequest.getParameter("oldFilename3") : image3;

		System.out.println(image1);

		String filePath1 = mRequest.getFilesystemName("image1"); // 사진 파일 경로
		String filePath2 = mRequest.getFilesystemName("image2");
		String filePath3 = mRequest.getFilesystemName("image3");
		// 기존값 가져오고, 기존값을 삭제하고 새로운 파일올리면 새로운값으로 대체
		filePath1 = (filePath1 == null) ? mRequest.getParameter("oldFilepath1") : filePath1;
		filePath2 = (filePath2 == null) ? mRequest.getParameter("oldFilepath2") : filePath2;
		filePath3 = (filePath3 == null) ? mRequest.getParameter("oldFilepath3") : filePath3;

		System.out.println("image1 : " + image1);
		System.out.println("image2 : " + image2);
		System.out.println("image3 : " + image3);
		System.out.println("filePath1 : " + filePath1);
		System.out.println("filePath2 : " + filePath2);
		System.out.println("filePath3 : " + filePath3);

		// 대표사진
		File f1 = new File();
		f1.setFileNo(Integer.parseInt(mRequest.getParameter("fileNo1")));
		f1.setFileName(image1);
		f1.setFilePath(filePath1);
		f1.setUserId(userId);
		f1.setProjectNo(Integer.parseInt(mRequest.getParameter("projectNo")));
		System.out.println("t1.tos=" + f1.toString());

		// 이미지1
		File f2 = new File();
		f2.setFileNo(Integer.parseInt(mRequest.getParameter("fileNo2")));
		f2.setFileName(image2);
		f2.setFilePath(filePath2);
		f2.setUserId(userId);
		f2.setProjectNo(Integer.parseInt(mRequest.getParameter("projectNo")));

		// 이미지2
		File f3 = new File();
		f3.setFileNo(Integer.parseInt(mRequest.getParameter("fileNo3")));
		f3.setFileName(image3);
		f3.setFilePath(filePath3);
		f3.setUserId(userId);
		f3.setProjectNo(Integer.parseInt(mRequest.getParameter("projectNo")));

		// 비지니스로직처리
		int fileResult1 = new ProjectService().ModifyPhoto(f1);
		int fileResult2 = new ProjectService().ModifyPhoto(f2);
		int fileResult3 = new ProjectService().ModifyPhoto(f3);

		// 다음 페이지 설정
		RequestDispatcher rd = request.getRequestDispatcher("common/msg.jsp");
		request.setAttribute("loc", "/");
		if (fileResult1 > 0 && fileResult2 > 0 && fileResult3 > 0) {
			request.setAttribute("msg", "수정 등록 성공");
		} else {
			request.setAttribute("msg", "파일 등록 실패");
		}
		rd.forward(request, response);
	}

}
