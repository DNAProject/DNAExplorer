package com.onchain.projects.domain;

/**
 * Created by ZhouQ on 2017/7/5.
 */
public class TransactionInput {
    public String txid;
    public String inputid;
    public String prevhash;
    public String previndex;

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public String getInputid() {
        return inputid;
    }

    public void setInputid(String inputid) {
        this.inputid = inputid;
    }

    public String getPrevhash() {
        return prevhash;
    }

    public void setPrevhash(String prevhash) {
        this.prevhash = prevhash;
    }

    public String getPrevindex() {
        return previndex;
    }

    public void setPrevindex(String previndex) {
        this.previndex = previndex;
    }
}
