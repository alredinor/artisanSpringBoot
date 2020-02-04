package artisanSpringBoot.model;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="seqImplementation", sequenceName="seq_impl", initialValue = 1, allocationSize = 1)
public class MetierJoinCompte {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqImplementation")
	private Long id;
	@ManyToOne
	@JoinColumn(name="id_compte", foreignKey = @ForeignKey(name="metier_compte_id_fk"))
	private Compte compte;
	@ManyToOne
	@JoinColumn(name="id_metier", foreignKey = @ForeignKey(name="compte_metier_id_fk"))
	private Metier metier;
	
	public MetierJoinCompte() 
	{
		
	}

	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Compte getCompte() 
	{
		return compte;
	}
	public void setCompte(Compte compte) 
	{
		this.compte = compte;
	}
	public Metier getMetier() 
	{
		return metier;
	}
	public void setMetier(Metier metier) 
	{
		this.metier = metier;
	}
	
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((compte == null) ? 0 : compte.hashCode());
		result = prime * result + ((metier == null) ? 0 : metier.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MetierJoinCompte other = (MetierJoinCompte) obj;
		if (compte == null)
		{
			if (other.compte != null)
				return false;
		} else if (!compte.equals(other.compte))
			return false;
		if (metier == null) 
		{
			if (other.metier != null)
				return false;
		} 
		else if (!metier.equals(other.metier))
			return false;
		return true;
	}
	
	
}
