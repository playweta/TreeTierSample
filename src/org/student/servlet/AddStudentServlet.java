package org.student.servlet;

import org.student.entity.Student;
import org.student.service.IStudentService;
import org.student.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 添加学生的Servlet
 *
 *  * out request response session application
 *  * out: response.getWriter();
 *  * session: request.getSession()
 *  * application: request.getServletContext()
 */
public class AddStudentServlet extends HttpServlet {

    /**
     * 用request将前端用户输入的内容(用name属性获取)获取，
     * 然后将数据传入Student对象中，然后将对象传给service层的
     * addStudent方法并返回一个布尔值，判断是否添加成功
     * @param request 转发(请求)，即客户端发来的请求
     * @param response 重定向(响应)，即服务端做出的响应
     * @throws ServletException servlet异常
     * @throws IOException io流异常
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(request.getParameter("sno"));
        String name = request.getParameter("sname");
        int age = Integer.parseInt(request.getParameter("sage"));
        String address = request.getParameter("saddress");
        Student student = new Student(id,name,age,address);

        //多态：接口 x = new 实现类();
        IStudentService studentService = new StudentServiceImpl();
        boolean result = studentService.addStudent(student);

        PrintWriter out = response.getWriter();
        /*if (result) {
            // out.print("增加成功");
            //增加成功无需传输数据所有我们所有重定向response
            //response.sendRedirect("QueryAllStudentServlet");
        } else {
            out.print("增加失败");

        }*/

        /*
            这个判断执行的结果将转发(request)到request域中，
            数据会随页面一起跑到QueryAllStudentServlet中，并
            再一次转发到前端页面(index.jsp)中去
         */
        if(!result) {
            //增加失败
            request.setAttribute("error","addError");
        } else {
            // 增加成功
            request.setAttribute("error", "addNoError");
        }
        request.getRequestDispatcher("QueryStudentByPage").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
