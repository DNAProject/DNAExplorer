package com.onchain.projects.web.param;

/**
 * Created by ZhouQ on 2017/6/1.
 */
public class OutputInfo {
    private String qid;

    private String method;

    private String errorcode;

    private String errorstring;

    private String result;

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    public String getErrorstring() {
        return errorstring;
    }

    public void setErrorstring(String errorstring) {
        this.errorstring = errorstring;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
