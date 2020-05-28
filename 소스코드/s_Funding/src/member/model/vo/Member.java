package member.model.vo;

import java.sql.Date;

public class Member {

   private String userId;
   private String userPwd;
   private String userName;
   private String phone;
   private String nickname;
   private String address;
   private String email;
   private int point;
   private int enabled;
   private Date uRegdate;
   private String interest;
   
   public Member() {}

   public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public String getUserId() {
      return userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }

   public String getUserPwd() {
      return userPwd;
   }

   public void setUserPwd(String userPwd) {
      this.userPwd = userPwd;
   }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   

   public String getNickname() {
      return nickname;
   }

   public void setNickname(String nickname) {
      this.nickname = nickname;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public int getPoint() {
      return point;
   }

   public void setPoint(int point) {
      this.point = point;
   }

   public int getEnabled() {
      return enabled;
   }

   public void setEnabled(int enabled) {
      this.enabled = enabled;
   }

   public Date getuRegdate() {
      return uRegdate;
   }

   public void setuRegdate(Date uRegdate) {
      this.uRegdate = uRegdate;
   }

   public String getInterest() {
      return interest;
   }

   public void setInterest(String interest) {
      this.interest = interest;
   }
   
   
}