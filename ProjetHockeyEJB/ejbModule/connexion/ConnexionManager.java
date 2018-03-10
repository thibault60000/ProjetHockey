package connexion;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import utilisateur.Utilisateur;

/**
 * Session Bean implementation class ConnexionManager
 */
@Stateless
@LocalBean
public class ConnexionManager implements ConnexionManagerRemote {

	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
    public ConnexionManager() {}
    
    @Override 
    public boolean seConnecter(String email, String mdp){
    	List<Utilisateur> testUtilisateur =em.createQuery("SELECT testUser FROM Utilisateur testUser WHERE mail='"+email+"'").getResultList();
    	if(mdp.equals(testUtilisateur.get(0).getPassword())){
    		return true;
    	}
    	else{
    		return false;
    	}
    	
    }
    

}
