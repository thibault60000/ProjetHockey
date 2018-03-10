package web;

import gardien.Gardien;
import gardien.GardienManagerRemote;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import match.MatchHockey;
import match.MatchHockeyManagerRemote;
import statistique.Statistique;
import statistique.StatistiqueManagerRemote;

/**
 * Servlet implementation class InsertStatistique
 */
@WebServlet("/InsertStatistique")
public class InsertStatistique extends HttpServlet {
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

		
		String tmp = (String) request.getParameter("combinaison");
		double aTraiter = Double.parseDouble(tmp);
		
		int codeGardien=(int)((aTraiter-(aTraiter%1000))/1000);
		int codeZoneArret = (int)(((aTraiter%1000)-((aTraiter%1000)%100))/100);
		int codeZoneTir = (int)(((aTraiter%100)-((aTraiter%100)%10))/10);
		int codeBut = (int)(aTraiter%10) ;
		
		GardienManagerRemote gardienManagerRemote = EjbLocator.getLocator().getGardien();
		Gardien gardien = gardienManagerRemote.getInfoGardien((int)codeGardien);
		MatchHockeyManagerRemote matchHockeyManagerRemote = EjbLocator.getLocator().getMatchHockey();
		MatchHockey matchHockey = matchHockeyManagerRemote.getInfoMatchHockey(Integer.parseInt(request.getParameter("idMatchHockey")));
		
		Statistique statistique = new Statistique();
		StatistiqueManagerRemote statistiqueManagerRemote = EjbLocator.getLocator().getStatistique();
		statistique = statistiqueManagerRemote.UpdateOrInsert((Integer.parseInt(request.getParameter("idMatchHockey"))), codeGardien);
		
		RequestDispatcher rd = null;
		
