package artisanSpringBoot.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="compte")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@SequenceGenerator(name="seqCompte", sequenceName="seq_compte", initialValue = 1, allocationSize = 1)
@DiscriminatorColumn(name="type_compte",discriminatorType = DiscriminatorType.STRING, length=1)
@Embeddable
public class Compte {
	
	@Id
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqCompte")
	private Long idCompte;
	@Column(name="login", length=150)
	private String login;
	@Column(name="mdp", length=150)
	private String mdp;
	@Column(name="mail", length=255)
	private String email;
	@Embedded
	private Adresse adresse;
	//private String typeCompte;
	@Column(name="version")
	private int version;
	
	private boolean enable;
	@OneToMany(mappedBy = "idCompte")
	private Set<UserRole> roles;
	
	
	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Set<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Compte() {
		
	}

	public Compte(String login, String mdp, String email, Adresse adresse) {
		super();
		this.login = login;
		this.mdp = mdp;
		this.email = email;
		this.adresse = adresse;

	}

	public Long getIdCompte() {
		return idCompte;
	}

	public void setIdCompte(Long idCompte) {
		this.idCompte = idCompte;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCompte == null) ? 0 : idCompte.hashCode());
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
		Compte other = (Compte) obj;
		if (idCompte == null) {
			if (other.idCompte != null)
				return false;
		} else if (!idCompte.equals(other.idCompte))
			return false;
		return true;
	}
	
}
