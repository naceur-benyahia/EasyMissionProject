package services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Message;
import entities.Notification;
import entities.User;

/**
 * Session Bean implementation class NotificationServiceEJB
 */
@Stateful
@LocalBean
public class NotificationServiceEJB implements NotificationServiceEJBRemote {

	@PersistenceContext
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public NotificationServiceEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addNotification(Notification n) {
		em.persist(n);
		
	}

	@Override
	public void updateNotification(Notification n) {
		em.merge(n);
	}

	@Override
	public Notification findNotificationByID(int idNotification) {
		return em.find(Notification.class, idNotification );
		
	}

	@Override
	public void deleteNotification(Notification n) {
		em.remove(em.merge(n));
		
	}

	@Override
	public List<Notification> getAllNotificationByUser(User user) {
			if(em.createQuery("select n from Notification n where n.user=?1",Notification.class)
					.setParameter(1, user).getResultList()==null){
				 return new ArrayList<Notification>();
			 }
			else 
				return em.createQuery("select n from Notification n where n.user=?1",Notification.class)
						.setParameter(1, user).getResultList();
			
		}

	@Override
	public void deleteAllNotification() {
		em.createQuery("delete from Notification n").executeUpdate();
		
	}

	@Override
	public List<Notification> getAllNotificationByUserBySeen(User user) {
		if(em.createQuery("select n from Notification n where n.user=?1 and n.seen=?2",Notification.class)
				.setParameter(1, user).setParameter(2, false).getResultList()==null){
			 return new ArrayList<Notification>();
		 }
		else 
			return em.createQuery("select n from Notification n where n.user=?1 and n.seen=?2",Notification.class)
					.setParameter(1, user).setParameter(2, false).getResultList();
	
	}

	@Override
	public Long countNotifNotSeen(User user) {
		return (Long) em.createQuery("select count(n) from Notification n where n.seen=?1 and n.user=?2")
		.setParameter(1, false).setParameter(2, user)
		.getSingleResult();
		 
	}

	
}


