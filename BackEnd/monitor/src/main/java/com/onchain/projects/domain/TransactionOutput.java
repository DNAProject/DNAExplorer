package com.onchain.projects.domain;

/**
 * Created by ZhouQ on 2017/6/12.
 */
public class TransactionOutput {

    private String txId;
    private Integer outputId;
    private String assetId;
    private String value;
    private String scriptHash;
    private Integer spendFlag;
    private String assetName;

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public Integer getOutputId() {
        return outputId;
    }

    public void setOutputId(Integer outputId) {
        this.outputId = outputId;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getScriptHash() {
        return scriptHash;
    }

    public void setScriptHash(String scriptHash) {
        this.scriptHash = scriptHash;
    }

    public Integer getSpendFlag() {
        return spendFlag;
    }

    public void setSpendFlag(Integer spendFlag) {
        this.spendFlag = spendFlag;
    }

    @Override
    public String toString() {
        return "TransactionOutput{" +
                "txId='" + txId + '\'' +
                ", outputId=" + outputId +
                ", assetId='" + assetId + '\'' +
                ", value='" + value + '\'' +
                ", scriptHash='" + scriptHash + '\'' +
                ", spendFlag=" + spendFlag +
                '}';
    }
}
