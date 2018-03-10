package web;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import SResultSet.SerializedResultSet;
import connexion.ConnexionManagerRemote;

/**
 * Servlet implementation class appletLogin
 */
@WebServlet("/appletLogin")
public class appletLogin extends HttpServlet {
	
	
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
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public appletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try{
			boolean sresultat;
			System.out.println("11");
			ObjectInputStream entree=new ObjectInputStream(request.getInputStream());
			System.out.println("12");
			String tmp =(String)entree.readObject();
			String[] tmp2 = tmp.split("\\.\\.\\.");
			System.out.println(tmp2[0]);
			System.out.println(tmp2[1]);
			System.out.println("14");
			ObjectOutputStream sortie=new ObjectOutputStream(response.getOutputStream());
			System.out.println("15");
			ConnexionManagerRemote connexionManagerRemote = EjbLocator.getLocator().getSeConnecter();
			System.out.println("16");
			String adresseMail = tmp2[0];
			String motDePasse = tmp2[1];
			System.out.println("17");
			boolean testPseudoMdp = connexionManagerRemote.seConnecter(adresseMail, motDePasse);
			RequestDispatcher rd = null;
			if (testPseudoMdp == false ){
				sresultat = false;
			}else{
				sresultat = true;
				HttpSession session = request.getSession();
				session.setAttribute(ATT_USER_ID, adresseMail);
				session.setAttribute(ATT_SESSION_USER, motDePasse);
			}
			System.out.println("18");
			sortie.writeBoolean(sresultat);
		}
		catch (Exception ex) {
			System.out.println("Erreur d'exécution de la requête SQL : "+ex);
			}

		
		
	}

}
