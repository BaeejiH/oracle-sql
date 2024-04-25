package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Emp;

public class EmpDAO {
	//sql001selfjoin.jsp
	//관리자 등급 표시
	//NVL : null일때 다른 값으로 표시
	// ON e1.mgr = e2.empno : e1의 관리자와 e2의 직원번호가 일치하는경우 출력
	public static  ArrayList<HashMap<String,Object>>selectEmpmgr( ) throws Exception {
		  ArrayList<HashMap<String, Object>> list = new ArrayList<>();
		  Connection conn = DBHelper.getConnection();
	
		  String sql = "SELECT e1.empno, e1.ename, e1.grade, NVL(e2.ename, '관리자없음') \"mgrName\", NVL(e2.grade, 0) \"mgrGrade\" "
		  		+ "FROM emp e1 LEFT OUTER JOIN emp e2 "
		  		+ "ON e1.mgr = e2.empno "
		  		+ "ORDER BY e1.empno ASC";
		  System.out.println("SQL: " + sql);	
		  		  
		  PreparedStatement stmt = conn.prepareStatement(sql);
		  ResultSet rs = stmt.executeQuery();
		    
		
		    while (rs.next()) {
		        HashMap<String, Object> map = new HashMap<>();
		        map.put("empno", rs.getInt("empno"));
		        map.put("ename", rs.getString("ename"));
		        map.put("grade", rs.getInt("grade"));
		        map.put("mgrName", rs.getString("mgrName"));
		        map.put("mgrGrade", rs.getInt("mgrGrade"));
		  
		        list.add(map);
						 	
		   }
		    conn.close();
			return list;		  
	}	    
	
	
	
	
	//타입 : ArrayList
	public static  ArrayList<HashMap<String,Integer>>selectEmpSalState( ) throws Exception {
		  ArrayList<HashMap<String, Integer>> list = new ArrayList<>();
		  Connection conn = DBHelper.getConnection();
		String sql  = "SELECT"
				+ " grade"
				+ ", COUNT(*) count"
				+ ", SUM(sal) sum"
				+ ", AVG(sal) avg"
				+ ", MAX(sal) max"
				+ ", MIN(sal) min"
				+ " FROM emp"
				+ " GROUP BY grade"
				+ " ORDER BY grade ASC";
					
		 PreparedStatement stmt = conn.prepareStatement(sql);
	    ResultSet rs = stmt.executeQuery();
	    
	    // 쿼리 결과를 처리하여 Caselist에 추가
	    while (rs.next()) {
	        HashMap<String, Integer> map = new HashMap<>();
	        map.put("grade", rs.getInt("grade"));
	        map.put("count", rs.getInt("count"));
	        map.put("sum", rs.getInt("sum"));
	        map.put("avg", rs.getInt("avg"));
	        map.put("max", rs.getInt("max"));
	        map.put("min", rs.getInt("min"));
	        list.add(map);
				
		conn.close();
		
		
	}
		return list;
	    
}
	
	
	//sql001orderby.jsp
	public static ArrayList<Emp>selectEmpListSort(String col,String sort)throws Exception{
		//매개값 디버깅
		System.out.println(col+"<---EmpDAO.selectEmpListSort param col");
		System.out.println(sort+"<---EmpDAO.selectEmpListSort param Sort");
		
		ArrayList<Emp> list = new ArrayList<>();
		Connection conn = DBHelper.getConnection();
		
		String sql= "select empno,ename"
				+ " from emp";
		
		
		if(col !=null && sort != null) {
			sql= sql + "order by "+col+" "+sort;
		}
		 
		PreparedStatement stmt = conn.prepareStatement(sql);
		System.out.println(stmt);
		
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Emp e = new Emp();
			e.setEmpNo(rs.getInt("empno"));
			e.setEname(rs.getString("ename"));
			list.add(e);
		}
		 	 
