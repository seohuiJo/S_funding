package notice.model.vo;

import java.sql.Date;

public class Notice {
   private int noticeNo;
   private String managerId;
   private String managerName;
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
	
	public String getManagerId() {
		return managerId;
	}
	
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

   public String getManagerName() {
      return managerName;
   }

   public void setManagerName(String managerName) {
      this.managerName = managerName;
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
