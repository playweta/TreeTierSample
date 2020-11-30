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
 * 根据学号，修改学生的Servlet
 */
public class UpdateStudentServlet extends HttpServlet {
    /**
     * 获取前端传来的数据(sno）和（sname，sage，saddress)，然后将后三条数据传给student对象
     * 而后调用servlet的updateStudentBySno方法将sno和student对象传入，返回一个boolean类型的对象
     * 来判断是否修改成功
     * @param request 转发(请求)，即客户端发来的请求
     * @param response 重定向(响应)，即服务端做出的响应
     * @throws ServletException servlet异常
     * @throws IOException io流异常
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");

        //getParameter这个对象是获取name属性当中的值
        int no = Integer.parseInt(request.getParameter("sno"));
        //获取修改后的内容
        String name = request.getParameter("sname");
        int age = Integer.parseInt(request.getParameter("sage"));
        String address = request.getParameter("saddress");
        //将修改后的内容，封装到一个实体类中
        Student student = new Student(name, age, address);
        IStudentService studentServiceImpl = new StudentServiceImpl();
        boolean result = studentServiceImpl.updateStudentBySno(no, student);
        if (result) {
            // response.getWriter().println("修改成功！");
            //因为修改完成后直接调用查询所有学生(QueryAllStudentServlet),使用不需要传输数据，那么我们直接使用重定向response
            request.setAttribute("error", "updateNoError");
        } else {
            // response.getWriter().println("修改失败！");
            request.setAttribute("error","updateError");
        }

        request.getRequestDispatcher("QueryStudentByPage").forward(request, response); //这个servlet最后也是转发到查询所有的前端表单上(index.jsp)
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
