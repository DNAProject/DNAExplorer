package com.onchain.projects.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Transaction {
	//存证块高度
	private Integer height;
	//存证编号
	private String txId;
	//存证类型
	private Integer txType;
	//手续费
	private Integer fee;
	//caKey
	private String caKey;
	//存证时间
	private String date;
	//类型描述
	private String txTypeDesc;

	private List<Map> outputs = new ArrayList<>();

	private List<Map> inputs = new ArrayList<>();

	private String assetname ;


	public List<Map> getOutputs() {
		return outputs;
	}

	public void setOutputs(List<Map> outputs) {
		this.outputs = outputs;
	}

	public List<Map> getInputs() {
		return inputs;
	}

	public void setInputs(List<Map> inputs) {
		this.inputs = inputs;
	}

	public String getAssetname() {
		return assetname;
	}

	public void setAssetname(String assetname) {
		this.assetname = assetname;
	}

	public String getTxTypeDesc() {
		return txTypeDesc;
	}

	public void setTxTypeDesc(String txTypeDesc) {
		this.txTypeDesc = txTypeDesc;
	}

	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}

	public String getCaKey() {
		return caKey;
	}

	public void setCaKey(String caKey) {
		this.caKey = caKey;
	}

	public String getTxId() {
		return txId;
	}
	public void setTxId(String txId) {
		this.txId = txId;
	}
	public Integer getTxType() {
		return txType;
	}
	public void setTxType(Integer txType) {
		this.txType = txType;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	
}
