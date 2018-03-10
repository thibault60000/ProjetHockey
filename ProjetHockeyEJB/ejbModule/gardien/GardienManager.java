package gardien;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class GardienManager
 */
@Stateless
@LocalBean
public class GardienManager implements GardienManagerRemote {
	
	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
    public GardienManager() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public Collection<Gardien> listerGardien() {
    	// Vous pouvez aussi utiliser une named query d�finie dans l�entit� (cf TD5)
    	return em.createQuery("SELECT g FROM Gardien g").getResultList(); 
    	}
    
    @Override
    public Gardien getInfoGardien(int idGardien){
    	Gardien monGardien;
    	ArrayList<Gardien> tmp= (ArrayList<Gardien>)em.createQuery("Select g FROM Gardien g WHERE idGardien="+Integer.toString(idGardien)+"").getResultList();
    	monGardien = tmp.get(0);
    	return monGardien;
    }
    
}
