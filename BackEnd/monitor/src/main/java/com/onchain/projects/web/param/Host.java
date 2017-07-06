package com.onchain.projects.web.param;

/**
 * Created by ZhouQ on 2017/6/5.
 */
public class Host {

    private String Hostname;

    private String OS;

    private String Platform;

    public String getHostname() {
        return Hostname;
    }

    public void setHostname(String hostname) {
        Hostname = hostname;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public String getPlatform() {
        return Platform;
    }

    public void setPlatform(String platform) {
        Platform = platform;
    }
}
