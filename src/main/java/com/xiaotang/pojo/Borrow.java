package com.xiaotang.pojo;

public class Borrow {
    private Integer num;
    private String sno;
    private Integer bid;
    private String bname;
    private String begintime;
    private String endtime;
    private String status;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "borrow{" +
                "num=" + num +
                ", sno='" + sno + '\'' +
                ", bid=" + bid +
                ", bname='" + bname + '\'' +
                ", begintime='" + begintime + '\'' +
                ", endtime='" + endtime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
