package com.onchain.projects.web.param;

/**
 * Created by ZhouQ on 2017/6/6.
 */
public class NodeInfo {

    private Integer totalCount;
    private Integer errorNum;
    private Integer correctNum;
    private String errorPer;
    private String correctPer;
    private Integer type1Num;
    private Integer type2Num;

    public Integer getType1Num() {
        return type1Num;
    }

    public void setType1Num(Integer type1Num) {
        this.type1Num = type1Num;
    }

    public Integer getType2Num() {
        return type2Num;
    }

    public void setType2Num(Integer type2Num) {
        this.type2Num = type2Num;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getErrorNum() {
        return errorNum;
    }

    public void setErrorNum(Integer errorNum) {
        this.errorNum = errorNum;
    }

    public Integer getCorrectNum() {
        return correctNum;
    }

    public void setCorrectNum(Integer correctNum) {
        this.correctNum = correctNum;
    }

    public String getErrorPer() {
        return errorPer;
    }

    public void setErrorPer(String errorPer) {
        this.errorPer = errorPer;
    }

    public String getCorrectPer() {
        return correctPer;
    }

    public void setCorrectPer(String correctPer) {
        this.correctPer = correctPer;
    }
}
