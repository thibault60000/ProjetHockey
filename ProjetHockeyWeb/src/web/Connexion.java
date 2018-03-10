package web;

import gardien.GardienManagerRemote;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utilisateur.Utilisateur;
import connexion.ConnexionManager;
import connexion.ConnexionManagerRemote;

/**
 * Servlet implementation class Accueil
 */
@WebServlet("/Accueil")
public class Connexion extends HttpServlet {
	
	 private String ATT_USER_ID = "utilisateur";
	 private String ATT_SESSION_USER = "sessionUtilisateur";

	public String getATT_USER_ID() {
		return ATT_USER_ID;
	}
	public void setATT_USER_ID(String aTT_USER_ID) {
		ATT_USER_ID = aTT_USER_ID;
	}
	public String getATT_SESSION_USER() {
		return ATT_SESSION_USER;
	}
	public void setATT_SESSION_USER(String aTT_SESSION_USER) {
		ATT_SESSION_USER = aTT_SESSION_USER;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if ( session.getAttribute(ATT_USER_ID) == null ) {
			response.sendRedirect( request.getContextPath()+"/index.html");
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsps/accueil.jsp");
			rd.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConnexionManagerRemote connexionManagerRemote = EjbLocator.getLocator().getSeConnecter();
		String adresseMail = request.getParameter("email");
		String motDePasse = request.getParameter("password");
		boolean testPseudoMdp = connexionManagerRemote.seConnecter(adresseMail, motDePasse);
		RequestDispatcher rd = null;
		if (testPseudoMdp == false ){
			rd = request.getRequestDispatcher("/index.html");
		}else{
			HttpSession session = request.getSession();
			session.setAttribute(ATT_USER_ID, adresseMail);
			session.setAttribute(ATT_SESSION_USER, motDePasse);
			rd = request.getRequestDispatcher("/WEB-INF/jsps/accueil.jsp");
		}
		rd.forward(request, response);
	}

}
