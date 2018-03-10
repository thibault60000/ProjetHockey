package connexion;

import javax.ejb.Remote;
import javax.servlet.http.HttpServletRequest;

import utilisateur.Utilisateur;

@Remote
public interface ConnexionManagerRemote {

	boolean seConnecter(String email, String mdp);

}
