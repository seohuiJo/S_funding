package projectboard.model.vo;

import java.sql.Date;

public class ProjectBoard {
   private int projectNo;
   private String userId;  
   private String projectTitle;
   private String projectContent;
   private String category;
   private Date startDate;
   private Date endDate;
   private int sponsorCount;
   private int sumMoney;
   private int targetMoney;
   private Date pRegdate;
   
   public ProjectBoard() {}

   public int getProjectNo() {
      return projectNo;
   }

   public void setProjectNo(int projectNo) {
      this.projectNo = projectNo;
   }

   public String getUserId() {
      return userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }

   public String getProjectTitle() {
      return projectTitle;
   }

   public void setProjectTitle(String projectTitle) {
      this.projectTitle = projectTitle;
   }

   public String getProjectContent() {
      return projectContent;
   }

   public void setProjectContent(String projectContent) {
      this.projectContent = projectContent;
   }

   public String getCategory() {
      return category;
   }

   public void setCategory(String category) {
      this.category = category;
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

   public int getSponsorCount() {
      return sponsorCount;
   }

   public void setSponsorCount(int sponsorCount) {
      this.sponsorCount = sponsorCount;
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

   public Date getpRegdate() {
      return pRegdate;
   }

   public void setpRegdate(Date pRegdate) {
      this.pRegdate = pRegdate;
   }
   
   
   
   

}