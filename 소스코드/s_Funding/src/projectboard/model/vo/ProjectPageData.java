package projectboard.model.vo;

import java.util.ArrayList;

public class ProjectPageData {
	private ArrayList<ProjectBoard> pageList = null;
	private String pageNavi = null;
	
	public ProjectPageData() {
		
	}

	public ArrayList<ProjectBoard> getPageList() {
		return pageList;
	}

	public void setPageList(ArrayList<ProjectBoard> pageList) {
		this.pageList = pageList;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
}