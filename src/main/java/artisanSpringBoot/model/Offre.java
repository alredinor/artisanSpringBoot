package artisanSpringBoot.model;

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

import com.fasterxml.jackson.annotation.JsonView;

import artisanSpringBoot.model.jsonview.JsonViews;

@Entity
@SequenceGenerator(name = "seqOffre", sequenceName = "seq_offre", initialValue = 1, allocationSize = 1)
public class Offre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqOffre")
	@Column(name = "id_offre")
	@JsonView(JsonViews.Common.class)
	private Integer idOffre;
	
	@ManyToOne
	@JoinColumn(name = "id_artisan", foreignKey = @ForeignKey(name = "offre_artisan_id_fk"))
	@JsonView(JsonViews.Common.class)
	private Compte artisan;
	
	@OneToOne
	@JoinColumn(name = "id_service", foreignKey = @ForeignKey(name = "offre_service_id_fk"))
	@JsonView(JsonViews.Common.class)
	private Service service;
	
	@OneToOne
	@JoinColumn(name = "id_metier", foreignKey = @ForeignKey(name = "offre_metier_id_fk"))
	@JsonView(JsonViews.Common.class)
	private Metier metier;
	
	@Column(name = "version")
	private int version;

	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Integer getIdOffre() {
		return idOffre;
	}

	public void setIdOffre(Integer idOffre) {
		this.idOffre = idOffre;
	}

	public Compte getArtisan() {
		return artisan;
	}

	public void setArtisan(Compte artisan) {
		this.artisan = artisan;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artisan == null) ? 0 : artisan.hashCode());
		result = prime * result + ((idOffre == null) ? 0 : idOffre.hashCode());
		result = prime * result + ((metier == null) ? 0 : metier.hashCode());
		result = prime * result + ((service == null) ? 0 : service.hashCode());
		result = prime * result + version;
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
		Offre other = (Offre) obj;
		if (artisan == null) {
			if (other.artisan != null)
				return false;
		} else if (!artisan.equals(other.artisan))
			return false;
		if (idOffre == null) {
			if (other.idOffre != null)
				return false;
		} else if (!idOffre.equals(other.idOffre))
			return false;
		if (metier == null) {
			if (other.metier != null)
				return false;
		} else if (!metier.equals(other.metier))
			return false;
		if (service == null) {
			if (other.service != null)
				return false;
		} else if (!service.equals(other.service))
			return false;
		if (version != other.version)
			return false;
		return true;
	}
	
	
}
