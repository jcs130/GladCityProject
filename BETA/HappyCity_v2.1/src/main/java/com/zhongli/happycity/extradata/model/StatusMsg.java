package com.zhongli.happycity.extradata.model;

public class StatusMsg {

	private int code;
	private String name;

	public StatusMsg(int code, String name) {
		this.name = name;
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "StatusMsg [code=" + code + ", name=" + name + "]";
	}

}
