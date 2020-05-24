package comment.model.vo;

import java.sql.Date;

public class Comment {
   private int commentNo;
   private int projectNo;
   private int requestNo;
   private String content;
   private String userId;
   private String boardType;
   private Date cRegdate;
   
   public Comment() {}

   public int getCommentNo() {
      return commentNo;
   }

   public void setCommentNo(int commentNo) {
      this.commentNo = commentNo;
   }

   public int getProjectNo() {
      return projectNo;
   }

   public void setProjectNo(int projectNo) {
      this.projectNo = projectNo;
   }

   public int getRequestNo() {
      return requestNo;
   }

   public void setRequestNo(int requestNo) {
      this.requestNo = requestNo;
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

   public String getBoardType() {
      return boardType;
   }

   public void setBoardType(String boardType) {
      this.boardType = boardType;
   }

   public Date getcRegdate() {
      return cRegdate;
   }

   public void setcRegdate(Date cRegdate) {
      this.cRegdate = cRegdate;
   }
   
   

}