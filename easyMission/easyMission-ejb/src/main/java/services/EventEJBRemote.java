package services;

import java.util.List;

import javax.ejb.Remote;

import entities.Candidate;
import entities.Events;

@Remote
public interface EventEJBRemote {
	void AddEvent(Events event);
	void UpdateEvent(Events event);
	void DeleteEvent(Events event);
	Events FindEventById(Integer id);
	 int EtatEvent(Candidate C,Events event);
	 void Participer(Events event, Candidate C);
	 List<Events> showall();
}
