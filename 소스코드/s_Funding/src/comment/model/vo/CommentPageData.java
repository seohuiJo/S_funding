package comment.model.vo;

import java.util.ArrayList;



public class CommentPageData {
	private ArrayList<Comment> pageList = null;
	private String pageNavi = null;
	
	public CommentPageData() {
		
	}

	public ArrayList<Comment> getPageList() {
		return pageList;
	}

	public void setPageList(ArrayList<Comment> pageList) {
		this.pageList = pageList;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
	

}
