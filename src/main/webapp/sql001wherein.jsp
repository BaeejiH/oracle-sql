<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.net.*"%>
<%@ page import = "java.util.*" %>
<%@ page import = "vo.*" %>
<%@ page import = "dao.*" %>
    
<%
	ArrayList<Emp> list = null;
	String [] ck = request.getParameterValues("ck");	
	if(ck == null){		
		System.out.println(ck+"<----ck");
	}else {
		
		System.out.println(ck.length+"<----ck.length");
		ArrayList<Integer> ckList = new ArrayList<>();
		for(String s : ck){
			ckList.add(Integer.parseInt(s));
		}
		list = EmpDAO.selectEMPListByGrade(ckList);
		System.out.println(list.size() + " 결과셋행(list.size())");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Emp Grade 검색</h1>
	<form action="sql001wherein.jsp" method="post">
		Grade:
		<%
			for (int i=1; i<6; i=i+1) {
		%>
			<input name="ck" type="checkbox" value="<%=i%>"><%=i%>
			
		<%			
			}
		%>
		<button type="submit">검색</button>
	</form>
	
	<h1>결과 view</h1>
	<%
		if(ck == null){
			return;
		}
	%>
	
	<table>
		<tr>
			<th>ename</th>
			<th>grade</th>
		</tr>
		
		<%
			for(Emp e: list ){
		%>
			<td><%=e.getEname() %></td>
			<td><%=e.getGrade() %></td>
		
		<%			
			}
		%>
	</table>
	
	
</body>
</html>