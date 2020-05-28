package supportlist.model.vo;

import java.util.ArrayList;



public class SupportListPageData {
	private ArrayList<SupportList> pageList = null;
	private String pageNavi = null;
	public ArrayList<SupportList> getPageList() {
		return pageList;
	}
	public void setPageList(ArrayList<SupportList> pageList) {
		this.pageList = pageList;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
}
