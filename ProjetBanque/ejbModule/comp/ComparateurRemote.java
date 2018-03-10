package comp;

import java.util.ArrayList;

import javax.ejb.Remote;

@Remote
public interface ComparateurRemote {

	void calculer(double somme, String mois, int annee, int duree, double taux);
	ArrayList<String> comparer();
	
}
