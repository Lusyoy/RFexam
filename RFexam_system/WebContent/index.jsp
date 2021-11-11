<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
session.setAttribute("path", path);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'index.jsp' starting page</title>
	
  </head>
  
  <body>
   <div style="border:1px red solid;width:300px;height:300px">
   		<a href="${path }/servlet/ShowTestServlet">显示题库</a>
   		<a href="${path }/servlet/GetCourseServlet">出题</a>
   		<a href="${path }/servlet/GetCourseAndClasstbServlet">组卷</a>
   </div>
  </body>
</html>
