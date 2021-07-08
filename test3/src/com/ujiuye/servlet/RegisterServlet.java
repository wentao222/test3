package com.ujiuye.servlet;

import com.ujiuye.bean.Student;
import com.ujiuye.service.StuService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/reg")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        resp.setContentType("text/html;charset=utf8");

        String sname = req.getParameter("sname");
        String gender = req.getParameter("gender");
        String[] hobby = req.getParameterValues("hobby");
        String degree = req.getParameter("degree");
        String mark = req.getParameter("mark");

        boolean b = new StuService().register(new Student(0, sname, Integer.parseInt(gender), Arrays.toString(hobby), degree, mark));

        if(b) {
            resp.sendRedirect("index.jsp");
        }
    }
}
