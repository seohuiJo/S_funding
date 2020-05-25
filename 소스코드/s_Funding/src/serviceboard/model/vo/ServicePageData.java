package serviceboard.model.vo;

import java.util.ArrayList;

public class ServicePageData {

	private ArrayList<ServiceBoard> pageList = null;
	private String pageNavi = null;
	
	public ServicePageData() {}

	public ArrayList<ServiceBoard> getPageList() {
		return pageList;
	}

	public void setPageList(ArrayList<ServiceBoard> pageList) {
		this.pageList = pageList;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
}
