package com.ujiuye.servlet;

import com.ujiuye.bean.Student;
import com.ujiuye.service.StuService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/allStu")
public class AllStuServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        resp.setContentType("text/html;charset=utf8");

        List<Student> list = new StuService().getStuList();
        System.out.println(list);
        req.setAttribute("list", list);
        req.getRequestDispatcher("show.jsp").forward(req, resp);
    }
}
