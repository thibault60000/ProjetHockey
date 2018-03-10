package gardien;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import statistique.Statistique;

@Entity
public class Gardien implements Serializable{

	@Id
	@GeneratedValue (strategy=GenerationType.AUTO) 
	private int idGardien;
	
	private String nomGardien;
	private String prenomGardien;
	
	@OneToMany(cascade={CascadeType.REMOVE, CascadeType.PERSIST},
            mappedBy="gardien")
	private Collection<Statistique> statistiques;

	public int getIdGardien() {
		return idGardien;
	}

	public void setIdGardien(int idGardien) {
		this.idGardien = idGardien;
	}

	public String getNomGardien() {
		return nomGardien;
	}

	public void setNomGardien(String nomGardien) {
		this.nomGardien = nomGardien;
	}

	public String getPrenomGardien() {
		return prenomGardien;
	}

	public void setPrenomGardien(String prenomGardien) {
		this.prenomGardien = prenomGardien;
	}

	public Collection<Statistique> getStatistiques() {
		return statistiques;
	}

	public void setStatistiques(Collection<Statistique> statistiques) {
		this.statistiques = statistiques;
	}

	
	
	
}
