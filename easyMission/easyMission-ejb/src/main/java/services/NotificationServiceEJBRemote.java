package services;

import java.util.List;

import javax.ejb.Remote;

import entities.Notification;
import entities.User;

@Remote
public interface NotificationServiceEJBRemote {
	public void addNotification(Notification n);
	public void updateNotification(Notification n);
	public Notification findNotificationByID(int idNotification);
	public void deleteNotification(Notification n);
	public void deleteAllNotification();
	public List<Notification> getAllNotificationByUser(User user);
	public List<Notification> getAllNotificationByUserBySeen(User user);
	public Long countNotifNotSeen(User user);
	
	
	
}
