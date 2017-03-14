package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Events
 *
 */
@Entity

public class Events implements Serializable {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int idEvent;
	private String Titre;
	private String description;
	private String adresse;
	private Date datedebut;
	private Date datefin;
	private int duree;
	private int nbparticipant;

	private static final long serialVersionUID = 1L;

	public Events() {
		super();
	}

	public Events(String titre, String description, String adresse) {
		super();
		Titre = titre;
		this.description = description;
		this.adresse = adresse;
	}

	public int getIdEvent() {
		return this.idEvent;
	}

	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}

	public String getTitre() {
		return this.Titre;
	}

	public void setTitre(String Titre) {
		this.Titre = Titre;
	}

	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Date getDatedebut() {
		return this.datedebut;
	}

	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}

	public Date getDatefin() {
		return this.datefin;
	}

	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}

	public int getDuree() {
		return this.duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNbparticipant() {
		return nbparticipant;
	}

	public void setNbparticipant(int nbparticipant) {
		this.nbparticipant = nbparticipant;
	}

}
