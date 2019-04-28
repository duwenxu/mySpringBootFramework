package com.example.SpringBoot.Entity;

public class UserTE {
    private int id;
    private String name;
    private String tech1;
    private String tech2;
    private String tech3;
    private String base;

    public UserTE() {
    }

    public UserTE(String name, String tech1, String tech2, String tech3, String base) {
        this.name = name;
        this.tech1 = tech1;
        this.tech2 = tech2;
        this.tech3 = tech3;
        this.base = base;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTech1() {
        return tech1;
    }

    public void setTech1(String tech1) {
        this.tech1 = tech1;
    }

    public String getTech2() {
        return tech2;
    }

    public void setTech2(String tech2) {
        this.tech2 = tech2;
    }

    public String getTech3() {
        return tech3;
    }

    public void setTech3(String tech3) {
        this.tech3 = tech3;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    @Override
    public String toString() {
        return "UserTE{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tech1='" + tech1 + '\'' +
                ", tech2='" + tech2 + '\'' +
                ", tech3='" + tech3 + '\'' +
                ", base='" + base + '\'' +
                '}';
    }
}
