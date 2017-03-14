package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: JobOffer
 *
 */
@Entity

public class JobOffer implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idOffer;
	private String title;
	private String city;
	private Date PostDate;
	private String sector;
	private String studyLevel;
	private String ContractType;
	private String experience;
	private Integer minSalary;
	private Integer maxSalary;
	private String competence;
	private String exigence;
	private String advantages;
	private boolean approuved;
	private String type; //parttime,fulltime,intership,flextime
	private boolean sponsored;
	private String beginingHour;
	private String finishingHour;
	private String hoursPerMonth;
	@ManyToOne
	private Recruiter recruiter;
	@OneToMany(mappedBy="testJobOffer",cascade={CascadeType.ALL},orphanRemoval=true,fetch=FetchType.EAGER)
	List<OnLineTest> test;
	@OneToMany(mappedBy="j",fetch=FetchType.EAGER,cascade={CascadeType.ALL},orphanRemoval=true)
	private List<CandidateJobOffer> aplyes;
	private static final long serialVersionUID = 1L;

	public JobOffer() {
		super();
	}
	
	public JobOffer(int idOffer, String title, String city, Date postDate, String sector, String studyLevel,
			String contractType, String experience, Integer minSalary, Integer maxSalary, String competence,
			String exigence, String advantages, boolean approuved, String type, boolean sponsored, String beginingHour,
			String finishingHour, String hoursPerMonth, Recruiter recruiter) {
		super();
		this.idOffer = idOffer;
		this.title = title;
		this.city = city;
		this.PostDate = postDate;
		this.sector = sector;
		this.studyLevel = studyLevel;
		this.ContractType = contractType;
		this.experience = experience;
		this.minSalary = minSalary;
		this.maxSalary = maxSalary;
		this.competence = competence;
		this.exigence = exigence;
		this.advantages = advantages;
		this.approuved = approuved;
		this.type = type;
		this.sponsored = sponsored;
		this.beginingHour = beginingHour;
		this.finishingHour = finishingHour;
		this.hoursPerMonth = hoursPerMonth;
		this.recruiter = recruiter;
	}
    
	public List<OnLineTest> getTest() {
		return test;
	}

	public void setTest(List<OnLineTest> test) {
		this.test = test;
	}

	public List<CandidateJobOffer> getAplyes() {
		return aplyes;
	}

	public void setAplyes(List<CandidateJobOffer> aplyes) {
		this.aplyes = aplyes;
	}

	public JobOffer(String title, String city, Date postDate, String sector, String studyLevel, String contractType,
			String experience, Integer minSalary, Integer maxSalary, String competence, String exigence,
			String advantages, boolean approuved, String type, boolean sponsored, String beginingHour,
			String finishingHour, String hoursPerMonth, Recruiter recruiter) {
		super();
		this.title = title;
		this.city = city;
		this.PostDate = postDate;
		this.sector = sector;
		this.studyLevel = studyLevel;
		this.ContractType = contractType;
		this.experience = experience;
		this.minSalary = minSalary;
		this.maxSalary = maxSalary;
		this.competence = competence;
		this.exigence = exigence;
		this.advantages = advantages;
		this.approuved = approuved;
		this.type = type;
		this.sponsored = sponsored;
		this.beginingHour = beginingHour;
		this.finishingHour = finishingHour;
		this.hoursPerMonth = hoursPerMonth;
		this.recruiter = recruiter;
	}

	public int getIdOffer() {
		return idOffer;
	}

	public void setIdOffer(int idOffer) {
		this.idOffer = idOffer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getPostDate() {
		return PostDate;
	}

	public void setPostDate(Date postDate) {
		PostDate = postDate;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getStudyLevel() {
		return studyLevel;
	}

	public void setStudyLevel(String studyLevel) {
		this.studyLevel = studyLevel;
	}

	public String getContractType() {
		return ContractType;
	}

	public void setContractType(String contractType) {
		ContractType = contractType;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public Integer getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(Integer minSalary) {
		this.minSalary = minSalary;
	}

	public Integer getMaxSalary() {
		return maxSalary;
	}

	public void setMaxSalary(Integer maxSalary) {
		this.maxSalary = maxSalary;
	}

	public String getCompetence() {
		return competence;
	}

	public void setCompetence(String competence) {
		this.competence = competence;
	}

	public String getExigence() {
		return exigence;
	}

	public void setExigence(String exigence) {
		this.exigence = exigence;
	}

	public String getAdvantages() {
		return advantages;
	}

	public void setAdvantages(String advantages) {
		this.advantages = advantages;
	}

	public boolean isApprouved() {
		return approuved;
	}

	public void setApprouved(boolean approuved) {
		this.approuved = approuved;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isSponsored() {
		return sponsored;
	}

	public void setSponsored(boolean sponsored) {
		this.sponsored = sponsored;
	}

	public String getBeginingHour() {
		return beginingHour;
	}

	public void setBeginingHour(String beginingHour) {
		this.beginingHour = beginingHour;
	}

	public String getFinishingHour() {
		return finishingHour;
	}

	public void setFinishingHour(String finishingHour) {
		this.finishingHour = finishingHour;
	}

	public String getHoursPerMonth() {
		return hoursPerMonth;
	}

	public void setHoursPerMonth(String hoursPerMonth) {
		this.hoursPerMonth = hoursPerMonth;
	}

	public Recruiter getRecruiter() {
		return recruiter;
	}

	public void setRecruiter(Recruiter rectuiter) {
		this.recruiter = rectuiter;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
   
}
