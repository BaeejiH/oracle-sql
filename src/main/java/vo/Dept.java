package vo;

//Vo(vlaue Object) > DTO(Data Transfer Object) , Domain(속성 값의 범위 , 테이블 속성의 범위)

public class Dept {
	
	private int deptNo;
	private String dname;
	private String loc;
	
	public int getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	
}
