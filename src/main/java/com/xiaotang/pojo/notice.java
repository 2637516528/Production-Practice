package com.xiaotang.pojo;

public class notice {
    private Integer id;
    private String noticedesc;
    private String posttime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getNoticedesc() {
        return noticedesc;
    }

    public void setNoticedesc(String noticedesc) {
        this.noticedesc = noticedesc;
    }

    public String getPosttime() {
        return posttime;
    }

    public void setPosttime(String posttime) {
        this.posttime = posttime;
    }

    @Override
    public String toString() {
        return "notice{" +
                "id=" + id +
                ", noticedesc='" + noticedesc + '\'' +
                ", posttime='" + posttime + '\'' +
                '}';
    }
}
