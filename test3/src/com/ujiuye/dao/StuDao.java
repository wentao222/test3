package com.ujiuye.dao;

import com.ujiuye.bean.Student;

import java.util.List;

public class StuDao {
    public boolean register(Student student) {

        int row = new BaseDao<>().update("insert into student values(?,?,?,?,?,?)",
                0, student.getName(), student.getGender(), student.getHobby(), student.getDegree(), student.getMark());
        return row > 0 ? true : false;
    }

    public List<Student> getStuList() {
        List<Student> list =  new BaseDao<Student>().getBeanList("select * from student", Student.class);
        return list;
    }
}
