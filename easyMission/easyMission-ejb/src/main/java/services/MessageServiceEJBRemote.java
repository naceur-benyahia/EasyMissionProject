package services;

import java.util.List;

import javax.ejb.Remote;

import entities.Message;
import entities.User;

@Remote
public interface MessageServiceEJBRemote {
	public void addMessage(Message m);
	public void updateMessage(Message m);
	public Message findMessageByID(int idMessage);
	public void deleteMessage(Message m);
	public List<Message> findAllMessages();
	public List<Message> findMessagesSendedByUser(User user);
	public List<Message> findMessagesReceivedByUser(User user);
	List<Message> advancedsearchMessage(String login,User user);
	List<Message> advancedsearchMessageContent(String login,User user);
	
	

}
