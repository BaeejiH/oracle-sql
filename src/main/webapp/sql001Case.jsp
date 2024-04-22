<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "dao.*" %>
<%@ page import = "java.util.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<!-- Model -->
<%
	ArrayList<HashMap<String, String>> Caselist 
		= EmpDAO.selectJOBCASEtList();
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<h1>Case</h1>
	<select name="dept">
		<option value="">--선택--</option>
		<%
			for(HashMap<String, String> m: Caselist) {
		%>
				<option value='<%=m.get("ename")%>'>
					<%=m.get("ename")%>
					<%=m.get("color")%>
				</option>
		<%	
			}
		%>
	</select>

</body>
</html>