package services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Comment;
import entities.Topic;
import entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



/**
 * Session Bean implementation class TopicEJB
 */
@Stateful
@LocalBean
public class TopicEJB implements TopicEJBRemote, TopicEJBLocal {
	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
    public TopicEJB() {
        // TODO Auto-generated constructor stub
    }
    @Override
    public void AddTopic(Topic topic) {
		em.persist(topic);
	}
    @Override
    public void UpdateTopic(Topic topic) {
		
		em.merge(topic);
	}
    @Override
    public void DeleteTopic(Topic topic) {
		em.remove(em.merge(topic));	
	}
    @Override
    public Topic FindTopicById(Integer idTopic) {

		return em.find(Topic.class, idTopic);
	}
	@Override
	public List<Topic> FindAllTopics() {
		// TODO Auto-generated method stub
		return em.createQuery("SELECT t FROM Topic t ", Topic.class).getResultList();
	}
	@Override
	public List<Topic> advancedsearchTopic(String title) {
		List<Topic> list = new ArrayList<Topic>();
		 return list=em.createQuery("SELECT t FROM Topic t WHERE t.title LIKE  '%' || :title || '%' ", Topic.class).setParameter("title",title).getResultList(); 
		 
		 }
	
	@Override
	public Long TopicCommentsNumber(int id) {
		Long a ;
		 return a =  (Long) em.createQuery("SELECT COUNT(p) FROM Topic t JOIN t.comments p WHERE t.idTopic=?1").setParameter(1,id).getSingleResult(); 
		 
		 }
	@Override
	public List<Topic> topicGroupByComment() {
		return em.createNativeQuery(
		        "SELECT t.title,COUNT(c.topic_idTopic) FROM topic t LEFT JOIN comment c ON t.idTopic=c.topic_idTopic GROUP BY t.title; ")
		        .getResultList();
		 
		 }
	@Override
	public List<Topic> sortTopicsByCommentsNumberDesc() {
		// TODO Auto-generated method stub
		return em.createQuery("SELECT t FROM Topic t ORDER BY t.commentsNumber DESC", Topic.class).getResultList();
	}
	@Override
	public List<User> userOrderByCommentsNumber() {
		return  em.createQuery(
		        "SELECT u FROM Topic t JOIN t.user u ORDER BY (t.commentsNumber)DESC  ")
		        .getResultList();
		 
		 }
	@Override
	public List<Topic> sortTopicByCategory(String category) {
	  
		return em.createQuery("SELECT t FROM Topic t WHERE t.category= ?1 ", Topic.class).setParameter(1,category).getResultList(); 

	}
	@Override
	public List<Topic> sortTopicsByCommentsNumberAsc() {
		return em.createQuery("SELECT t FROM Topic t ORDER BY t.commentsNumber ASC", Topic.class).getResultList();

	}
	@Override
	public Double moyCommPerForum() {
		return (Double) em.createQuery("SELECT AVG(t.commentsNumber) FROM Topic t ").getSingleResult();

	}
	
	
	
    
}
