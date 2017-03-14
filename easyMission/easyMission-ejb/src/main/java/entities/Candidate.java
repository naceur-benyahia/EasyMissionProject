package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Candidate
 *
 */
@Entity
@DiscriminatorValue(value="candidate")

public class Candidate extends User implements Serializable {

	private String domain;
	private static final long serialVersionUID = 1L;

	public Candidate() {
		super();
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
   
}
