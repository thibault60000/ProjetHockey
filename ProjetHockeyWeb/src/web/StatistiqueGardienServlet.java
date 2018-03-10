package web;

import statistique.Statistique;
import statistique.StatistiqueManagerRemote;
import gardien.Gardien;
import gardien.GardienManagerRemote;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import match.MatchHockey;
import match.MatchHockeyManagerRemote;
import statistique.Statistique;

/**
 * Servlet implementation class StatistiqueGardienServlet
 */
@WebServlet("/StatistiqueGardienServlet")
public class StatistiqueGardienServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String ATT_USER_ID = "utilisateur";
	private String ATT_SESSION_USER = "sessionUtilisateur";
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if ( session.getAttribute(ATT_USER_ID) == null ) {
			response.sendRedirect( request.getContextPath()+"/index.html");
		}else{
			GardienManagerRemote gardienManagerRemote = EjbLocator.getLocator().getGardien();
			request.setAttribute("listGardiens", gardienManagerRemote.listerGardien());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsps/listGardien.jsp");
			rd.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if ( session.getAttribute(ATT_USER_ID) == null ) {
			response.sendRedirect( request.getContextPath()+"/index.html");
		}else{
			StatistiqueManagerRemote statistiqueManagerRemote = EjbLocator.getLocator().getStatistique();
			Collection<Statistique> listStatistique = statistiqueManagerRemote.listerStat(Integer.parseInt(request.getParameter("idGardien")));
			request.setAttribute("listStatistique", listStatistique);
			
			MatchHockeyManagerRemote matchHockeyManagerRemote = EjbLocator.getLocator().getMatchHockey();
			//Collection<MatchHockey> listMatchHockeys = matchHockeyManagerRemote.listMatchHockey(Integer.parseInt(request.getParameter("idGardien")));
			Collection<MatchHockey> listMatchHockeys = matchHockeyManagerRemote.listMatchHockey();
			request.setAttribute("listMatchHockeys", listMatchHockeys);
			
			GardienManagerRemote gardienManagerRemote = EjbLocator.getLocator().getGardien();
			Gardien monGardien = gardienManagerRemote.getInfoGardien(Integer.parseInt(request.getParameter("idGardien")));
			request.setAttribute("monGardien",monGardien);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsps/analyse.jsp");
			rd.forward(request, response);
		}
	}

}
