package jms.pojo;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;


public class JMSSender {
	
	private JmsTemplate jmsTemplate;
	
	

	/**
	 * @return Returns the jmsTemplate102.
	 */
	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}
	/**
	 * @param jmsTemplate102 The jmsTemplate102 to set.
	 */
	public void setJmsTemplate(JmsTemplate jmsTemplate102) {
		this.jmsTemplate = jmsTemplate102;
	}
	
	public void sendMesage(final String message){
		jmsTemplate.send("java:comp/env/jms/MQWriter", new MessageCreator() {
		      public Message createMessage(Session session) throws JMSException {
		        return session.createTextMessage(message);
		      }
		    });

		
	}
}
