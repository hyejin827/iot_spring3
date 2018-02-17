package com.iot.spring.vo;

import java.util.Arrays;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class ConnectionInfoVO {
	private int ciNo;
	private String ciName;
	private String ciUrl;
	private int ciPort;
	private String ciDatabase;
	private String ciUser;
	private String ciPwd;
	private String ciEtc;
	private String uiId;
	private int id;
	private String text;
	private Object[] items;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Object[] getItems() {
		return items;
	}
	public void setItems(Object[] items) {
		this.items = items;
	}
	public String getUiId() {
		return uiId;
	}
	public void setUiId(String uiId) {
		this.uiId = uiId;
	}
	public int getCiNo() {
		return ciNo;
	}
	public void setCiNo(int ciNo) {
		this.ciNo = ciNo;
	}
	public String getCiName() {
		return ciName;
	}
	public void setCiName(String ciName) {
		this.ciName = ciName;
	}
	public String getCiUrl() {
		return ciUrl;
	}
	public void setCiUrl(String ciUrl) {
		this.ciUrl = ciUrl;
	}
	public int getCiPort() {
		return ciPort;
	}
	public void setCiPort(int ciPort) {
		this.ciPort = ciPort;
	}
	public String getCiDatabase() {
		return ciDatabase;
	}
	public void setCiDatabase(String ciDatabase) {
		this.ciDatabase = ciDatabase;
	}
	public String getCiUser() {
		return ciUser;
	}
	public void setCiUser(String ciUser) {
		this.ciUser = ciUser;
	}
	public String getCiPwd() {
		return ciPwd;
	}
	public void setCiPwd(String ciPwd) {
		this.ciPwd = ciPwd;
	}
	public String getCiEtc() {
		return ciEtc;
	}
	public void setCiEtc(String ciEtc) {
		this.ciEtc = ciEtc;
	}
	@Override
	public String toString() {
		return "ConnectionInfoVO [ciNo=" + ciNo + ", ciName=" + ciName + ", ciUrl=" + ciUrl + ", ciPort=" + ciPort
				+ ", ciDatabase=" + ciDatabase + ", ciUser=" + ciUser + ", ciPwd=" + ciPwd + ", ciEtc=" + ciEtc
				+ ", uiId=" + uiId + ", id=" + id + ", text=" + text + ", items=" + Arrays.toString(items) + "]";
	}
	
}
