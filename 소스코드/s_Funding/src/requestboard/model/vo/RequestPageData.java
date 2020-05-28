package requestboard.model.vo;

import java.util.ArrayList;

public class RequestPageData {
	private ArrayList<RequestBoard> pageList=null;
	private String pageNavi=null;
	
	public RequestPageData() {}

	public ArrayList<RequestBoard> getPageList() {
		return pageList;
	}

	public void setPageList(ArrayList<RequestBoard> pageList) {
		this.pageList = pageList;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
	
}
