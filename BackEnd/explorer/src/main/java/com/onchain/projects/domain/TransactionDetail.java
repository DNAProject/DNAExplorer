package com.onchain.projects.domain;

/**
 * Created by ZhouQ on 2017/7/12.
 */
public class TransactionDetail {

    private String txid;

    private int txtype;

    private String scripthash;

    private String txtime;

    private String txflag;

    private int height;

    private long amount;

    private long fee;

    private String assertId;

    private String assertname;

    private int txindex;

    private String prevhash;

    private String previndex;

    private String tohash;

    private String cakey;

    private int spendflag;

    private String counterparty;

    public String getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(String counterparty) {
        this.counterparty = counterparty;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public int getTxtype() {
        return txtype;
    }

    public void setTxtype(int txtype) {
        this.txtype = txtype;
    }

    public String getScripthash() {
        return scripthash;
    }

    public void setScripthash(String scripthash) {
        this.scripthash = scripthash;
    }

    public String getTxtime() {
        return txtime;
    }

    public void setTxtime(String txtime) {
        this.txtime = txtime;
    }

    public String getTxflag() {
        return txflag;
    }

    public void setTxflag(String txflag) {
        this.txflag = txflag;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getFee() {
        return fee;
    }

    public void setFee(long fee) {
        this.fee = fee;
    }

    public String getAssertId() {
        return assertId;
    }

    public void setAssertId(String assertId) {
        this.assertId = assertId;
    }

    public String getAssertname() {
        return assertname;
    }

    public void setAssertname(String assertname) {
        this.assertname = assertname;
    }

    public int getTxindex() {
        return txindex;
    }

    public void setTxindex(int txindex) {
        this.txindex = txindex;
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

    public String getTohash() {
        return tohash;
    }

    public void setTohash(String tohash) {
        this.tohash = tohash;
    }

    public String getCakey() {
        return cakey;
    }

    public void setCakey(String cakey) {
        this.cakey = cakey;
    }

    public int getSpendflag() {
        return spendflag;
    }

    public void setSpendflag(int spendflag) {
        this.spendflag = spendflag;
    }
}
