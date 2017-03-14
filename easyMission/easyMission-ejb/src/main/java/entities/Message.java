package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;



/**
 * Entity implementation class for Entity: Message
 *
 */
@Entity

public class Message implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idMessage;
	private String content;
	private Date date;
	private String type;
	private static final long serialVersionUID = 1L;
	@ManyToOne
	private User sender;
	@ManyToOne
	private User receiver;

	public Message() {
		super();
	}   
	public int getIdMessage() {
		return this.idMessage;
	}

	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}   
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}   
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}   
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public User getReceiver() {
		return receiver;
	}
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	
   
}
