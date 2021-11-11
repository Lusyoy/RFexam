<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
session.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>My JSP 'login.jsp' starting page</title>
</head>
<body>
   <form action="${path}/servlet/LoginServlet" method="post">
      学号<input type="text" name="id"><br>
       <input type="submit" value="登录">      
   </form>
</body>
</html>