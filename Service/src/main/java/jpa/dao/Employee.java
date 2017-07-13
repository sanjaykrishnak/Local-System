package jpa.dao;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="EMP")
public class Employee implements Serializable
{
	
    @Id
    //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="emp_seq")
    //@SequenceGenerator(name="emp_seq", sequenceName="emp_seq", allocationSize=1)
    @Column(name="EMPNO")
	private Integer EMPNO;
	
    @Column(name="ENAME")
    private String name;
	
    @Column(name="JOB")
    private String job;
    
    @Column(name="MGR")
	private Integer managerNO;
	
	@Column(name="SAL")
	private Integer salary;
	
	@Column(name="COMM")
	private Integer commission;
	
	//@ManyToOne
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="DEPTNO")
    private Department department;
	
	public Integer getCommission() {
		return commission;
	}
	public void setCommission(Integer commission) {
		this.commission = commission;
	}

	public Integer getEMPNO() {
		return EMPNO;
	}
	public void setEMPNO(Integer empno) {
		EMPNO = empno;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Integer getManagerNO() {
		return managerNO;
	}
	public void setManagerNO(Integer managerNO) {
		this.managerNO = managerNO;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}

}
