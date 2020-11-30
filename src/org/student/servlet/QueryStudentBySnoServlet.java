package org.student.servlet;

import org.student.entity.Student;
import org.student.service.IStudentService;
import org.student.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 通过学号查询单个学生
 */
public class QueryStudentBySnoServlet extends HttpServlet {
    /**
     * 先从前端获取到学号然后调用service的queryStudentBySno方法，
     * 并传入学号，返回一个学生对象，因为已经将Student对象中的toString方法重写
     * 所以student将直接输出这个对象的全部属性，因为要将数据传输到前端，这样就要调用request方法
     * 跳转到studentInfo.jsp,并将student对象传入request域中。
     * @param request 转发(请求)，即客户端发来的请求
     * @param response 重定向(响应)，即服务端做出的响应
     * @throws ServletException servlet异常
     * @throws IOException io流异常
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        int no = Integer.parseInt(request.getParameter("sno"));
        IStudentService studentServiceImpl = new StudentServiceImpl();
        Student student = studentServiceImpl.queryStudentBySno(no);
        System.out.println(student);
        //将此人的数据通过前台jsp显示：studentInfo.jsp
        //如果request域没有数据，使用重定向跳转response，sendRedirect();
        //如果request域有数据（request.setAttribute），
        //使用请求转发(request.getRequestDispatcher)跳转
        request.setAttribute("student", student);
        request.getRequestDispatcher("studentInfo.jsp").forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
