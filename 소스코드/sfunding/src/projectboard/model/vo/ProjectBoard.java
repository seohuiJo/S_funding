package projectboard.model.vo;

import java.sql.Date;
import java.util.ArrayList;

import commentboard.model.vo.Comment;
import filetbl.model.vo.File;

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
	private File image;
	private ArrayList<Comment> comments = null; // 댓글
	// 프로젝트 랭킹순
	private int ranking;
	private float rate;

	public ProjectBoard() {
	}

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

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public ArrayList<Comment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

}