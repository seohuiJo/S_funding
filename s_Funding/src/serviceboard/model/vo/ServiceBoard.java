package serviceboard.model.vo;

import java.sql.Date;

public class ServiceBoard {
	private int serviceNo;
	private String userId;
	private String serviceTitle;
	private String serviceContent;
	private String serviceCategory;
	private Date rRegdate;
	
	public ServiceBoard() {}
	
	public int getServiceNo() {
		return serviceNo;
	}
	public void setServiceNo(int serviceNo) {
		this.serviceNo = serviceNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getServiceTitle() {
		return serviceTitle;
	}
	public void setServiceTitle(String serviceTitle) {
		this.serviceTitle = serviceTitle;
	}
	public String getServiceContent() {
		return serviceContent;
	}
	public void setServiceContent(String serviceContent) {
		this.serviceContent = serviceContent;
	}
	public String getServiceCategory() {
		return serviceCategory;
	}
	public void setServiceCategory(String serviceCategory) {
		this.serviceCategory = serviceCategory;
	}
	public Date getrRegdate() {
		return rRegdate;
	}
	public void setrRegdate(Date rRegdate) {
		this.rRegdate = rRegdate;
	}
}
