<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'begintest.jsp' starting page</title>
  </head>
  <body>
   <form action="${path }/servlet/StartTestServlet" method="post">
      班级：${classtb.cname }
      试卷:
      <select name="tid">
         <c:forEach items="${tlist }" var="i">
         </c:forEach>     
      </select>
      <input type="submit" value="开始考试"/>
   </form>  
  </body>
</html>
