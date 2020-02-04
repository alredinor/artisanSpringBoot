package artisanSpringBoot.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name="seqDemande", sequenceName="seq_demande", initialValue = 1, allocationSize = 1)
public class Demande {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqDemande")
	@Column(name="id_demande")
	private Long idDemande;
	private String message;
	
	@ManyToOne
	@JoinColumn(name="id_artisan", foreignKey = @ForeignKey(name="demande_artisan_id_fk"))
	private Compte artisan;
	
	@ManyToOne
	@JoinColumn(name="id_client", foreignKey = @ForeignKey(name="demande_client_id_fk"))
	private Compte client;
	
	
	@OneToOne
	@JoinColumn(name="id_service", foreignKey = @ForeignKey(name="demande_service_id_fk"))
	private Service service;
	
	@OneToOne
	@JoinColumn(name="id_metier", foreignKey = @ForeignKey(name="demande_metier_id_fk"))
	private Metier metier;
	@Column(name="statut")
	private StatutDemande statut;
	@Temporal(TemporalType.DATE)
	@Column(name="date_creation_demande")
	private Date date;
	
	public Date getDate() {
		return date;
	}





	public void setDate(Date date) {
		this.date = date;
	}





	public Demande() {
	}

	

	

	public Long getIdDemande() {
		return idDemande;
	}





	public void setIdDemande(Long idDemande) {
		this.idDemande = idDemande;
	}





	





	public StatutDemande getStatut() {
		return statut;
	}





	public void setStatut(StatutDemande statut) {
		this.statut = statut;
	}





	public Compte getArtisan() {
		return artisan;
	}





	public void setArtisan(Compte artisan) {
		this.artisan = artisan;
	}





	public Compte getClient() {
		return client;
	}





	public void setClient(Compte client) {
		this.client = client;
	}





	public Service getService() {
		return service;
	}





	public void setService(Service service) {
		this.service = service;
	}





	public Metier getMetier() {
		return metier;
	}





	public void setMetier(Metier metier) {
		this.metier = metier;
	}





	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idDemande == null) ? 0 : idDemande.hashCode());
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
		Demande other = (Demande) obj;
		if (idDemande == null) {
			if (other.idDemande != null)
				return false;
		} else if (!idDemande.equals(other.idDemande))
			return false;
		return true;
	}

	
	
	
	
}
