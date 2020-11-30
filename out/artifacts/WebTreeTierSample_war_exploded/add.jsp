<%@ page import="org.student.entity.Page" %><%--
  Created by IntelliJ IDEA.
  User: Scrap image
  Date: 2020/11/26
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>

    <script type="text/javascript">
        function check() {
            var sno = $("#sno").val();
            var sname = $("#sname").val();
            var sage = $("#sage").val();
            var saddress = $("#saddress").val();
            if (!(sno > 11400 && sno < 11500 )) {
                alert("学号有误！必须是11401-11499")
                return false;
            }
            if (!(sname.length >= 2 && sname.length <= 6)) {
                alert("姓名长度有误！必须是2-6位")
                return false;
            }
            if (!(sage > 0 && sage < 150)) {
                alert("年龄大小有误！必须是1-150")
                return false;
            }
            if (!(saddress.length("北京"||"上海"||"江西"||"广东"||"云南"||"四川"))){
                alert("地址有误");
                return true;
            }

            //if(...) return false ;

            return true;
        }


        $(document).ready(function () {
        });

    </script>
    <title>添加学生</title>
</head>
<body>
<form action="AddStudentServlet" method="post" onsubmit="return check()">
    学号：<input type="text" name="sno" id="sno"/><br/>
    姓名：<input type="text" name="sname" id="sname"/><br/>
    年龄：<input type="text" name="sage" id="sage"/><br/>
    地址：<input type="text" name="saddress" id="saddress"/><br/>
    <input type="submit" value="新增"><br/>
    <a href="QueryStudentByPage">返回</a>
</form>
</body>
</html>
