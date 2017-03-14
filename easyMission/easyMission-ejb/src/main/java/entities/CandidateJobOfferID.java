package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: CandidateJobOfferID
 *
 */
@Embeddable

public class CandidateJobOfferID implements Serializable {

	int idCandidatePK;
	int idJobOfferPK;
	
	private static final long serialVersionUID = 1L;

	public CandidateJobOfferID() {
		super();
	}
	
	public CandidateJobOfferID(int idCandidatePK, int idJobOfferPK) {
		super();
		this.idCandidatePK = idCandidatePK;
		this.idJobOfferPK = idJobOfferPK;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCandidatePK;
		result = prime * result + idJobOfferPK;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CandidateJobOfferID other = (CandidateJobOfferID) obj;
		if (idCandidatePK != other.idCandidatePK)
			return false;
		if (idJobOfferPK != other.idJobOfferPK)
			return false;
		return true;
	}
   
}
