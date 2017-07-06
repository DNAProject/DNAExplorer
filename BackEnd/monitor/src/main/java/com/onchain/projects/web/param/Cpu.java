package com.onchain.projects.web.param;

/**
 * Created by ZhouQ on 2017/6/1.
 */
public class Cpu {
    //空闲
    private String Idle;
    //使用率
    private String UsedPercent;

    public String getIdle() {
        return Idle;
    }

    public void setIdle(String idle) {
        Idle = idle;
    }

    public String getUsedPercent() {
        return UsedPercent;
    }

    public void setUsedPercent(String usedPercent) {
        UsedPercent = usedPercent;
    }
}
