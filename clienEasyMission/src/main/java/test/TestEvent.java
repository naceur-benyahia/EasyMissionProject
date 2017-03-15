

package test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Events;

import services.EventEJBRemote;

public class TestEvent {

	public static void main(String[] args) throws NamingException {
		InitialContext ctx = new InitialContext();
		EventEJBRemote proxy = (EventEJBRemote) ctx
				.lookup("/easyMission-ear/easyMission-ejb/EventEJB!services.EventEJBRemote");

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		// Events ev3 =new Events();
		//
		// ev3.setTitre("evenement4");
		// ev3.setAdresse("monastir");
		// try {
		// ev3.setDatedebut(format.parse("01/04/2017"));
		// } catch (ParseException e) {
		//
		// e.printStackTrace();
		// }
		// try {
		// ev3.setDatefin(format.parse("04/04/2017"));
		// } catch (ParseException e) {
		//
		// e.printStackTrace();
		// }
		// ev3.setDuree(3);
		// proxy.AddEvent(ev3);
		// System.out.println("ev4");
		// //System.out.println(proxy.FindEventById(2));
		//proxy.DeleteEvent(proxy.FindEventById(7));
		//List<Events> list =proxy.FindEventByAdresse("tunis");
		//for (Events events : list) {
			//System.out.println(events.getAdresse());
				//proxy.EtatEvent(event);
		}
		// System.out.println(e.getIdEvent()+ e.getAdresse() + e.getDuree()+"");
	}


