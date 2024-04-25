<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.net.*"%>
<%@ page import = "java.util.*" %>
<%@ page import = "dao.*" %>    

<%
	ArrayList<HashMap<String, Integer>> list = EmpDAO.selectEmpSalState();
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>등급별 Sal통계</h1>
	<table>
		<tr>
			<td>grade</td>
			<td>count</td>
			<td>sum</td>
			<td>avg</td>
			<td>max</td>
			<td>min</td>
		</tr>
		
		<%
			for(HashMap<String,Integer> m : list) {
		%>
			<tr>
				<td>
						<%
							for(int i=0; i<m.get("grade"); i=i+1) {
						%>
							
						<%
							}
						%>
				</td>							
				<td><%=m.get("count") %></td>
				<td><%=m.get("sum") %></td>
				<td><%=m.get("avg") %></td>
				<td><%=m.get("max") %></td>
				<td><%=m.get("min") %></td>
			</tr>
		<%
			}
		%>
		
		
		
		
		
		
		
	</table>


</body>
</html>