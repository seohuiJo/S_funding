package serviceboard.model.vo;

import java.sql.Date;

public class ServiceBoard {
	private int serviceNo;
	private String userId;
	private String serviceContent;
	private String serviceCategory;
	private Date sRegdate;
	
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
	public Date getsRegdate() {
		return sRegdate;
	}
	public void setsRegdate(Date sRegdate) {
		this.sRegdate = sRegdate;
	}
}
