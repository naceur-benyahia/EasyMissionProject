package services;

import java.util.List;

import javax.ejb.Local;

import entities.Administrator;
import entities.User;

@Local
public interface UserEJBLocal {
	
	public void addUser(User user);
	public void deleteUser(User user);
	public void updateUser(User user);
	public List<User> getAllUser();
	public User findById(int idUser);
	public Administrator authenticate(String login, String password);
	public User findUserByLogin(String login);

}
