package com.rummycircle.model.tablerequest;

public class Context {
	private String source;
	private long time;
	private int channelId;
	private String loginId;
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public int getChannelId() {
		return channelId;
	}
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	@Override
	public String toString() {
		return "Context [source=" + source + ", time=" + time + ", channelId="
				+ channelId + ", loginId=" + loginId + "]";
	}
	
	
}
