package artisanSpringBoot.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name="seqMetier", sequenceName="seq_metier", initialValue = 1, allocationSize = 1)
@Table(name="metier")
public class Metier
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqMetier")
	private Long idMetier;
	@Column(name="titre_metier", length=150)
	private String titreMetier;
	
	public Long getIdMetier() {
		return idMetier;
	}
	public void setIdMetier(Long idMetier) {
		this.idMetier = idMetier;
	}
	public String getTitreMetier() {
		return titreMetier;
	}
	public void setTitreMetier(String titreMetier) {
		this.titreMetier = titreMetier;
	}
	
	public Metier() {
	}
	public Metier(String titreMetier) {
		this.titreMetier = titreMetier;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idMetier == null) ? 0 : idMetier.hashCode());
		result = prime * result + ((titreMetier == null) ? 0 : titreMetier.hashCode());
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
		Metier other = (Metier) obj;
		if (idMetier == null) {
			if (other.idMetier != null)
				return false;
		} else if (!idMetier.equals(other.idMetier))
			return false;
		if (titreMetier == null) {
			if (other.titreMetier != null)
				return false;
		} else if (!titreMetier.equals(other.titreMetier))
			return false;
		return true;
	}
	
	
	

	
	
}
