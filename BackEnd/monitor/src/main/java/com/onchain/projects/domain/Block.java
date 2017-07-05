package com.onchain.projects.domain;

import java.math.BigInteger;

public class Block {
	
	private Integer height;
	private String hash;
	private String prevblock;
	private String nextblock;
	private String merkleroot;
	
	//private Integer timestamp;
	private String timestamp;
	private BigInteger nonce;
	//private String nonce;
	
	private String miner;
	private Integer txnum;
	private Double fee;
	private Integer size;
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getPrevblock() {
		return prevblock;
	}
	public void setPrevblock(String prevblock) {
		this.prevblock = prevblock;
	}
	public String getNextblock() {
		return nextblock;
	}
	public void setNextblock(String nextblock) {
		this.nextblock = nextblock;
	}
	public String getMerkleroot() {
		return merkleroot;
	}
	public void setMerkleroot(String merkleroot) {
		this.merkleroot = merkleroot;
	}
	/*public Integer getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Integer timestamp) {
		this.timestamp = timestamp;
	}*/
	/*public Long getNonce() {
		return nonce;
	}
	public void setNonce(Long nonce) {
		this.nonce = nonce;
	}*/
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public BigInteger getNonce() {
		return nonce;
	}
	public void setNonce(BigInteger nonce) {
		this.nonce = nonce;
	}
	public String getMiner() {
		return miner;
	}
	public void setMiner(String miner) {
		this.miner = miner;
	}
	public Integer getTxnum() {
		return txnum;
	}
	public void setTxnum(Integer txnum) {
		this.txnum = txnum;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
}
