package notice.model.vo;

import java.sql.Date;

public class Notice {
   private int noticeNo;
   private String userId;
   private String userName;
   private String noticeTitle;
   private String noticeContent;
   private Date nRegdate;
   
   public Notice() {}

public int getNoticeNo() {
	return noticeNo;
}

public void setNoticeNo(int noticeNo) {
	this.noticeNo = noticeNo;
}

public String getUserId() {
	return userId;
}

public void setUserId(String userId) {
	this.userId = userId;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getNoticeTitle() {
	return noticeTitle;
}

public void setNoticeTitle(String noticeTitle) {
	this.noticeTitle = noticeTitle;
}

public String getNoticeContent() {
	return noticeContent;
}

public void setNoticeContent(String noticeContent) {
	this.noticeContent = noticeContent;
}

public Date getnRegdate() {
	return nRegdate;
}

public void setnRegdate(Date nRegdate) {
	this.nRegdate = nRegdate;
}

  

}
