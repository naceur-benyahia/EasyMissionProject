package clienEasyMission;



import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Message;
import entities.Notification;
import entities.User;
import services.NotificationServiceEJBRemote;
import services.UserEJBRemote;

public class NotificationCRUD {
	public static void main(String[] args) throws NamingException{
		InitialContext ctx = new InitialContext();
		NotificationServiceEJBRemote proxy = (NotificationServiceEJBRemote) ctx.lookup("easyMission-ear/easyMission-ejb/"
				+ "NotificationServiceEJB!services.NotificationServiceEJBRemote");
		UserEJBRemote proxy1 = (UserEJBRemote) ctx.lookup("/easyMission-ear/easyMission-ejb/"
					+ "UserEJB!services.UserEJBRemote");
//		Notification n = new Notification();
//		Date d = new Date();
//		n.setContent("content");
//		n.setSendDate(d);
//		proxy.addNotification(n);
//		Message m1 = proxy.findMessageByID(1);
//		m1.setContent("cont");
//		proxy.updateMessage(m1);
//		proxy.deleteMessage(proxy.findNotificationByID(1));
//		proxy.deleteMessage(m);
		
		User u = proxy1.findById(2);
		
		List<Notification> notifications = proxy.getAllNotificationByUserBySeen(u);
		for (Notification message : notifications) {
			System.out.println(message.getContent());
		}
		
//		proxy.deleteAllNotification();
		
		
		


	}
}
