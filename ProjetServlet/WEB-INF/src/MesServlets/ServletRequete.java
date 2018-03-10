package MesServlets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import SResultSet.SerializedResultSet;

public class ServletRequete extends HttpServlet {
	private DataSource ds;
	Connection BD;
	String nomPersonne;
	SerializedResultSet sresultat;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
											throws ServletException, IOException {
		try {
			// R�cup�ration du flux d'entr�e envoy� par l'applet
			ObjectInputStream entree=new ObjectInputStream(request.getInputStream());
			nomPersonne=(String)entree.readObject();
			// Pr�paration du flux de sortie
			ObjectOutputStream sortie=new ObjectOutputStream(response.getOutputStream());
			// Execution de la requ�te
			sresultat=ExecuterRequete();
			sresultat.first();
			// Envoi du r�sultat au client
			sortie.writeObject(sresultat);
		} catch (Exception ex) {
		System.out.println("Erreur d'ex�cution de la requ�te SQL : "+ex);
		}
	}

	public SerializedResultSet ExecuterRequete()
	{
		try
		{
			// Ex�cution de la requ�te
			BD=ds.getConnection();
			Statement s = BD.createStatement();
			ResultSet r = s.executeQuery("select * from personne where nom= '"+nomPersonne+"'");
			// Transformation du ResultSet en sResultSet
			java.sql.ResultSetMetaData columnNames = r.getMetaData();
			SResultSet.SerializedResultSet sResultSet = new SResultSet.SerializedResultSet();
		for (int i = 1; i <= columnNames.getColumnCount(); i++) {
			sResultSet.addColumn(columnNames.getColumnName(i), i);
			}
			while (r.next()) {
				for (int column = 1; column <= columnNames.getColumnCount(); column++) {
				sResultSet.addColumnData(column, r.getObject(column));
				}
			}

			r.close();
			s.close();
			BD.close();
			s = null;
			r = null;
			return sResultSet;
		}
		catch (java.sql.SQLException ex) {
				System.out.println("Erreur d'ex�cution de la requ�te SQL \n"+ex);
				return null;
		}
	}
	
	public void init() throws ServletException {
		try {
			Context initCtx = new InitialContext();
			System.out.println("lookup de env");
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			System.out.println("lookup de base_test");
			ds=(DataSource) envCtx.lookup("base_test");
		}
		catch(Exception er) {
			System.out.println("Erreur de chargement du contexte " + er);
		}
	}
}
