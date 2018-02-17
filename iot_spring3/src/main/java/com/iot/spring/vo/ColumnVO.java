package com.iot.spring.vo;

public class ColumnVO {
	private String id;
	private String columnName;
	private String dataType;
	private int maxLength;
	private String isNull;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public int getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}
	public String getIsNull() {
		return isNull;
	}
	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}
	@Override
	public String toString() {
		return "ColumnVO [columnName=" + columnName + ", dataType=" + dataType + ", maxLength=" + maxLength
				+ ", isNull=" + isNull + "]";
	}
}
