package com.rummycircle.model.tablerequest;

public class TableRequest {
	
	private Value value;
	private Context context;
	public Value getValue() {
		return value;
	}
	public void setValue(Value value) {
		this.value = value;
	}
	public Context getContext() {
		return context;
	}
	public void setContext(Context context) {
		this.context = context;
	}
	@Override
	public String toString() {
		return "TableRequest [value=" + value + ", context=" + context + "]";
	}
	
	

}
