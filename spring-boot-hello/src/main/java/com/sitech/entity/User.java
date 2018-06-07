package com.sitech.entity;

public class User {

    public User() {
    }

    public User(String name, int age) {

        this.name = name;
        this.age = age;
    }

    public User(Long id) {
        this.id = id;
    }

    public User(String name, int age, Long id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    private String name;

    private int age;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}