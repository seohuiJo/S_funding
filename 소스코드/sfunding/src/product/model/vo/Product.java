package product.model.vo;

public class Product {
	private int optionNo;
	private int projectNo;
	private String productName;
	private String option;
	private int sponsorCount;
	private int price;

	public Product() {
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public int getSponsorCount() {
		return sponsorCount;
	}

	public void setSponsorCount(int sponsorCount) {
		this.sponsorCount = sponsorCount;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}