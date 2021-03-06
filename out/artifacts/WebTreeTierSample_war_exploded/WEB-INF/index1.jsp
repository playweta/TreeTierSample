<%@ page import="java.util.List" %>
<%@ page import="org.student.entity.Student" %><%--
  Created by IntelliJ IDEA.
  User: Scrap image
  Date: 2020/11/26
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>

    <script type="text/javascript">
        $(document).ready(function(){
            $("tr:odd").css("background-color","lightgray");
        });

    </script>
    <title>学生信息列表</title>
</head>
<body>




<!--接收AddStudentServlet中request域传来的增加成功失败的信息-->
<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
        if (error.equals("addError")) {
            //添加失败
            out.print("增加失败");
        } else if (error.equals("addNoError")) {
            //添加成功
            out.print("增加成功");
        } else if (error.equals("updateNoError")) {
            out.print("修改成功");
        } else if (error.equals("updateError")) {
            out.print("修改失败");
        } else if (error.equals("deleteNoError")) {
            out.print("删除成功");
        } else if (error.equals("deleteError")){
            out.print("删除失败");
        }
    }
%>

<form action="/QueryAllStudentsServlet" method="post">
    <table border="1px">
        <tr>
            <th>学号</th>
            <th>姓名</th>
            <th>年龄</th>
            <%--            <th>地址</th>--%>
            <th>删除</th>
        </tr>
        <%
            //获取QueryAllStudentsServlet中request域中的数据
            List<Student> students = (List<Student>) request.getAttribute("page");
            for (Student student : students) {
        %>
        <tr>
            <td><a href="QueryStudentBySnoServlet?sno=<%=student.getSno()%>">
                <%=student.getSno()%>
            </a></td>
            <td><%=student.getSname()%>
            </td>
            <td><%=student.getSage()%>
            </td>
            <%--                <td><%=student.getSaddress()%></td>--%>
            <td><a href="DeleteStudentServlet?sno=<%=student.getSno()%>">删除</a></td>
        </tr>

        <%
            }
        %>
    </table>
    <a href="add.jsp">增加</a><br/>
    <a href="QueryStudentByPage？currentPage=1">首页</a>
    <a href="QueryStudentByPage？currentPage=1">首页</a>
    <a href="QueryStudentByPage？currentPage=1">首页</a>
    <a href="QueryStudentByPage？currentPage=1">首页</a>
</form>
</body>
</html>
