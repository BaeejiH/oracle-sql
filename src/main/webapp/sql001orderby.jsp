<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.net.*"%>
<%@ page import = "java.util.*" %>
<%@ page import = "vo.*" %>
<%@ page import = "dao.*" %> 	


<!-- 컨트롤러 -->
<%
	// 어떤 컬럼으로 정령
	String col = request.getParameter("col");
	System.out.println(col+"<---col");
	//오름/내
	String sort = request.getParameter("sort");
	System.out.println(sort+"<---sort");
	
	//DAO 호출-> 모델 반환
	ArrayList<Emp>list = EmpDAO.selectEmpListSort(col, sort); 
	System.out.println(list+"<---sql001orederby.jsp list.size()");
%>	





<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>
				empno
				<a href="./sql001orderby.jsp?col=empno&sort=asc">오름</a>
				<a href="./sql001orderby.jsp?col=empno&sort=desc">내림</a>
			</th>
			<th>
				ename
				<a href="./sql001orderby.jsp?col=ename&sort=asc">오름</a>
				<a href="./sql001orderby.jsp?col=ename&sort=desc">내림</a>
			</th>
		</tr>
		<%
			for(Emp e : list){
		%>
			<tr>
					<td><%=e.getEmpNo()%></td>
					<td><%=e.getEname()%></td>	
			</tr>
		
		
		
		<%	
			}
		%>
	</table>

</body>
</html>