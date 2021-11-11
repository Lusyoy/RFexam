<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'createtest.jsp' starting page</title>
  </head>
  <body>
   	<form method="post" action="${path }/servlet/CreateTestServlet">
   		题目数量:<input type="text" name="num" />
   		科目:
	   	<select name="courseid">
	   		<c:forEach items="${clist }" var="i">
	   			<option value="${i.id }">${i.cname }</option>
	   		</c:forEach>
	   	</select>
	   	班级:
	   	<select name="classid">
	   		<c:forEach items="${classList }" var="i">
	   			<option value="${i.id }">${i.cname }</option>
	   		</c:forEach>
	   	</select>
	   	试卷名称:<input type="text" name="examname" />
	   	<input type="submit" value="提交" />
   	</form>
  </body>
</html>
