package com.deptempspringjdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.deptempspringjdbc.model.Department;
import com.deptempspringjdbc.model.Employee;
public class DeptEmpDaoImpl implements DeptEmpDao{

	private JdbcTemplate jTemplate;
	
	
	public DeptEmpDaoImpl(JdbcTemplate jTemplate) {
		super();
		this.jTemplate = jTemplate;
	}

	public DeptEmpDaoImpl() {
		super();
	}

	public JdbcTemplate getjTemplate() {
		return jTemplate;
	}

	public void setjTemplate(JdbcTemplate jTemplate) {
		this.jTemplate = jTemplate;
	}

	public boolean createDept(Department dept) {
		String query = "insert into department values(?,?,?)";
		jTemplate.update(query, dept.getDeptId(),dept.getDeptName(),dept.getDeptLoc());
		return false;
	}

	public boolean updateDept(Department dept) {
		
		String query = "update department set dept_name=?,dept_loc=? where dept_id=?";
		System.out.println("de[tid"+dept.getDeptId());
		jTemplate.update(query, dept.getDeptName(),dept.getDeptLoc(),dept.getDeptId());
		
		
		return false;
	}

	public List<Department> readAllDept() {
 String query = "select * from department";
 List<Department> dept =jTemplate.query(query, new ResultSetExtractor<List<Department>>(){

	public List<Department> extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		List<Department> dert = new ArrayList<>();
		while(rs.next())
		{
			Department dpt = new Department(rs.getInt(1),rs.getString(2),rs.getString(3));
			dert.add(dpt);
		}
		
		
		
		return dert;
	}
	 
	 
 
 });
		return dept;
	}

	public boolean delteDept(int deptId) {
		String query = "delete from department where dept_id = ?";
		jTemplate.update(query, deptId);
		return false;
	}

	public boolean createEmp(Employee employee) {

		String query = "insert into employee values (?,?,?,?,?,?,?,?)";
		jTemplate.update(query, employee.getEmpId(),employee.getEmpName(),employee.getDateOfBirth(),employee.getMailId(),employee.getDeptEmpId(),employee.getMobileNo(),employee.getSalary(),employee.getCompanyName());
		
		return false;
	}

	public boolean updateEmp(Employee employee) {
		String query = "update employee set emp_name=?,mail_id=?,dob=?,mobile_no=?,salary=?,company_name=?,dept_emp_fk=? where emp_id=?";
		
		jTemplate.update(query, employee.getEmpName(),employee.getMailId(),employee.getDateOfBirth(),employee.getMobileNo(),employee.getSalary(),employee.getCompanyName(),employee.getDeptEmpId(),employee.getEmpId());
		
		return false;
	}

	public List<Employee> readEmpFromDept(int deptId) {
	
		String query ="select * from employee where dept_emp_fk=?";
		
		List<Employee> lisemp = jTemplate.execute(query, new PreparedStatementCallback<List<Employee>>() {
    List<Employee> empLis = new ArrayList<>();
			@Override
			public List<Employee> doInPreparedStatement(PreparedStatement ps)
					throws SQLException, DataAccessException {
				ps.setInt(1, deptId);
				ResultSet rsd = ps.executeQuery();
				while(rsd.next())
				{
					Employee empl = new Employee(rsd.getInt(1),rsd.getString(2),rsd.getString(3),rsd.getString(4),rsd.getInt(5),rsd.getLong(6),rsd.getFloat(7),rsd.getString(8));
				empLis.add(empl);
				
				}
				return empLis;
			}
		});
		return lisemp;
	}

	public boolean deleteEmpFromDept(int deptId, int empId) {
		String query ="delete from employee where dept_emp_fk=? and emp_id=?";
		jTemplate.update(query, deptId,empId);
		return false;
	}

	
	
	

}
