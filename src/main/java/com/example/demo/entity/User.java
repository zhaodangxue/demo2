package com.example.demo.entity;

import java.util.Objects;

public class User {
    private String stamp;
    private String name;
    private String introduction;
    private String school;
    private String password;

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String stamp, String name, String introduction, String school, String password) {
        this.stamp = stamp;
        this.name = name;
        this.introduction = introduction;
        this.school = school;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(stamp, user.stamp) && Objects.equals(name, user.name) && Objects.equals(introduction, user.introduction) && Objects.equals(school, user.school) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stamp, name, introduction, school, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "stamp='" + stamp + '\'' +
                ", name='" + name + '\'' +
                ", introduction='" + introduction + '\'' +
                ", school='" + school + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
