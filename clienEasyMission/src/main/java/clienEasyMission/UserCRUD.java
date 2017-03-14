package clienEasyMission;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.User;
import services.UserEJBRemote;

public class UserCRUD {
		public static void main(String[] args) throws NamingException{
			InitialContext ctx = new InitialContext();
			UserEJBRemote proxy = (UserEJBRemote) ctx.lookup("/easyMission-ear/easyMission-ejb/"
					+ "UserEJB!services.UserEJBRemote");
			
			
			List<User> users = proxy.getAllUser();
			for (User user : users) {
				System.out.println(user);
			}
			
			
			
//			Message m1 = proxy.findMessageByID(1);
//			m1.setContent("cont");
//			proxy.updateMessage(m1);
//			proxy.deleteMessage(proxy.findMessageByID(6));
//			proxy.deleteMessage(m);
			
		}

	}



