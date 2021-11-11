<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'addtest.jsp' starting page</title>
  </head>
  <body>
   <form action="${path }/servlet/AddTestServlet" method="post">
   	科目:
   	<select name="courseid">
   		<c:forEach items="${clist }" var="i">
   			<option value="${i.id }">${i.cname }</option>
   		</c:forEach>
   	</select><br/>
   	问题:<input type="text" name="question"	 /><br/>
   	A选项:<input type="text" name="ansA"	 /><br/>
   	B选项:<input type="text" name="ansB"	 /><br/>
   	C选项:<input type="text" name="ansC"	 /><br/>
   	D选项:<input type="text" name="ansD"	 /><br/>
   	正确答案:<input type="text" name="ans"	 /><br/>
   	<input type="submit" value="添加试题" />
   </form>
   	
  </body>
</html>
