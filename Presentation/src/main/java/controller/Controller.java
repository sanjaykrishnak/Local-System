package controller;


import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.business.BusinessFacade;



public class Controller extends HttpServlet {
	
	//ApplicationContext context;

	public void init(ServletConfig config) throws ServletException {
	    super.init(config);
	    
//	     ServletContext servletContext = this.getServletContext();
	    //this.context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);

	}

	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
	{
		System.out.println("Controller:doGet() called");
		
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
	throws IOException
	{
		System.out.println("Controller:doPost() called");
		String type = req.getParameter("type");
		ApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext.xml");

		
		if(type.equals("send"))
		{
			try
			{
				
				
				BusinessFacade facade = (BusinessFacade) context.getBean("busFacade");
				
		
				res.getWriter().print(facade.transactionRollback());
			}
			catch(Exception e){e.printStackTrace();}
		}
		else if(type.equals("jpa-save"))
		{
			try
			{
			    
			      
				BusinessFacade facade = (BusinessFacade) context.getBean("busFacade");
				
				facade.persistEmployee();
				
				
			    
			     /* dao.HibernateClientSessionBeanRemoteHome hm = (dao.HibernateClientSessionBeanRemoteHome) ctx.lookup("ejb.HibernateClientSessionBeanRemoteHome");
			      
			      dao.HibernateClientSessionBeanRemote hcbr = hm.create();
			      boolean b = hcbr.saveEmp();*/

				
				//res.getWriter().print(save);
			}
			catch(Exception e){e.printStackTrace();}
		}
		else if(type.equals("jms-send"))
		{
			try
			{
			    
			      
				BusinessFacade facade = (BusinessFacade) context.getBean("busFacade");
				
				facade.sendMessage("God is great");
				
				
			    
			     /* dao.HibernateClientSessionBeanRemoteHome hm = (dao.HibernateClientSessionBeanRemoteHome) ctx.lookup("ejb.HibernateClientSessionBeanRemoteHome");
			      
			      dao.HibernateClientSessionBeanRemote hcbr = hm.create();
			      boolean b = hcbr.saveEmp();*/

				
				//res.getWriter().print(save);
			}
			catch(Exception e){e.printStackTrace();}
		}
		
	}

	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
		System.out.println("destory called");

	}

}
