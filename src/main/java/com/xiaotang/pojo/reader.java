package com.xiaotang.pojo;

public class reader {
    private Integer id;
    private String sno;
    private String password;
    private String name;
    private String phone;
    private Integer status;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "reader{" +
                "id=" + id +
                ", sno='" + sno + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                '}';
    }
}
