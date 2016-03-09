package com.tek.simTrade.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.stereotype.Service;

@Service
public class AppService
{
	@Autowired
	private Environment env;



	@SuppressWarnings("rawtypes")
	public String getPropertyFileAndProperties()
	{
		StringBuilder builder = new StringBuilder();
		Map<String, Object> map = new HashMap<String, Object>();
		for (Iterator it = ((AbstractEnvironment) env).getPropertySources()
				.iterator(); it.hasNext();)
		{
			PropertySource propertySource = (PropertySource) it.next();
			if (propertySource instanceof ResourcePropertySource)
			{
				String propertySourceName = propertySource.getName();
				builder.append(propertySourceName);
				builder.append(" <br/>");
				map.putAll(((MapPropertySource) propertySource).getSource());
				builder.append(
						((MapPropertySource) propertySource).getSource());
				builder.append(" <br/>");
			}
		}

		return builder.toString();
	}
	public void sendmail(String tomail,String subject,String text)
	{
		String to=tomail;//change accordingly  
		  
		  //Get the session object  
		  Properties props = new Properties();  
		  props.put("mail.smtp.host", "smtp.gmail.com");  
		  props.put("mail.smtp.socketFactory.port", "465");  
		  props.put("mail.smtp.socketFactory.class",  
		            "javax.net.ssl.SSLSocketFactory");  
		  props.put("mail.smtp.auth", "true");  
		  props.put("mail.smtp.port", "465");  
		   
		  Session session = Session.getDefaultInstance(props,  
		   new javax.mail.Authenticator() {  
		   protected PasswordAuthentication getPasswordAuthentication() {  
		   return new PasswordAuthentication("yourEmailID@gmail.com","your Password");//change accordingly  
		   }  
		  });  
		   
		  //compose message  
		  try {  
		   MimeMessage message = new MimeMessage(session);  
		   message.setFrom(new InternetAddress("yourEmailId@gmail.com"));//change accordingly  
		   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
		   message.setSubject(subject);  
		   message.setText(text);  
		     
		   //send message  
		   Transport.send(message);  
		  
	
		   
		  } catch (MessagingException e) {throw new RuntimeException(e);}  
		   
	}
}
