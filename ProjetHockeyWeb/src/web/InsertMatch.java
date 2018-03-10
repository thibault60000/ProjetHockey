package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gardien.GardienManagerRemote;
import match.MatchHockey;
import match.MatchHockeyManagerRemote;

/**
 * Servlet implementation class InsertMatch
 */
@WebServlet("/InsertMatch")
public class InsertMatch extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		MatchHockeyManagerRemote matchHockeyManagerRemote = EjbLocator.getLocator().getMatchHockey();
		MatchHockey matchHockey = new MatchHockey();
		matchHockey.setDateDuMatchHockey(request.getParameter("annee")+"-"+request.getParameter("mois")+"-"+request.getParameter("jour"));
		MatchHockey newMatchHockey = matchHockeyManagerRemote.ajouterMatchHockey(matchHockey);
		RequestDispatcher rd = null;
		if(newMatchHockey.getIdMatchHockey() > 0) {
			GardienManagerRemote gardienManagerRemote = EjbLocator.getLocator().getGardien();
			request.setAttribute("listGardiens", gardienManagerRemote.listerGardien());
			request.setAttribute("MatchHockey", newMatchHockey);
			rd = request.getRequestDispatcher("/WEB-INF/jsps/saisie.jsp");
		}
		else {
			rd = request.getRequestDispatcher("/WEB-INF/jsps/newSaisie.jsp");
		}
		rd.forward(request, response);
	}

}
