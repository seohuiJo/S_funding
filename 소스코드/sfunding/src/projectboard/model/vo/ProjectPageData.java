package projectboard.model.vo;

import java.util.ArrayList;

import filetbl.model.vo.File;

public class ProjectPageData {
	private ArrayList<ProjectBoard> pageList = null;
	private ArrayList<File> fileList=null;
	private String pageNavi = null;
	
	public ProjectPageData() {
		
	}

	public ArrayList<ProjectBoard> getPageList() {
		return pageList;
	}

	public void setPageList(ArrayList<ProjectBoard> pageList) {
		this.pageList = pageList;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}

	public ArrayList<File> getFileList() {
		return fileList;
	}

	public void setFileList(ArrayList<File> fileList) {
		this.fileList = fileList;
	}
	
	
}