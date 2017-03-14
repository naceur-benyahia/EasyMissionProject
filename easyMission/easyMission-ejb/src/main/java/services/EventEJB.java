package services;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Candidate;
import entities.Events;

/**
 * Session Bean implementation class EventEJB
 */
@Stateful
@LocalBean
public class EventEJB implements EventEJBRemote, EventEJBLocal {
	@PersistenceContext
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public EventEJB() {

	}

	@Override
	public void AddEvent(Events event) {

		em.persist(event);
	}

	@Override
	public void UpdateEvent(Events event) {

		em.merge(event);
	}

	@Override
	public void DeleteEvent(Events event) {

		em.remove(em.merge(event));
	}

	@Override
	public Events FindEventById(Integer id) {

		return em.find(Events.class, id);

	}

	@Override
	public int EtatEvent(Candidate C, Events event) {
		Date date = new Date(); // initialiser par default à la date local
		int d = event.getDatefin().compareTo(date);
		// Return value is 0 if both dates are equal.
		// Return value is greater than 0 , if Datefin is after the localdate
		// argument.
		// Return value is less than 0, if Datefin is before the localdate
		// argument.}
		return d;

	}

	@Override
	public void Participer(Events event, Candidate C) {
		int evplus = event.getNbparticipant();
		event.setNbparticipant(evplus + 1);
		em.merge(event);

	}

	@Override
	public List<Events> showall() {
		return em.createQuery("select e from Events e").getResultList();

	}

}
