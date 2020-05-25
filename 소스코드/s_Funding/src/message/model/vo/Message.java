package message.model.vo;

import java.sql.Date;

public class Message {
   private int messageNo;
   private String userId;
   private String nickname;
   private String messageContent;
   private Date mRegdate;
   
   public Message() {}

   public int getMessageNo() {
      return messageNo;
   }

   public void setMessageNo(int messageNo) {
      this.messageNo = messageNo;
   }

   public String getUserId() {
      return userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }

   public String getNickname() {
      return nickname;
   }

   public void setNickname(String nickname) {
      this.nickname = nickname;
   }

   public String getMessageContent() {
      return messageContent;
   }

   public void setMessageContent(String messageContent) {
      this.messageContent = messageContent;
   }

   public Date getmRegdate() {
      return mRegdate;
   }

   public void setmRegdate(Date mRegdate) {
      this.mRegdate = mRegdate;
   }
   
}