package com.onchain.projects.domain;

/**
 * Created by DELL on 2017/4/25.
 */
public class Summary {

    //区块高度
    Integer height;
    //存证数量
    Integer txncount;

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getTxncount() {
        return txncount;
    }

    public void setTxncount(Integer txncount) {
        this.txncount = txncount;
    }
}
