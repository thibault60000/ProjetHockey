package match;

import gardien.Gardien;

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
public class MatchHockey implements Serializable{

	@Id
	@GeneratedValue (strategy=GenerationType.AUTO) 
	private int idMatchHockey;
	
	private String DateDuMatchHockey;
	

	@OneToMany(cascade={CascadeType.REMOVE, CascadeType.PERSIST},
            mappedBy="matchHockey")
	private Collection<Statistique> statistiques;
	

	public int getIdMatchHockey() {
		return idMatchHockey;
	}

	public void setIdMatchHockey(int idMatchHockey) {
		this.idMatchHockey = idMatchHockey;
	}

	public String getDateDuMatchHockey() {
		return DateDuMatchHockey;
	}

	public void setDateDuMatchHockey(String dateDuMatchHockey) {
		DateDuMatchHockey = dateDuMatchHockey;
	}

	public Collection<Statistique> getStatistiques() {
		return statistiques;
	}

	public void setStatistiques(Collection<Statistique> statistiques) {
		this.statistiques = statistiques;
	}
	
	
	  
}
