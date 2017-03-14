package services;

import java.util.List;

import javax.ejb.Remote;

import entities.Administrator;
import entities.User;

@Remote
public interface UserEJBRemote {
	
	public void addUser(User user);
	public void deleteUser(User user);
	public void updateUser(User user);
	public User findById(int idUser);
	public Administrator authenticate(String login, String password);
	public User findUserByLogin(String login);
	public List<User> getAllUser();
	public List<User> getUserByfirstLastName(String first);

}
