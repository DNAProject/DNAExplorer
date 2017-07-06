package com.onchain.projects.domain;

/**
 * Created by ZhouQ on 2017/6/1.
 */
public class Machine {

    private String cpuUsedPer;

    private String memUsedPer;

    private String disUsedPer;

    private String date;

    private String node_ip;

    public String getCpuUsedPer() {
        return cpuUsedPer;
    }

    public void setCpuUsedPer(String cpuUsedPer) {
        this.cpuUsedPer = cpuUsedPer;
    }

    public String getMemUsedPer() {
        return memUsedPer;
    }

    public void setMemUsedPer(String memUsedPer) {
        this.memUsedPer = memUsedPer;
    }

    public String getDisUsedPer() {
        return disUsedPer;
    }

    public void setDisUsedPer(String disUsedPer) {
        this.disUsedPer = disUsedPer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNode_ip() {
        return node_ip;
    }

    public void setNode_ip(String node_ip) {
        this.node_ip = node_ip;
    }
}
