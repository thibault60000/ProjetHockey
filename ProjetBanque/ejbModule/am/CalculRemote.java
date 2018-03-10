package am;

import java.util.ArrayList;

import javax.ejb.Remote;

import SResultSet.SerializedResultSet;

@Remote
public interface CalculRemote {
	public String calculer();
	public ArrayList<String> calculer(double somme, String mois, int annee, int duree, double taux);
}
