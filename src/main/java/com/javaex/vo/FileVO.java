package com.javaex.vo;

public class FileVO {
	//필드
	private int userNo;
	private String orgName;
	private String exName;
	private String saveName;
	private String filePath;
	private long fileSize;
	
	//생성자
	public FileVO() {
		super();
	}
	
	public FileVO(int userNo, String orgName, String exName, String saveName, String filePath, long fileSize) {
		super();
		this.userNo = userNo;
		this.orgName = orgName;
		this.exName = exName;
		this.saveName = saveName;
		this.filePath = filePath;
		this.fileSize = fileSize;
	}
	
	//메소드gs
	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getExName() {
		return exName;
	}
	public void setExName(String exName) {
		this.exName = exName;
	}
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	
	//메소드일반
	@Override
	public String toString() {
		return "FileVO [orgName=" + orgName + ", exName=" + exName + ", saveName=" + saveName + ", filePath=" + filePath
				+ ", fileSize=" + fileSize + "]";
	}
	
}
