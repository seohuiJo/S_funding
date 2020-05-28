package notice.model.vo;

import java.util.ArrayList;



public class NoticePageData {
	private ArrayList<Notice> pageList = null;
	private String pageNavi = null;
	public ArrayList<Notice> getPageList() {
		return pageList;
	}
	public void setPageList(ArrayList<Notice> pageList) {
		this.pageList = pageList;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
	

}