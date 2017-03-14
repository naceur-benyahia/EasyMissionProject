package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: CandidateJobOffer
 *
 */
@Entity

public class CandidateJobOffer implements Serializable {
	@EmbeddedId
	private CandidateJobOfferID o;
	@ManyToOne
	@JoinColumn(name="idCandidatePK",insertable=false,updatable=false)
	private Candidate c;
	@ManyToOne
	@JoinColumn(name="idJobOfferPK",insertable=false,updatable=false)
	private JobOffer j;
	private boolean apply;
	private static final long serialVersionUID = 1L;

	public CandidateJobOffer() {
		super();
	}
	
	public CandidateJobOffer(CandidateJobOfferID o, Candidate c, JobOffer j, boolean apply) {
		super();
		this.o = o;
		this.c = c;
		this.j = j;
		this.apply = apply;
	}

	public CandidateJobOfferID getO() {
		return o;
	}

	public void setO(CandidateJobOfferID o) {
		this.o = o;
	}

	public Candidate getC() {
		return c;
	}

	public void setC(Candidate c) {
		this.c = c;
	}

	public JobOffer getJ() {
		return j;
	}

	public void setJ(JobOffer j) {
		this.j = j;
	}

	public boolean isApply() {
		return apply;
	}

	public void setApply(boolean apply) {
		this.apply = apply;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
   
}
