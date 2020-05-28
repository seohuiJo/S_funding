package message.model.vo;

import java.util.ArrayList;



public class MessagePageData {
	private ArrayList<Message> pageList = null;
	private String pageNavi = null;
	public ArrayList<Message> getPageList() {
		return pageList;
	}
	public void setPageList(ArrayList<Message> pageList) {
		this.pageList = pageList;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
	
}
