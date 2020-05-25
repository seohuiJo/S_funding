package blacklist.model.vo;

import java.sql.Date;

public class BlackList {

   private String userId;
   private String reason;
   private Date bRegdate;
   
   public BlackList() {}

   public String getUserId() {
      return userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }

   public String getReason() {
      return reason;
   }

   public void setReason(String reason) {
      this.reason = reason;
   }

   public Date getbRegdate() {
      return bRegdate;
   }

   public void setbRegdate(Date bRegdate) {
      this.bRegdate = bRegdate;
   }
   
}