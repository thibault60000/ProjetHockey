package projet_tetris;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import SResultSet.SerializedResultSet;

public class ServletMajScore extends HttpServlet {

	private DataSource ds;
	Connection BD;
	SerializedResultSet sresultat;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try{
			ObjectInputStream entree = new ObjectInputStream(request.getInputStream());
			 ObjectOutputStream sortie = new ObjectOutputStream(response.getOutputStream());
			 this.sresultat = ExecuterRequete();
			 sortie.writeObject(sresultat);
		}
		 catch (Exception ex){
		      System.out.println("Erreur d'ex�cution de la requ�te SQL (doPost) : " + ex);
		 }       
	}
	
	
	public SerializedResultSet ExecuterRequete()
	{
		try
		{
			// Exécution de la requête
			BD=ds.getConnection();
			Statement s = BD.createStatement();
			ResultSet r = s.executeQuery("select * from top_score order by score desc limit 5");
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
				System.out.println("Erreur d'exécution de la requête SQL \n"+ex);
				return null;
		}
	}
	
	
	public void init() throws ServletException{
	    try{
	      Context initCtx = new InitialContext();
	      System.out.println("lookup de env");
	      Context envCtx = (Context)initCtx.lookup("java:comp/env");
	      System.out.println("lookup de base_test");
	      this.ds = ((DataSource)envCtx.lookup("base_test"));
	    }
	    catch (Exception er){
	      System.out.println("Erreur de chargement du contexte " + er);
	    }
	  }
	
}
