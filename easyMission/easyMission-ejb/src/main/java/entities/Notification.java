package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;



/**
 * Entity implementation class for Entity: Notification
 *
 */
@Entity

public class Notification implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idNotification;
	private String content;
	private Date sendDate;
	private Boolean seen;
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private User user;

	
	public Notification() {
		super();
	}   
	public int getIdNotification() {
		return this.idNotification;
	}

	public void setIdNotification(int idNotification) {
		this.idNotification = idNotification;
	}   
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}   
	public Date getSendDate() {
		return this.sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	public Boolean getSeen() {
		return seen;
	}
	public void setSeen(Boolean seen) {
		this.seen = seen;
	}
	
	
}
