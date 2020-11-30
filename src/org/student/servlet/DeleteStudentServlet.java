package org.student.servlet;

import org.student.service.IStudentService;
import org.student.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 根据学号，删除学生的Servlet
 */
public class DeleteStudentServlet extends HttpServlet {
    /**
     * 因为是根据学号删除学生，所有先从前端获取学号，然后再实例化service对象
     * 并调用里面的deleteStudentBySno方法并将学号传入方法当中，并返回一个
     * 布尔值用于判断是否删除成功，而后再用request转发到QueryAllStudentServlet
     * 他会查询所有学生并将页面转发到index.jsp
     * @param request 转发(请求)，即客户端发来的请求
     * @param response 重定向(响应)，即服务端做出的响应
     * @throws ServletException servlet异常
     * @throws IOException io流异常
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        //接收前端传来的学号
        int no = Integer.parseInt(request.getParameter("sno"));

        IStudentService service = new StudentServiceImpl();
        boolean result = service.deleteStudentBySno(no);
        if (result) {
            // response.getWriter().println("删除成功！");
            request.setAttribute("error", "deleteNoError");
        } else {
            // response.getWriter().println("删除失败！");
            request.setAttribute("error", "deleteError");
        }
        request.getRequestDispatcher("QueryStudentByPage").forward(request, response);//重新查询所有学生
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
