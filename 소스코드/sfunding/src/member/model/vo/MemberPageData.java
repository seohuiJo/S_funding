package member.model.vo;

import java.util.ArrayList;

public class MemberPageData {

	private ArrayList<Member> pageList = null;
	private String pageNavi = null;

	public MemberPageData() {
	}

	public ArrayList<Member> getPageList() {
		return pageList;
	}

	public void setPageList(ArrayList<Member> pageList) {
		this.pageList = pageList;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}

}