package services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import entities.Message;
import entities.User;



/**
 * Session Bean implementation class MessageServiceEJB
 */
@Stateful
@LocalBean
public class MessageServiceEJB implements MessageServiceEJBRemote {
	
	@PersistenceContext
	EntityManager em;
    
    /**
     * Default constructor. 
     */
    public MessageServiceEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addMessage(Message m) {
		em.persist(m);		
	}

	@Override
	public void updateMessage(Message m) {
		em.merge(m);
		
	}

	@Override
	public Message findMessageByID(int idMessage) {
		return em.find(Message.class, idMessage );
		
	}

	@Override
	public void deleteMessage(Message m) {
		em.remove(em.merge(m));
	}

	@Override
	public List<Message> findAllMessages() {
		return em.createQuery("select m from Message m",Message.class).getResultList();
		}

	@Override
	public List<Message> findMessagesSendedByUser(User user) {
		if(em.createQuery("select m from Message m where m.sender=?1",Message.class)
				.setParameter(1, user).getResultList()==null){
			 return new ArrayList<Message>();
		 }
		else 
			return em.createQuery("select m from Message m where m.sender=?1",Message.class)
					.setParameter(1, user).getResultList();
		
	}
	@Override
	public List<Message> findMessagesReceivedByUser(User user) {
		
		 if(em.createQuery("select m from Message m where m.receiver=?1",Message.class)
				.setParameter(1, user).getResultList()==null){
			 return new ArrayList<Message>();
		 }
		else 
			return em.createQuery("select m from Message m where m.receiver=?1",Message.class)
				.setParameter(1, user).getResultList();
	}

	@Override
	public List<Message> advancedsearchMessage(String login, User user) {
			 return em.createQuery("SELECT t FROM Message t WHERE t.receiver=?1 and t.sender.login LIKE  '%' || :login || '%' ", Message.class).setParameter("login",login)
					 .setParameter(1, user).getResultList(); 
	}

	@Override
	public List<Message> advancedsearchMessageContent(String content, User user) {
		 return em.createQuery("SELECT t FROM Message t WHERE t.sender=?1 and t.content LIKE  '%' || :content || '%' ", Message.class)
				 .setParameter("content",content)
				 .setParameter(1, user).getResultList(); 

	}

	
	

}
