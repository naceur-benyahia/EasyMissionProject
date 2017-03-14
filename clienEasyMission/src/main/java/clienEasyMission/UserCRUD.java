package clienEasyMission;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Administrator;
import entities.User;
import services.UserEJBRemote;

public class UserCRUD {
		public static void main(String[] args) throws NamingException{
			InitialContext ctx = new InitialContext();
			UserEJBRemote proxy = (UserEJBRemote) ctx.lookup("/easyMission-ear/easyMission-ejb/"
					+ "UserEJB!services.UserEJBRemote");
			
			User u = new Administrator();
			u.setEmail("email");
			u.setFirstName("firstName");
			u.setLastName("lastName");
			u.setLogin("login");
			u.setPassword("password");
			u.setPhoneNumber(123456);
			proxy.addUser(u);
			//proxy.deleteUser(proxy.findById(1));
//			List<User> users = proxy.getAllUser();
//			for (User user : users) {
//				System.out.println(user);
//			}
			
			
			
//			Message m1 = proxy.findMessageByID(1);
//			m1.setContent("cont");
//			proxy.updateMessage(m1);
//			proxy.deleteMessage(proxy.findMessageByID(6));
//			proxy.deleteMessage(m);
			
		}

	}



