package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: OnLineTest
 *
 */
@Entity

public class OnLineTest implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idTest;
	private String title;
	@ManyToOne
	private JobOffer testJobOffer;
	
	private static final long serialVersionUID = 1L;

	public OnLineTest() {
		super();
	}
	
	public OnLineTest(String title, JobOffer testJobOffer) {
		super();
		this.title = title;
		this.testJobOffer = testJobOffer;
	}

	public OnLineTest(int idTest, String title, JobOffer testJobOffer) {
		super();
		this.idTest = idTest;
		this.title = title;
		this.testJobOffer = testJobOffer;
	}

	public int getIdTest() {
		return idTest;
	}

	public void setIdTest(int idTest) {
		this.idTest = idTest;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public JobOffer getTestJobOffer() {
		return testJobOffer;
	}

	public void setTestJobOffer(JobOffer testJobOffer) {
		this.testJobOffer = testJobOffer;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
   
}
