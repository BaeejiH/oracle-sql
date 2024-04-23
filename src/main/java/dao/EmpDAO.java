package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Emp;

public class EmpDAO {
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

