package services;

import java.util.List;

import javax.ejb.Remote;

import entities.Comment;
import entities.Topic;
import entities.User;
import javafx.collections.ObservableList;

@Remote
public interface TopicEJBRemote {
	void AddTopic(Topic topic);
	void UpdateTopic(Topic topic);
	void DeleteTopic(Topic topic);
	Topic FindTopicById(Integer id);
	List<Topic> FindAllTopics();
	List<Topic> advancedsearchTopic(String title);
	List<Topic> topicGroupByComment();
	Long TopicCommentsNumber(int id);
	
	List<User> userOrderByCommentsNumber();
	List<Topic> sortTopicByCategory(String category);
	List<Topic> sortTopicsByCommentsNumberDesc();
	List<Topic> sortTopicsByCommentsNumberAsc();
	Double moyCommPerForum();
	
	
}
