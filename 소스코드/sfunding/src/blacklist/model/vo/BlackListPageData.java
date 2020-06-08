package blacklist.model.vo;

import java.util.ArrayList;

public class BlackListPageData {
	private ArrayList<BlackList> pageList = null;
	private String pageNavi = null;
	private int size;

	public ArrayList<BlackList> getPageList() {
		return pageList;
	}

	public void setPageList(ArrayList<BlackList> pageList) {
		this.pageList = pageList;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
