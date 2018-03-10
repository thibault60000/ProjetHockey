package comp;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

/**
 * Session Bean implementation class historique
 */
@Stateful
public class Comparateur implements ComparateurRemote{
	
	//private
	ArrayList<ArrayList<String>> mes_emprunts_donnees = new ArrayList<ArrayList<String>>();
	ArrayList<String> mes_emprunts_param = new ArrayList<String>();
    /**
     * Default constructor. 
     */
    public Comparateur() {
        // TODO Auto-generated constructor stub
    }
    
    public void effacer(){
    	mes_emprunts_donnees.clear();
    	mes_emprunts_param.clear();
    }
    
    public void calculer(double somme, String mois, int annee, int duree, double taux){
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
    	String condition = "Somme empruntée : "+Double.toString(somme)+" - Taux : "+Double.toString(taux)+" - Durée : "+Integer.toString(duree)+" - Début du remboursement : "+mois+" "+Integer.toString(annee);
    	mes_emprunts_donnees.add(emprunt);
    	mes_emprunts_param.add(condition);
    }
    
    public ArrayList<String> comparer(){
    	ArrayList<String> mes_emprunts = new ArrayList<String>();
    	int i = 0;
    	for(String emprunt : mes_emprunts_param){
    		mes_emprunts.add(emprunt);
    		for(String ligne : mes_emprunts_donnees.get(i)){
				mes_emprunts.add(ligne);
			}
    		i++;
    		mes_emprunts.add("---------------------------------------------");
    	}
    	
    	return mes_emprunts;
    }
    
    public static double arrondir(double nb){
    	int tmp = (int)(nb*100);
    	nb = Double.parseDouble(Integer.toString(tmp))/100;
    	return nb;
    }
  
  

}
