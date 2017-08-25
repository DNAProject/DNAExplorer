package com.onchain.projects.domain;

/**
 * Created by ZhouQ on 2017/6/12.
 */
public class Result {
    private Integer state;
    private Integer n;
    private String value;
    private String txid;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }
}
