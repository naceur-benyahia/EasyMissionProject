package services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Comment;
import entities.Topic;

/**
 * Session Bean implementation class CommentEJB
 */
@Stateful
@LocalBean
public class CommentEJB implements CommentEJBRemote, CommentEJBLocal {
	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
    public CommentEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void AddComment(Comment comment) {
		// TODO Auto-generated method stub
		em.persist(comment);
	}

	@Override
	public void UpdateComment(Comment comment) {
		// TODO Auto-generated method stub
		em.merge(comment);
	}

	@Override
	public void DeleteComment(Comment comment) {
		// TODO Auto-generated method stub
		em.remove(em.merge(comment));
	}

	@Override
	public Comment FindCommentById(Integer id) {
		// TODO Auto-generated method stub
		return em.find(Comment.class, id);
	}

	@Override
	public List<Comment> FindCommentByTopic(Topic topic) {
		// TODO Auto-generated method stub
		List<Comment> list = new ArrayList<Comment>();
		 return list=em.createQuery("SELECT t FROM Comment t WHERE t.topic = ?1 ", Comment.class).setParameter(1,topic).getResultList(); 
		 
	}
	@Override
	public List<Comment> FindAllComments() {
		// TODO Auto-generated method stub
		List<Comment> list = new ArrayList<Comment>();
		 return list=em.createQuery("SELECT t FROM Comment t ", Comment.class).getResultList(); 
		 
	}
	@Override
	public List<Comment> sortCommentByCategory(String category) {
	 return em.createQuery("SELECT c FROM Comment c  WHERE c.topic.category=?1 ", Comment.class).setParameter(1,category).getResultList(); 
	}

	@Override
	public void DeleteCommentByTopic(Topic topic) {
		em.createQuery("DELETE FROM Comment c WHERE c.topic=?1").setParameter(1,topic).executeUpdate();
		
	}

}
