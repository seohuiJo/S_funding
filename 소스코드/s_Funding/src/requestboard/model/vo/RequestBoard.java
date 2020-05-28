package requestboard.model.vo;

import java.sql.Date;

public class RequestBoard {
	private int requestNo;
	private String userId;
	private String requestTitle;
	private String requestContent;
	private int good;
	private int bad;
	private Date rRegdate;
	private String projectList;
	
	public RequestBoard() {}

	public int getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(int requestNo) {
		this.requestNo = requestNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRequestTitle() {
		return requestTitle;
	}

	public void setRequestTitle(String requestTitle) {
		this.requestTitle = requestTitle;
	}

	public String getRequestContent() {
		return requestContent;
	}

	public void setRequestContent(String requestContent) {
		this.requestContent = requestContent;
	}

	public int getGood() {
		return good;
	}

	public void setGood(int good) {
		this.good = good;
	}

	public int getBad() {
		return bad;
	}

	public void setBad(int bad) {
		this.bad = bad;
	}

	public Date getrRegdate() {
		return rRegdate;
	}

	public void setrRegdate(Date rRegdate) {
		this.rRegdate = rRegdate;
	}

	public String getProjectList() {
		return projectList;
	}

	public void setProjectList(String projectList) {
		this.projectList = projectList;
	}
	
	
}
