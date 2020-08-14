package com.panda.entity;

/**
 * @Author Panda
 * @create 2020/7/22 17:45
 */
public class BookCase {
    private Integer id;
    private String name;

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

    public BookCase(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
