package serviceboard.model.vo;

import java.util.ArrayList;

public class ServiceCommentPage {
	private ArrayList<ServiceComment> pageList=null;
	private String pageNavi=null;
	public ArrayList<ServiceComment> getPageList() {
		return pageList;
	}
	public void setPageList(ArrayList<ServiceComment> pageList) {
		this.pageList = pageList;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
}
