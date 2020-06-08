package supportlist.model.vo;

import java.sql.Date;

public class SupportList {

   private String userId;
   private String projectContent;
   private String selluserId;
   private Date startDate;
   private Date endDate;
   private int price;
   private int sumMoney;
   private int targetMoney;
   
   public SupportList() {}

public String getUserId() {
	return userId;
}

public void setUserId(String userId) {
	this.userId = userId;
}

public String getProjectContent() {
	return projectContent;
}

public void setProjectContent(String projectContent) {
	this.projectContent = projectContent;
}

public String getSelluserId() {
	return selluserId;
}

public void setSelluserId(String selluserId) {
	this.selluserId = selluserId;
}

public Date getStartDate() {
	return startDate;
}

public void setStartDate(Date startDate) {
	this.startDate = startDate;
}

public Date getEndDate() {
	return endDate;
}

public void setEndDate(Date endDate) {
	this.endDate = endDate;
}

public int getPrice() {
	return price;
}

public void setPrice(int price) {
	this.price = price;
}

public int getSumMoney() {
	return sumMoney;
}

public void setSumMoney(int sumMoney) {
	this.sumMoney = sumMoney;
}

public int getTargetMoney() {
	return targetMoney;
}

public void setTargetMoney(int targetMoney) {
	this.targetMoney = targetMoney;
}

@Override
public String toString() {
	return "SupportList [userId=" + userId + ", projectContent=" + projectContent + ", selluserId=" + selluserId
			+ ", startDate=" + startDate + ", endDate=" + endDate + ", price=" + price + ", sumMoney=" + sumMoney
			+ ", targetMoney=" + targetMoney + "]";
}

 

  






   
}