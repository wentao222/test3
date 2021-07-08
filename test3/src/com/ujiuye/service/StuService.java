package com.ujiuye.service;

import com.ujiuye.bean.Student;
import com.ujiuye.dao.StuDao;

import java.util.List;

public class StuService {
    StuDao sd = new StuDao();
    public boolean register(Student student) {
        return sd.register(student);

    }

    public List<Student> getStuList() {
        return sd.getStuList();
    }
}