		if( statistique == null){
			//on ajoute une ligne
			statistique = new Statistique();
			statistique.setGardien(gardien);
			statistique.setMatch(matchHockey);
			
			
			if(codeBut==1){
				if(codeZoneTir == 1){
					statistique.setArretZoneDeTir1(statistique.getArretZoneDeTir1()+1);
				}
				if(codeZoneTir == 2){
					statistique.setArretZoneDeTir2(statistique.getArretZoneDeTir2()+1);
				}
				if(codeZoneTir == 3){
					statistique.setArretZoneDeTir3(statistique.getArretZoneDeTir3()+1);
				}
				if(codeZoneTir == 4){
					statistique.setArretZoneDeTir4(statistique.getArretZoneDeTir4()+1);
				}
				if(codeZoneTir == 5){
					statistique.setArretZoneDeTir5(statistique.getArretZoneDeTir5()+1);
				}
				if(codeZoneTir == 6){
					statistique.setArretZoneDeTir6(statistique.getArretZoneDeTir6()+1);
				}
				if(codeZoneArret == 1){
					statistique.setArretZoneArret1(statistique.getArretZoneArret1()+1);
				}
				if(codeZoneArret == 2){
					statistique.setArretZoneArret2(statistique.getArretZoneArret2()+1);
				}
				if(codeZoneArret == 3){
					statistique.setArretZoneArret3(statistique.getArretZoneArret3()+1);
				}
				if(codeZoneArret == 4){
					statistique.setArretZoneArret4(statistique.getArretZoneArret4()+1);
				}
				if(codeZoneArret == 5){
					statistique.setArretZoneArret5(statistique.getArretZoneArret5()+1);
				}
				if(codeZoneArret == 6){
					statistique.setArretZoneArret6(statistique.getArretZoneArret6()+1);
				}
				if(codeZoneArret == 7){
					statistique.setArretZoneArret7(statistique.getArretZoneArret7()+1);
				}
				if(codeZoneArret == 8){
					statistique.setArretZoneArret8(statistique.getArretZoneArret8()+1);
				}
				if(codeZoneArret == 9){
					statistique.setArretZoneArret9(statistique.getArretZoneArret9()+1);
				}
			}
			if(codeZoneTir == 1){
				statistique.setLancerZoneDeTir1(statistique.getLancerZoneDeTir1()+1);
			}
			if(codeZoneTir == 2){
				statistique.setLancerZoneDeTir2(statistique.getLancerZoneDeTir2()+1);
			}
			if(codeZoneTir == 3){
				statistique.setLancerZoneDeTir3(statistique.getLancerZoneDeTir3()+1);
			}
			if(codeZoneTir == 4){
				statistique.setLancerZoneDeTir4(statistique.getLancerZoneDeTir4()+1);
			}
			if(codeZoneTir == 5){
				statistique.setLancerZoneDeTir5(statistique.getLancerZoneDeTir5()+1);
			}
			if(codeZoneTir == 6){
				statistique.setLancerZoneDeTir6(statistique.getLancerZoneDeTir6()+1);
			}
			if(codeZoneArret == 1){
				statistique.setLancerZoneArret1(statistique.getLancerZoneArret1()+1);
			}
			if(codeZoneArret == 2){
				statistique.setLancerZoneArret2(statistique.getLancerZoneArret2()+1);
			}
			if(codeZoneArret == 3){
				statistique.setLancerZoneArret3(statistique.getLancerZoneArret3()+1);
			}
			if(codeZoneArret == 4){
				statistique.setLancerZoneArret4(statistique.getLancerZoneArret4()+1);
			}
			if(codeZoneArret == 5){
				statistique.setLancerZoneArret5(statistique.getLancerZoneArret5()+1);
			}
			if(codeZoneArret == 6){
				statistique.setLancerZoneArret6(statistique.getLancerZoneArret6()+1);
			}
			if(codeZoneArret == 7){
				statistique.setLancerZoneArret7(statistique.getLancerZoneArret7()+1);
			}
			if(codeZoneArret == 8){
				statistique.setLancerZoneArret8(statistique.getLancerZoneArret8()+1);
			}
			if(codeZoneArret == 9){
				statistique.setLancerZoneArret9(statistique.getLancerZoneArret9()+1);
			}
			Statistique newStatistique = statistiqueManagerRemote.ajouterStat(statistique);
			
			if(newStatistique.getIdStatistique() > 0) {
				request.setAttribute("listGardiens", gardienManagerRemote.listerGardien());
				request.setAttribute("MatchHockey", matchHockey);
				rd = request.getRequestDispatcher("/WEB-INF/jsps/saisie.jsp");
			}else{
				rd = request.getRequestDispatcher("/WEB-INF/jsps/errorSaisie.jsp");
			}
			rd.forward(request, response);
			
		}else{
			//mise à jour de la ligne
			
			if(codeBut==1){
				if(codeZoneTir == 1){
					statistique.setArretZoneDeTir1(statistique.getArretZoneDeTir1()+1);
				}
				if(codeZoneTir == 2){
					statistique.setArretZoneDeTir2(statistique.getArretZoneDeTir2()+1);
				}
				if(codeZoneTir == 3){
					statistique.setArretZoneDeTir3(statistique.getArretZoneDeTir3()+1);
				}
				if(codeZoneTir == 4){
					statistique.setArretZoneDeTir4(statistique.getArretZoneDeTir4()+1);
				}
				if(codeZoneTir == 5){
					statistique.setArretZoneDeTir5(statistique.getArretZoneDeTir5()+1);
				}
				if(codeZoneTir == 6){
					statistique.setArretZoneDeTir6(statistique.getArretZoneDeTir6()+1);
				}
				if(codeZoneArret == 1){
					statistique.setArretZoneArret1(statistique.getArretZoneArret1()+1);
				}
				if(codeZoneArret == 2){
					statistique.setArretZoneArret2(statistique.getArretZoneArret2()+1);
				}
				if(codeZoneArret == 3){
					statistique.setArretZoneArret3(statistique.getArretZoneArret3()+1);
				}
				if(codeZoneArret == 4){
					statistique.setArretZoneArret4(statistique.getArretZoneArret4()+1);
				}
				if(codeZoneArret == 5){
					statistique.setArretZoneArret5(statistique.getArretZoneArret5()+1);
				}
				if(codeZoneArret == 6){
					statistique.setArretZoneArret6(statistique.getArretZoneArret6()+1);
				}
				if(codeZoneArret == 7){
					statistique.setArretZoneArret7(statistique.getArretZoneArret7()+1);
				}
				if(codeZoneArret == 8){
					statistique.setArretZoneArret8(statistique.getArretZoneArret8()+1);
				}
				if(codeZoneArret == 9){
					statistique.setArretZoneArret9(statistique.getArretZoneArret9()+1);
				}
			}
			if(codeZoneTir == 1){
				statistique.setLancerZoneDeTir1(statistique.getLancerZoneDeTir1()+1);
			}
			if(codeZoneTir == 2){
				statistique.setLancerZoneDeTir2(statistique.getLancerZoneDeTir2()+1);
			}
			if(codeZoneTir == 3){
				statistique.setLancerZoneDeTir3(statistique.getLancerZoneDeTir3()+1);
			}
			if(codeZoneTir == 4){
				statistique.setLancerZoneDeTir4(statistique.getLancerZoneDeTir4()+1);
			}
			if(codeZoneTir == 5){
				statistique.setLancerZoneDeTir5(statistique.getLancerZoneDeTir5()+1);
			}
			if(codeZoneTir == 6){
				statistique.setLancerZoneDeTir6(statistique.getLancerZoneDeTir6()+1);
			}
			if(codeZoneArret == 1){
				statistique.setLancerZoneArret1(statistique.getLancerZoneArret1()+1);
			}
			if(codeZoneArret == 2){
				statistique.setLancerZoneArret2(statistique.getLancerZoneArret2()+1);
			}
			if(codeZoneArret == 3){
				statistique.setLancerZoneArret3(statistique.getLancerZoneArret3()+1);
			}
			if(codeZoneArret == 4){
				statistique.setLancerZoneArret4(statistique.getLancerZoneArret4()+1);
			}
			if(codeZoneArret == 5){
				statistique.setLancerZoneArret5(statistique.getLancerZoneArret5()+1);
			}
			if(codeZoneArret == 6){
				statistique.setLancerZoneArret6(statistique.getLancerZoneArret6()+1);
			}
			if(codeZoneArret == 7){
				statistique.setLancerZoneArret7(statistique.getLancerZoneArret7()+1);
			}
			if(codeZoneArret == 8){
				statistique.setLancerZoneArret8(statistique.getLancerZoneArret8()+1);
			}
			if(codeZoneArret == 9){
				statistique.setLancerZoneArret9(statistique.getLancerZoneArret9()+1);
			}
			
			Statistique statistiqueUpdated = statistiqueManagerRemote.miseAJourStat(statistique);
		
			request.setAttribute("listGardiens", gardienManagerRemote.listerGardien());
			request.setAttribute("MatchHockey", matchHockey);
			rd = request.getRequestDispatcher("/WEB-INF/jsps/saisie.jsp");
			rd.forward(request, response);
		}
	}

}
