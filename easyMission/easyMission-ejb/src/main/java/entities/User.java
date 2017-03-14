package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;



/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type")
@DiscriminatorValue(value="user")
public class User implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	protected int idUser;
	protected String firstName;
	protected  String lastName;
	protected  String email;
	protected  String login;
	protected  String password;
	protected  int phoneNumber;
	protected  String photo;
	protected  Date registrationDate;
	protected  static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy="sender")
	private List<Message> listMessagessended;
	@OneToMany(mappedBy="receiver")
	private List<Message> listMessagesReceived;
	
	@OneToMany(mappedBy="user")
	private List<Notification> listNotifications;
	


	public User() {
		super();
	}   
	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
   
}
