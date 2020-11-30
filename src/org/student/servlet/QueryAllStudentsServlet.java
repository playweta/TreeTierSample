package org.student.servlet;

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
 * 查询全部学生的Servlet
 */
public class QueryAllStudentsServlet extends HttpServlet {
    /**
     * 查询所有不需要传数据，只需要接收数据因此，先实例化
     * 一个service对象再调用queryAllStudent方法并获取
     * 返回值(一个List集合)，然后用request的setAttribute方法把
     * 返回的数组对象传入request域中并调用request的
     * getRequestDispatcher方法将页面跳转到index.jsp
     *
     * @param request 转发(请求)，即客户端发来的请求
     * @param response 重定向(响应)，即服务端做出的响应
     * @throws ServletException servlet异常
     * @throws IOException io流异常
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        IStudentService studentService = new StudentServiceImpl();
        List<Student> students = studentService.queryAllStudent();
        System.out.println(students);

        request.setAttribute("students", students);
        //因为request域中有数据，因此需要通过请求转发的方式跳转（重定向会丢失request域）
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
