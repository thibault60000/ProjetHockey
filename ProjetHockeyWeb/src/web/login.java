package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String ATT_USER_ID = "utilisateur";
	private String ATT_SESSION_USER = "sessionUtilisateur";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession();
	if ( session.getAttribute(ATT_USER_ID) == null ) {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsps/login.jsp");
		rd.forward(request, response);
	}else{
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsps/accueil.jsp");
		rd.forward(request, response);
	}
	}
}
