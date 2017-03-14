package services;

import java.util.List;

import javax.ejb.Remote;

import entities.Comment;
import entities.Topic;

@Remote
public interface CommentEJBRemote {
	void AddComment(Comment comment);
	void UpdateComment(Comment comment);
	void DeleteComment(Comment comment);
	void DeleteCommentByTopic(Topic topic);
	Comment FindCommentById(Integer id);
	List<Comment> FindCommentByTopic(Topic topic );
	List<Comment> FindAllComments();
	List<Comment> sortCommentByCategory(String category);
}
