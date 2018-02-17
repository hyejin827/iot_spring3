package com.iot.spring.vo;

public class UserInfoVO {
	private String uiName;
	private String uiId;
	private String uiPwd;	
	private String uiEmail;
	private String admin;
	
//	public int getUiNo() {
//		return uiNo;
//	}
//	public void setUiNo(int uiNo) {
//		this.uiNo = uiNo;
//	}
	public String getUiId() {
		return uiId;
	}
	public void setUiId(String uiId) {
		this.uiId = uiId;
	}
	public String getUiPwd() {
		return uiPwd;
	}
	public void setUiPwd(String uiPwd) {
		this.uiPwd = uiPwd;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public String getUiName() {
		return uiName;
	}
	public void setUiName(String uiName) {
		this.uiName = uiName;
	}
	public String getUiEmail() {
		return uiEmail;
	}
	public void setUiEmail(String uiEmail) {
		this.uiEmail = uiEmail;
	}
//	@Override
//	public String toString() {
//		return "UserInfoVO [uiNo=" + uiNo + ", uiName=" + uiName + ", uiId=" + uiId + ", uiPwd=" + uiPwd + ", uiEmail="
//				+ uiEmail + ", admin=" + admin + "]";
//	}
	@Override
	public String toString() {
		return "UserInfoVO [uiName=" + uiName + ", uiId=" + uiId + ", uiPwd=" + uiPwd + ", uiEmail=" + uiEmail
				+ ", admin=" + admin + "]";
	}
	
}
