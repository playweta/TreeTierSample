<%@ page import="org.student.entity.Student" %><%--
  Created by IntelliJ IDEA.
  User: Scrap image
  Date: 2020/11/27
  Time: 19:29
  To change this template use File | Settings | File Templates.

  该页面是从QueryStudentBySnoServlet请求转发过来的，数据用request域传
  过来的页面跳转用request的getRequestDispatcher方法跳转过来的
  用于显示和修改个人数据的页面

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人信息页面</title>
</head>
<body>
        <%
            //将request域中的数据取出来给student对象
            Student student = (Student) request.getAttribute("student");

        %>
        <!--通过表单展示此人信息-->
        <form action="UpdateStudentServlet">                               <!--该属性用于禁止修改-->
            学号：<input type="text" name="sno" value="<%=student.getSno()%>" readonly="readonly"/><br/>
            姓名：<input type="text" name="sname" value="<%=student.getSname()%>"/><br/>
            年龄：<input type="text" name="sage" value="<%=student.getSage()%>"/><br/>
            地址：<input type="text" name="saddress" value="<%=student.getSaddress()%>"/><br/>
            <input type="submit" value="修改">
            <a href="QueryStudentByPage">返回</a>

        </form>

</body>
</html>
