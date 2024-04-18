<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "java.util.*" %>
<%@ page import = "vo.*" %>
<%@ page import = "dao.*" %>

<%
	ArrayList<Dept> deptList = DeptDAO.selectDeptList();
	ArrayList<Emp> empList = EmpDAO.selectEmpList();
	ArrayList<HashMap<String, Object>> deptOnOffList 
								= DeptDAO.selectDeptOnOffList();
	ArrayList<HashMap<String, Object>> empAndDeptList 
								= EmpDAO.selectEmpAndDeptList();

%>   
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<h1>Dept List</h1>
<table>
	<tr border="1">
		<td>deptNo</td>
		<td>dName</td>
		<td>loc</td>
	</tr>
	
	<%
		for(Dept d : deptList){
	%>		
	
	<tr>
		<td><%=d.deptNo %></td>
		<td><%=d.dname%></td>
		<td><%=d.loc%></td>
	</tr>	
			
			
	<%	
		}
	%>	

</table>

<h1>Emp List</h1>
<table>
	<tr border="1">
		<td>empNo</td>
		<td>eName</td>
		<td>sal</td>
	</tr>
	
	<%
		for(Emp e : empList){
	%>		
	
	<tr>
		<td><%=e.empNo%></td>
		<td><%=e.ename%></td>
		<td><%=e.sal%></td>
	</tr>	
			
			
	<%	
		}
	%>	

</table>


<h1>Dept + onoffList</h1>
<table>
	<tr border="1">
		<td>deptNo</td>
		<td>dName</td>
		<td>loc</td>
		<td>onoff</td>
	</tr>
	
	<%
		for( HashMap<String,Object> don  : deptOnOffList ){
	%>		
	
	<tr>
		<td><%=(Integer)(don.get("deptNo"))%></td>
		<td><%=(String)(don.get("dName"))%></td>
		<td><%=(String)(don.get("loc"))%></td>
		<td><%=(String)(don.get("onoff"))%></td>
	</tr>	
			
			
	<%	
		}
	%>	

</table>


<h1>Emp INNER JOIN Dept List</h1>
<table>
	<tr border="1">
		<td>empNo</td>
		<td>ename</td>
		<td>deptNo</td>
		<td>dname</td>
	</tr>
	
	<%
		for( HashMap<String,Object> ed : empAndDeptList){
	%>		
	
	<tr>
					<td><%=(Integer)(ed.get("empNo"))%></td>
					<td><%=(String)(ed.get("ename"))%></td>
					<td><%=(Integer)(ed.get("deptNo"))%></td>
					<td><%=(String)(ed.get("dname"))%></td>
				</tr>

			
	<%	
		}
	%>	

</table>











</body>
</html>