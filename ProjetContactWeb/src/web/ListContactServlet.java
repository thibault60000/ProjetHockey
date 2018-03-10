package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejbAgenda.ContactManagerRemote;

/**
 * Servlet implementation class ListContactServlet
 */
@WebServlet("/ListContactServlet")
public class ListContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContactManagerRemote contactManagerRemote = EjbLocator.getLocator().getContactManager();
		request.setAttribute("listContacts", contactManagerRemote.listerContact());
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsps/listContact.jsp");
		rd.forward(request, response);
	}


}
