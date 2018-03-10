package statistique;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class StatistiqueManager
 */
@Stateless
@LocalBean
public class StatistiqueManager implements StatistiqueManagerRemote {

	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
    public StatistiqueManager() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public Collection<Statistique> listerStat(int idGardien) {
      	return em.createQuery("SELECT s FROM Statistique s WHERE gardien_id="+Integer.toString(idGardien)+"").getResultList(); 
    }
    
    @Override
    public Statistique UpdateOrInsert(int idMatchHockey, int idGardien){
    	Statistique statistique;
    	ArrayList<Statistique> statistiques = (ArrayList<Statistique>) em.createQuery("SELECT s FROM Statistique s WHERE matchHockey_id="+Integer.toString(idMatchHockey)+"AND gardien_id="+Integer.toString(idGardien)+"").getResultList();
    	if (statistiques.size()>0){
    		return statistiques.get(0);
    	}else{
    		return null;
    	}
    }
    
    @Override
    public Statistique ajouterStat(Statistique statistique){
    	em.persist(statistique);
    	return statistique;
    }
    
    @Override
    public Statistique miseAJourStat(Statistique statistique){
    	em.merge(statistique);
    	return statistique;
    }

}
