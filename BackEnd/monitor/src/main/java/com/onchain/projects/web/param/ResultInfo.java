package com.onchain.projects.web.param;

/**
 * Created by ZhouQ on 2017/6/1.
 */
public class ResultInfo {

    private String Cpu;

    private String Mem;

    private String Dis;

    private String Net;

    private String Host;

    private String Proc;

    public String getCpu() {
        return Cpu;
    }

    public void setCpu(String cpu) {
        Cpu = cpu;
    }

    public String getMem() {
        return Mem;
    }

    public void setMem(String mem) {
        Mem = mem;
    }

    public String getDis() {
        return Dis;
    }

    public void setDis(String dis) {
        Dis = dis;
    }

    public String getNet() {
        return Net;
    }

    public void setNet(String net) {
        Net = net;
    }

    public String getHost() {
        return Host;
    }

    public void setHost(String host) {
        Host = host;
    }

    public String getProc() {
        return Proc;
    }

    public void setProc(String proc) {
        Proc = proc;
    }
}
