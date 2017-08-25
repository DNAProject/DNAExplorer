package com.onchain.projects.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ZhouQ on 2017/7/12.
 */
public class ViewTransaction {

    private int height;

    private String txId;

    private int txType;

    private String date;

    private String caKey;
    //资产名称
    private String assetname;
    //类型描述
    private String txTypeDesc;
    //交易输出列表
    private List<Map> outputs = new ArrayList<>();
    //交易输入列表
    private List<Map> inputs = new ArrayList<>();

    private String assetid;

    private String amount;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAssetid() {
        return assetid;
    }

    public void setAssetid(String assetid) {
        this.assetid = assetid;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public int getTxType() {
        return txType;
    }

    public void setTxType(int txType) {
        this.txType = txType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCaKey() {
        return caKey;
    }

    public void setCaKey(String caKey) {
        this.caKey = caKey;
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

    @Override
    public String toString() {
        return "ViewTransaction{" +
                "height=" + height +
                ", txId='" + txId + '\'' +
                ", txType=" + txType +
                ", date='" + date + '\'' +
                ", caKey='" + caKey + '\'' +
                ", assetname='" + assetname + '\'' +
                ", txTypeDesc='" + txTypeDesc + '\'' +
                ", outputs=" + outputs +
                ", inputs=" + inputs +
                '}';
    }
}
