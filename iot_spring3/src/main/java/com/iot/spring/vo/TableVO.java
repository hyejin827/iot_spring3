package com.iot.spring.vo;

public class TableVO {
	private String tableName;
	private String tableComment;
	private double tableSize;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableComment() {
		return tableComment;
	}
	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}
	public double getTableSize() {
		return tableSize;
	}
	public void setTableSize(double tableSize) {
		this.tableSize = tableSize;
	}
	@Override
	public String toString() {
		return "TableVO [tableName=" + tableName + ", tableComment=" + tableComment + ", tableSize=" + tableSize + "]";
	}
}
