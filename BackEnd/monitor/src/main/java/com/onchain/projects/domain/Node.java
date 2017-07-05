package com.onchain.projects.domain;

public class Node {
	private String id;

	private String url;

	private String type;
	//区块状态String类型
	private String blockStatus;
	//网络状态String类型
	private String rpcStatus;

	private String date;

	private String systemInfo;

	private String runningTime;

	//用于页面x，y坐标
	private Integer x;
	private Integer y;

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public String getSystemInfo() {
		return systemInfo;
	}

	public void setSystemInfo(String systemInfo) {
		this.systemInfo = systemInfo;
	}

	public String getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(String runningTime) {
		this.runningTime = runningTime;
	}

	public String getBlockStatus() {
		return blockStatus;
	}

	public void setBlockStatus(String blockStatus) {
		this.blockStatus = blockStatus;
	}

	public String getRpcStatus() {
		return rpcStatus;
	}

	public void setRpcStatus(String rpcStatus) {
		this.rpcStatus = rpcStatus;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}


	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
