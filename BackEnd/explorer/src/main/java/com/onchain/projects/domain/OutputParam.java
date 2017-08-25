package com.onchain.projects.domain;

import java.util.List;

/**
 * Created by ZhouQ on 2017/6/12.
 */
public class OutputParam {
    private String assetid;
    private String balance;
    private List<Result> list;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssetid() {
        return assetid;
    }

    public void setAssetid(String assetid) {
        this.assetid = assetid;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public List<Result> getList() {
        return list;
    }

    public void setList(List<Result> list) {
        this.list = list;
    }
}
