package services;

import java.util.List;

import javax.ejb.Remote;

import entities.JobOffer;
import entities.Topic;

@Remote
public interface JobOfferEJBRemote {
	List<JobOffer> FindAllJobOffers();
	List<JobOffer> FindByType(String category);
	Float FindMoyOfferByContractType(String category);
	List<JobOffer> advancedsearchJobOffer(String title);
	List<JobOffer> FindByState(boolean state );
	void updateJobOffer(JobOffer jobOffer);
	void deleteJobOffer(JobOffer jobOffer);
	JobOffer findJobOfferById(Integer id);
	List<JobOffer> FindByContractType(String category);
	
}
