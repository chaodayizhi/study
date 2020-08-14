package com.panda.entity;

/**
 * @Author Panda
 * @create 2020/7/20 19:11
 */
public class User {
    //成员变量
    private Integer id;
    private String name;
    private Integer score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public User(Integer id, String name, Integer score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }
}
