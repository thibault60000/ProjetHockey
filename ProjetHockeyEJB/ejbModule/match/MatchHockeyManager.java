package match;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class MatchHockeyManager
 */
@Stateless
@LocalBean
public class MatchHockeyManager implements MatchHockeyManagerRemote {

	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
    public MatchHockeyManager() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public MatchHockey ajouterMatchHockey(MatchHockey matchHockey){
    	em.persist(matchHockey);
    	return matchHockey;
    }

    @Override
    public Collection<MatchHockey> listMatchHockey(){
    	return em.createQuery("SELECT m FROM MatchHockey m").getResultList();
    }
    
    @Override
    public Collection<MatchHockey> listMatchHockey(int idGardien){
    	//return em.createQuery("SELECT m FROM MatchHockey m JOIN m.idMatchHockey a WHERE a.gardien_id= :"+Integer.toString(idGardien)+")").getResultList();
    	javax.persistence.Query query = em.createQuery("SELECT m FROM Statistique s JOIN s.matchHockey_id m WHERE s.gardien_id=:gardien");
    	query.setParameter("gardien", Integer.toString(idGardien));
    	MatchHockey matchHockey;
    	return (ArrayList<MatchHockey>)query.getResultList();
    	//ArrayList<MatchHockey> matchHockeys = (ArrayList<MatchHockey>) em.createQuery("SELECT m FROM MatchHockey m WHERE idMatchHockey="+Integer.toString(idMatchHockey)+"").getResultList();
    }
  
    @Override
    public MatchHockey getInfoMatchHockey(int idMatchHockey){
    	MatchHockey matchHockey;
    	ArrayList<MatchHockey> matchHockeys = (ArrayList<MatchHockey>) em.createQuery("SELECT m FROM MatchHockey m WHERE idMatchHockey="+Integer.toString(idMatchHockey)+"").getResultList();
    	matchHockey = matchHockeys.get(0);
    	return matchHockey;
    }
    
}
