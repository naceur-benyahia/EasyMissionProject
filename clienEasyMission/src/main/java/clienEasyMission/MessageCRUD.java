package clienEasyMission;


import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Message;
import services.MessageServiceEJBRemote;

public class MessageCRUD {
	public static void main(String[] args) throws NamingException{
		InitialContext ctx = new InitialContext();
		MessageServiceEJBRemote proxy = (MessageServiceEJBRemote) ctx.lookup("easyMission-ear/easyMission-ejb/"
				+ "MessageServiceEJB!services.MessageServiceEJBRemote");
//		Message m1 = new Message();
//		Date d = new Date();
//		m1.setContent("conten");
//		m1.setDate(d);
//		m1.setType("typ");
//		User user = new User();
//		m1.setUser(user);
//		proxy.addMessage(m1);
//		
		List<Message> messages = proxy.findAllMessages();
		for (Message message : messages) {
			System.out.println(message.getContent());
		}
		proxy.findAllMessages();
		
		
//		Message m1 = proxy.findMessageByID(1);
//		m1.setContent("cont");
//		proxy.updateMessage(m1);
//		proxy.deleteMessage(proxy.findMessageByID(6));
//		proxy.deleteMessage(m);
		
	}

}
