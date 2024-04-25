<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.net.*"%>
<%@ page import = "java.util.*" %>
<%@ page import = "vo.*" %>
<%@ page import = "dao.*" %>    
<%
	ArrayList<HashMap<String, Object>> list = EmpDAO.selectEmpmgr();
%>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>관리자 등급</h1>
	<table border="1">
		<tr>
			<td>직원번호</td>
			<td>직원이름</td>
			<td>등급</td>
			<td>관리자이름</td>
			<td>관리자등급</td>
		</tr>
		
		<%
			for(HashMap<String,Object> m : list) {
		%>
			<tr>									
				<td><%=m.get("empno") %></td>
				<td><%=m.get("ename") %></td>
				<td><%=m.get("grade") %></td>
				<td><%=m.get("mgrName") %></td>
				<td>
						<%
							int grade = (int)m.get("mgrGrade");
							for(int i=0; i<grade; i=i+1) {
						%>
							★
						<%
							}
						%>
				</td>
			</tr>
			
					
		<%
			}
		%>
	</table>







</body>
</html>