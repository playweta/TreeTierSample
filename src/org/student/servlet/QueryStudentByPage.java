package org.student.servlet;

import org.student.entity.Page;
import org.student.entity.Student;
import org.student.service.IStudentService;
import org.student.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 */
public class QueryStudentByPage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");

        IStudentService studentService = new StudentServiceImpl();

        //将4条数据数据封装入Page

        //总数据条数
        int totalCount = studentService.getTotalCount();

        /*
        * 当前页（页码）
        判断当前页是否为空，如果为空，则给当前赋值为1，
        如果不为空就说明不是第一次访问，则直接传给service
        */
        String cPage = request.getParameter("currentPage");
        if (cPage == null) {
            cPage="1";
        }
        int currentPage = Integer.parseInt(cPage);

        int pageSize = 3;
        String size = request.getParameter("pageSize");
        if (request.getParameter("pageSize")!=null) {
            //当前页面大小
            pageSize = Integer.parseInt(size);
        }
        //int pageSize=3;

        //当前页的数据（学生）集合
        List<Student> students = studentService.queryStudentsByPage(currentPage, pageSize);

        Page page = new Page(
                currentPage,
                pageSize,
                students,
                totalCount
        );
        request.setAttribute("page", page);
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
