package serviceboard.model.vo;

import java.sql.Date;

public class ServiceComment {
	private int commentNo;
	private String content;
	private String userId;
	private Date cRegdate;
	private int serviceNo;
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getcRegdate() {
		return cRegdate;
	}
	public void setcRegdate(Date cRegdate) {
		this.cRegdate = cRegdate;
	}
	public int getServiceNo() {
		return serviceNo;
	}
	public void setServiceNo(int serviceNo) {
		this.serviceNo = serviceNo;
	}
	
	

}
