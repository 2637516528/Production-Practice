package com.xiaotang.pojo;

public class feedback {
    private Integer id;
    private String sno;
    private String desc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getDesc() { return desc; }

    public void setDesc(String desc) {
        this.desc = desc;
    }



    @Override
    public String toString() {
        return "feedback{" +
                "id=" + id +
                ", sno='" + sno + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
