package com.xiaotang.pojo;

public class Book {
    private Integer id;
    private String bname;
    private String author;
    private String press;
    private String type;
    private String status;
    private String introduction;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "book{" +
                "id=" + id +
                ", bname='" + bname + '\'' +
                ", author='" + author + '\'' +
                ", press='" + press + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                '}';
    }


}
