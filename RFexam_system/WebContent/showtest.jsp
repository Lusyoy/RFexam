<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'showtest.jsp' starting page</title>
  </head>
  <body>
   <table border="1px">
   	<Tr>
   		<Td>编号</Td>
   		<Td>科目</Td>
   		<Td>题目</Td>
   		<Td>A选项</Td>
   		<Td>B选项</Td>
   		<Td>C选项</Td>
   		<Td>D选项</Td>
   		<Td>正确答案</Td>
   	</Tr>
   	<c:forEach items="${elist }" var="i">
   		<Tr>
   		<Td>${i.id }</Td>
   		<Td>${i.courseid==1?"百科":"数学"}</Td>
   		<Td>${i.question }</Td>
   		<Td>${i.ansA} </Td>
   		<Td>${i.ansB}</Td>
   		<Td>${i.ansC}</Td>
   		<Td>${i.ansD}</Td>
   		<Td>${i.ans}</Td>
   		</Tr>
   	</c:forEach>
   </table>
   <a href="${path }/index.jsp">返回首页</a>
  </body>
</html>
