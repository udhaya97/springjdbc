package com.deptempspringjdbc;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deptempspringjdbc.dao.DeptEmpDao;
import com.deptempspringjdbc.model.Department;
import com.deptempspringjdbc.model.Employee;


public class App 
{
    public static void main( String[] args )
    {
       ApplicationContext appc = new ClassPathXmlApplicationContext("applicationContext.xml");
       
       DeptEmpDao empDao = (DeptEmpDao) appc.getBean("demp");
       
       System.out.println("insert into department");
       
       Department dept = new Department(3,"HR","Chennai");
       Department dept2 = new Department(2,"Technical","palakkad");
      // empDao.createDept(dept2);
       
       System.out.println("update name");
       
      // dept.setDeptName("Admin");
      
       //empDao.updateDept(dept);
       
       /*System.out.println("show employee");
       List<Department> dep = empDao.readAllDept();
       for (Department department : dep) {
		  System.out.println(department.getDeptId()+" "+department.getDeptName()+department.getDeptLoc());
	}
       System.out.println("delete");
       empDao.delteDept(2);
       
       System.out.println("show employee");
      List<Department> depp = empDao.readAllDept();
       for (Department department : depp) {
		  System.out.println(department.getDeptId()+" "+department.getDeptName()+department.getDeptLoc());
	}
    }*/
    System.out.println("create employee");
    
    Employee emp = new Employee(1,"udhay","1997-07-29","udhaya@gmail.com",dept.getDeptId(),9876543215L,45632.26f,"HCL");
    Employee emp1 = new Employee(2,"Soorya","1994-04-29","udhaya@gmail.com",1,9876543215L,45632.26f,"HCL");
    
    Employee emp2 = new Employee(3,"Raj","1997-07-29","udhaya@gmail.com",dept.getDeptId(),9876543215L,45632.26f,"HCL");
    
    Employee emp3 = new Employee(4,"Kishore","1997-07-29","udhaya@gmail.com",1,9876543215L,45632.26f,"HCL");
    
    System.out.println("Employee inserted");
    
   // empDao.createEmp(emp1);
   // empDao.createEmp(emp2);
  //  empDao.createEmp(emp3);
    
    System.out.println("update employee name");
   /* emp.setDateOfBirth("1997-07-29");
    emp.setMailId("udhaya@gmail.com");
    empDao.updateEmp(emp);
    
    */
    
    System.out.println("list emp from dept");
    
    List<Employee> empLis = empDao.readEmpFromDept(dept.getDeptId());
    
    for (Employee employee : empLis) {
		System.out.println("employee name :"+employee.getEmpName());
	}
    System.out.println("delete emp from dept");
    
    empDao.deleteEmpFromDept(dept.getDeptId(), emp2.getEmpId());
    
    
    }
    
   
    
}
