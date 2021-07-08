package com.ujiuye.bean;

public class Student {
    private Integer sid;
    private String name;
    private Integer gender;
    private String hobby;
    private String degree;
    private String mark;

    public Student(Integer sid, String name, Integer gender, String hobby, String degree, String mark) {
        this.sid = sid;
        this.name = name;
        this.gender = gender;
        this.hobby = hobby;
        this.degree = degree;
        this.mark = mark;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", hobby='" + hobby + '\'' +
                ", degree='" + degree + '\'' +
                ", mark='" + mark + '\'' +
                '}';
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
