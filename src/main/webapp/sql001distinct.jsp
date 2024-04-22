<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "dao.*" %>
<%@ page import = "java.util.*" %>
    
 <%
	ArrayList<Integer> list = EmpDAO. selectDeptnoList();
%>
 
    
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

<select name="deptno">
		<option value="">--선택--</option>
	<%
		for (Integer i: list) {	
	%>	
			<option value="<%=i%>"><%=i%></option>
	<% 
		}
	%>
	
</select>











</body>
</html>