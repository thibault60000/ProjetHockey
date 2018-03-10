package projet_tetris;

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

public class ServletInsertScore extends HttpServlet {
	
	private DataSource ds;
	Connection BD;
	
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try{
			ObjectInputStream entree = new ObjectInputStream(request.getInputStream());
			Joueur new_joueur = null;
			new_joueur=(Joueur)entree.readObject();
			ObjectOutputStream sortie = new ObjectOutputStream(response.getOutputStream()); 
			BD=ds.getConnection();
			Statement s = BD.createStatement();
			System.out.println(new_joueur.getNom());
			System.out.println(new_joueur.getLevel());
			System.out.println(new_joueur.getScore());
			s.executeUpdate("INSERT INTO `top_score`(`id`, `nom`, `score`, `level`) VALUES (null , '"+new_joueur.getNom()+"' , "+new_joueur.getScore()+", "+new_joueur.getLevel()+")");
			sortie.writeObject(null);
		}
		 catch (Exception ex){
		      System.out.println("Erreur d'ex�cution de la requ�te SQL (insert) : " + ex);
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
