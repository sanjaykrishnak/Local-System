package com.business;


import javax.persistence.EntityManager;

import jms.pojo.JMSReceiver;
import jms.pojo.JMSSender;
import jpa.dao.Department;
import jpa.dao.Employee;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class BusinessFacade 
{
	
	public PlatformTransactionManager transactionManager;
	
	public EntityManager entityManager;
	
	public JMSSender jmsSender;
	
	public JMSReceiver jmsReceiver;
	
 


	
	public JMSSender getJmsSender() {
		return jmsSender;
	}

	public void setJmsSender(JMSSender jmsSender) {
		this.jmsSender = jmsSender;
	}

	public JMSReceiver getJmsReceiver() {
		return jmsReceiver;
	}

	public void setJmsReceiver(JMSReceiver jmsReceiver) {
		this.jmsReceiver = jmsReceiver;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}



	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(PlatformTransactionManager txManager) {
		this.transactionManager = txManager;
	}
	
	public void persistEmployee()
	{
		EntityManager em = getEntityManager();
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = getTransactionManager().getTransaction(def);
		
			Department dept = new Department();
			dept.setDeptno(50);
			dept.setLoc("NY");
			dept.setDname("FBI");
			
			Employee emp1 = new Employee();
			emp1.setName("AAA");
			emp1.setEMPNO(8000);
			emp1.setJob("Hibernte");
			emp1.setDepartment(dept);
			
			Employee emp2 = new Employee();
			emp2.setName("BBB");
			emp2.setEMPNO(8001);
			emp2.setJob("Hibernte");
			emp2.setDepartment(dept);
			
			
			//em.getTransaction().begin();
			
		    em.persist(dept);
		    em.persist(emp1);
		    em.persist(emp2);
		    
		   // em.getTransaction().commit();
		    //getTransactionManager().commit(status);
		    
		    //return true;
	
	    em.close();
	  	    
	    System.out.println("Employee saved");
	    
	    throw new RuntimeException();
	    
   
		
	}
	
	public void sendMessage(String str)
	{
		
		getJmsSender().sendMesage(str);
				
	}
	
	//@Transactional(rollbackFor=Exception.class)
	public boolean transactionRollback()
	{
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = getTransactionManager().getTransaction(def);
		
		TransactionTemplate template  = new TransactionTemplate(this.getTransactionManager()); 
		boolean value = (Boolean) template.execute( new TransactionCallback<Object>(){ 
		  public Object doInTransaction(TransactionStatus status){ 
		   // work done here will be wrapped by a transaction and committed. 
		   // the transaction will be rolled back if 
		   // status.setRollbackOnly(true) is called or an exception is thrown 
			  try
			  {
			  	sendMessage("Global Transactions working");
				persistEmployee();
				
				return true;
			  }
			  catch(Exception e)
			  {
				  status.setRollbackOnly();
				  return false;
			  }
		  } 
		});
		
		return value;
		
		
				
		
	}
	
	
	
	

}
