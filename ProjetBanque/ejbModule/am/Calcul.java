package am;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class Calcul
 */
@Stateless
@LocalBean
public class Calcul implements CalculRemote {

    /**
     * Default constructor. 
     */    
    @Override
    public ArrayList<String> calculer(double somme, String mois, int annee, int duree, double taux){
    	ArrayList<String> emprunt = new ArrayList<String>();
    	double[][] table_calcul = new double[duree][5];
    	taux = taux/100;
    	double annuite = somme * (taux/(1-Math.pow(1+taux, 0 - duree)));
    	for(int i=0;i<duree;i++){
    		table_calcul[i][0] = annee + i;
    		table_calcul[i][1] = somme;
    		table_calcul[i][2] = somme*taux;
    		table_calcul[i][3] = annuite-table_calcul[i][2];
    		table_calcul[i][4] = annuite;
    		somme = somme - table_calcul[i][3];
    	}    	
    	
    	for(int j=0;j<duree;j++){
    		String ligne =mois+" "+Integer.toString((int)table_calcul[j][0])+"---"+arrondir(table_calcul[j][1])+"---"+arrondir(table_calcul[j][2])+"---"+arrondir(table_calcul[j][3])+"---"+arrondir(table_calcul[j][4]);
          	emprunt.add(ligne);
    	}
    	
    	return emprunt;
    }
    
    public double arrondir(double nb){
    	int tmp = (int)(nb*100);
    	nb = Double.parseDouble(Integer.toString(tmp))/100;
    	return nb;
    }

	@Override
	public String calculer() {
		// TODO Auto-generated method stub
		return null;
	}
    
        
    
   
}