		 conn.close();
		 return list;
	}
	
	
	public static ArrayList<Emp> selectEMPListByGrade
			(ArrayList<Integer>ckList) throws Exception{
		ArrayList<Emp> list = new ArrayList<>();
		  Connection conn = DBHelper.getConnection();
		  String sql = "select ename,grade"
		  		+ " from emp "
		  		+ "where grade in ";
				
				  	
		  PreparedStatement stmt = null;
		  if(ckList.size() == 1) {
			  sql = sql +"(?)";
			  stmt = conn.prepareStatement(sql);
			  stmt.setInt(1, ckList.get(0));
		  }else if(ckList.size() == 2) {
			  sql = sql +"(?,?)";
			  stmt = conn.prepareStatement(sql);
			  stmt.setInt(1, ckList.get(0));
			  stmt.setInt(2, ckList.get(1));
		  }else if(ckList.size() == 3) {
			  sql = sql +"(?,?,?)";
			  stmt = conn.prepareStatement(sql);
			  stmt.setInt(1, ckList.get(0));
			  stmt.setInt(2, ckList.get(1));
			  stmt.setInt(3, ckList.get(2));
		  }else if(ckList.size() == 4) {
			  sql = sql +"(?,?,?,?";
			  stmt = conn.prepareStatement(sql);
			  stmt.setInt(1, ckList.get(0));
			  stmt.setInt(2, ckList.get(1));
			  stmt.setInt(3, ckList.get(2));
			  stmt.setInt(4, ckList.get(3));
		  }else if(ckList.size() == 5) {
			  sql = sql +"(?,?,?,?,?)";
			  stmt = conn.prepareStatement(sql);
			  stmt.setInt(1, ckList.get(0));
			  stmt.setInt(2, ckList.get(1));
			  stmt.setInt(3, ckList.get(2));
			  stmt.setInt(4, ckList.get(3));
			  stmt.setInt(5, ckList.get(4));
			  
				  
		  }  
		  ResultSet rs = stmt.executeQuery();
			  while(rs.next()) {
				  Emp e = new Emp();
				  e.setEname(rs.getString("ename"));
				  e.setGrade(rs.getInt("grade"));
				  list.add(e);
			  }
		conn.close();  	
		return list;
	}

	
	public static ArrayList<HashMap<String,String>> selectJOBCASEtList() throws Exception {
	    ArrayList<HashMap<String, String>> Caselist = new ArrayList<>();
	    
	    Connection conn = DBHelper.getConnection();
	    
	    String sql = "SELECT ename,\r\n"
	    		+ "       CASE\r\n"
	    		+ "           WHEN job = 'PRESIDENT' THEN '빨강'\r\n"
	    		+ "           WHEN job = 'MANAGER' THEN '주황'\r\n"
	    		+ "           WHEN job = 'ANALYST' THEN '노랑'\r\n"
	    		+ "           WHEN job = 'CLERK' THEN '초록'\r\n"
	    		+ "           ELSE '파랑'\r\n"
	    		+ "       END AS color\r\n"
	    		+ "FROM emp\r\n"
	    		+ "ORDER BY (CASE \r\n"
	    		+ "              WHEN job = 'PRESIDENT' THEN 1\r\n"
	    		+ "              WHEN job = 'MANAGER' THEN 2\r\n"
	    		+ "              WHEN job = 'ANALYST' THEN 3\r\n"
	    		+ "              WHEN job = 'CLERK' THEN 4\r\n"
	    		+ "              ELSE 5\r\n"
	    		+ "          END) ASC";

	    PreparedStatement stmt = conn.prepareStatement(sql);
	    ResultSet rs = stmt.executeQuery();
	    
	    // 쿼리 결과를 처리하여 Caselist에 추가
	    while (rs.next()) {
	        HashMap<String, String> map = new HashMap<>();
	        map.put("ename", rs.getString("ename"));
	        map.put("color", rs.getString("color"));
	        Caselist.add(map);
	    }
	    
	    // 연결을 닫음
	    conn.close();
	    
	    return Caselist;
		}
	
	


	

	//DEPTNO 뒤에 부서별 인원 같이 조회하는 메서드
	public static ArrayList<HashMap<String, Integer>> selectDeptNoCntList() throws Exception {
	    ArrayList<HashMap<String, Integer>> list = new ArrayList<>();
	    
	    Connection conn = DBHelper.getConnection();
	    
	    String sql = "SELECT deptno deptNo, COUNT(*) cnt" 
	            + " FROM emp"
	            + " WHERE deptno IS NOT NULL"
	            + " GROUP BY deptno"
	            + " ORDER BY deptno ASC";

	    PreparedStatement stmt = conn.prepareStatement(sql);
	    ResultSet rs = stmt.executeQuery();
	    
	    while (rs.next()) {
	        HashMap<String, Integer> m = new HashMap<>();
	        m.put("cnt", rs.getInt("cnt"));
	        m.put("deptNo", rs.getInt("deptNo")); 
	        list.add(m);
	    }
	    
	    return list;
	}

	
	
	
	
	//DEPTNO 목록을 출력하는 메서드
	public static ArrayList<Integer> selectDeptnoList() throws Exception{
		ArrayList<Integer> list = new ArrayList<>();
		
		Connection conn = DBHelper.getConnection();
		
		String sql = "select distinct deptno deptNo from emp where deptno is not null order by deptno asc";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Integer i = rs.getInt("deptNo"); //  랩퍼타입과 기본타입 간에 Auto Boxing
			list.add(i);
		}
		
		
		
		conn.close();
		
		return list;
				
	}
	
	
	
	//조인으로 Map을 사용하는 경우
	public static ArrayList<HashMap<String,Object>> selectEmpAndDeptList()
	throws Exception{
		ArrayList<HashMap<String,Object>> list = new ArrayList<>();
		
		Connection conn = DBHelper.getConnection();
		String sql = "select emp.empno empNo, emp.ename ename, emp.deptno deptNo,dept.dname dname"
				+ " From emp "
				+ "INNER JOIN dept "
				+ "ON emp.deptno = dept.deptno";
			
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<>();
			m.put("empNo", rs.getInt("empNo"));
			m.put("ename", rs.getString("ename"));
			m.put("deptNo", rs.getInt("deptNo"));
			m.put("dname", rs.getString("dname"));
			list.add(m);
		}
		return list;
	}

	
		
			
	// VO 사용
		public static ArrayList<Emp> selectEmpList() throws Exception {
			ArrayList<Emp> list = new ArrayList<>();
			
			Connection conn = DBHelper.getConnection();
			String sql = "SELECT"
					+ " empno empNo, ename, sal"
					+ " FROM emp";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Emp e = new Emp();
				e.setEmpNo(rs.getInt("empNo"));
				e.setEname(rs.getString("ename"));
				e.setSal(rs.getDouble("sal"));
				list.add(e);
			}
			
			return list;
		}
	}

