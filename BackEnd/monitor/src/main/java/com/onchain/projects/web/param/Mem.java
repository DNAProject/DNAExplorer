package com.onchain.projects.web.param;

/**
 * Created by ZhouQ on 2017/6/1.
 */
public class Mem {

    private String Total;

    private String Free;

    private String Used;

    private String UsedPercent;

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public String getFree() {
        return Free;
    }

    public void setFree(String free) {
        Free = free;
    }

    public String getUsed() {
        return Used;
    }

    public void setUsed(String used) {
        Used = used;
    }

    public String getUsedPercent() {
        return UsedPercent;
    }

    public void setUsedPercent(String usedPercent) {
        UsedPercent = usedPercent;
    }
}
