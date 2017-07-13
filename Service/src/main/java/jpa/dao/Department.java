package jpa.dao;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(name="DEPT")
public class Department implements Serializable
{
	@Id
    //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DEP_SEQ")
    //@SequenceGenerator(name="DEP_SEQ", sequenceName="DEP_SEQ", allocationSize=1)
	private Integer deptno;
	
	@Column(name="DNAME")
	private String dname;
	
	@Column(name="LOC")
	private String loc;
	
	  //@OneToMany(cascade=CascadeType.ALL,  fetch=FetchType.EAGER)
	 // @OneToMany(mappedBy="department")
	 //@JoinColumn(name="DEPTNO")
		
	@OneToMany(mappedBy="department", cascade=CascadeType.ALL,  fetch=FetchType.EAGER)
	  private List<Employee> empList = new ArrayList<Employee>();
	

	public Integer getDeptno() {
		System.out.println(deptno);
		return deptno;
		
	}
	
	public void addEmployee(Employee emp)
	{
		empList.add(emp);
		if(emp.getDepartment() == null)
			emp.setDepartment(this);
		
	}

	public List<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}

	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}

	public String getDname() {
		System.out.println(dname);
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getLoc() {
		System.out.println(loc);
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}


}
