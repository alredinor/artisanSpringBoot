package artisanSpringBoot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="service")
@SequenceGenerator(name="seqService", sequenceName="seq_service", initialValue = 1, allocationSize = 1)
public class Service {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqService")
	private Long idService;
	@Column(name="Service")
	private String nomService;
	@ManyToOne
	@JoinColumn(name="id_metier", foreignKey = @ForeignKey(name="service_metier_id_fk"))
	private Metier metier;
	
	public Long getIdService() {
		return idService;
	}
	public void setIdService(Long idService) {
		this.idService = idService;
	}
	public String getNomService() {
		return nomService;
	}
	public void setNomService(String nomService) {
		this.nomService = nomService;
	}
	public Metier getMetier() {
		return metier;
	}
	public void setMetier(Metier metier) {
		this.metier = metier;
	}
	
	public Service() {
	}
	
	public Service(String nomService) {
		this.nomService = nomService;
	}
	
	
	public Service(String nomService, Metier metier) {
		this.nomService = nomService;
		this.metier = metier;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idService == null) ? 0 : idService.hashCode());
		result = prime * result + ((metier == null) ? 0 : metier.hashCode());
		result = prime * result + ((nomService == null) ? 0 : nomService.hashCode());
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
		Service other = (Service) obj;
		if (idService == null) {
			if (other.idService != null)
				return false;
		} else if (!idService.equals(other.idService))
			return false;
		if (metier == null) {
			if (other.metier != null)
				return false;
		} else if (!metier.equals(other.metier))
			return false;
		if (nomService == null) {
			if (other.nomService != null)
				return false;
		} else if (!nomService.equals(other.nomService))
			return false;
		return true;
	}

	




}
