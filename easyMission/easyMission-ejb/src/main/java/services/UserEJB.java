package services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Administrator;
import entities.Message;
import entities.User;

/**
 * Session Bean implementation class UserEJB
 */
@Stateless
@LocalBean
public class UserEJB implements UserEJBRemote, UserEJBLocal {

	@PersistenceContext
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public UserEJB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addUser(User user) {
		em.persist(user);

	}

	@Override
	public User findById(int idUser) {

		return em.find(User.class, idUser);
	}

	@Override
	public void deleteUser(User user) {
		em.remove(em.merge(user));

	}

	@Override
	public void updateUser(User user) {
		em.merge(user);

	}

	@Override
	public List<User> getAllUser() {
		return em.createQuery("select u from User u", User.class).getResultList();

	}

	@Override
	public Administrator authenticate(String login, String password) {
		Administrator found = null;
		found = em.createQuery("select p from Administrator p where p.login=?1", Administrator.class)
				.setParameter(1, login).getSingleResult();

		try {

			if (found.getPassword().equals(password)) {
				System.out.println(found.getFirstName());
			}

		} catch (Exception ex) {
			Logger.getLogger(UserEJB.class.getName()).log(Level.WARNING,
					"authentication attempt failure with login=" + login + "password=" + password);
		}
		return found;

	}

	@Override
	public User findUserByLogin(String login) {
		return em.createQuery("select u from User u where u.login=?1", User.class).setParameter(1, login)
				.getSingleResult();

	}

	@Override
	public List<User> getUserByfirstLastName(String first) {
			 return em.createQuery("SELECT t FROM User t WHERE t.firstName || t.lastName LIKE  '%' || :first || '%' ", User.class)
					 .setParameter("first",first)
					 .getResultList(); 
	}
}


