package com.rummycircle.model;

import java.util.List;

public class TableResponse {
	private boolean success;
	private List<Value> value;
	private long errorCode;
	private boolean hasMoreRecords;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<Value> getValues() {
		return value;
	}
	public void setValues(List<Value> values) {
		this.value = values;
	}
	public long getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(long errorCode) {
		this.errorCode = errorCode;
	}
	public boolean isHasMoreRecords() {
		return hasMoreRecords;
	}
	public void setHasMoreRecords(boolean hasMoreRecords) {
		this.hasMoreRecords = hasMoreRecords;
	}
	@Override
	public String toString() {
		return "TableResponse [success=" + success + ", values=" + value
				+ ", errorCode=" + errorCode + ", hasMoreRecords="
				+ hasMoreRecords + "]";
	}
	
	
	

}
