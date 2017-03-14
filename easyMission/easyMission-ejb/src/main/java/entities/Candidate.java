package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Candidate
 *
 */
@Entity
@DiscriminatorValue(value="candidate")

public class Candidate extends User implements Serializable {

	private String domain;
	@OneToMany(mappedBy="c",fetch=FetchType.EAGER)
	private List<CandidateJobOffer> aplies;
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
