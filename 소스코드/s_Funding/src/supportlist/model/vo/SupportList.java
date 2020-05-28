package supportlist.model.vo;

import java.sql.Date;

public class SupportList {
   private String userId;
   private int optionNo;
   private int projectNo;
   private int supportMoney;
   private Date sRegdate;
   
   public SupportList() {}

   public String getUserId() {
      return userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }

   public int getOptionNo() {
      return optionNo;
   }

   public void setOptionNo(int optionNo) {
      this.optionNo = optionNo;
   }

   public int getProjectNo() {
      return projectNo;
   }

   public void setProjectNo(int projectNo) {
      this.projectNo = projectNo;
   }

   public int getSupportMoney() {
      return supportMoney;
   }

   public void setSupportMoney(int supportMoney) {
      this.supportMoney = supportMoney;
   }

   public Date getsRegdate() {
      return sRegdate;
   }

   public void setsRegdate(Date sRegdate) {
      this.sRegdate = sRegdate;
   }
   
   

}