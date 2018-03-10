package applet;

import java.applet.Applet;
import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.Label;
import java.awt.event.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import SResultSet.SerializedResultSet;
import web.Connexion;

public class CreateAWTButtonExample extends Applet implements ActionListener{
	
	Label labelIdentifiant = new Label();
	Label labelPassword = new Label();
	TextField identifiant = new TextField(40);
	TextField password = new TextField(40);
	Button b = new Button("Se connecter");
	
    public void init(){
    	labelIdentifiant.setText("Identifiant : ");
    	labelPassword.setText("Mot de passe : ");
    	setLayout(new GridBagLayout());
    	GridBagConstraints gbc = new GridBagConstraints();
    	gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        add(labelIdentifiant, gbc);
        gbc.gridx = 1;
        add(identifiant, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        add(labelPassword, gbc);
        gbc.gridx = 1;
        add(password, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        add(b, gbc);
        b.addActionListener(this);
        //b.addActionListener(checkLogin(identifiant.getText(),password.getText()));
    }
    
    public void actionPerformed(ActionEvent evt) 
    {
    	 if (evt.getSource() == b){
    		 checkLogin(identifiant.getText(),password.getText());
    	 }
    }

	private ActionListener checkLogin(String id, String mdp) {
		try{
			System.out.println("1");
			URL url=new URL("http://localhost:8080/ProjetHockeyWeb/appletLogin");
			System.out.println("2");
			URLConnection connexion=url.openConnection();
			System.out.println("3");
			connexion.setDoOutput(true);
			System.out.println("4");
			ObjectOutputStream fluxsortie = new ObjectOutputStream(connexion.getOutputStream());
			System.out.println("5");
			fluxsortie.writeObject(id+"..."+mdp);
			System.out.println("6");
			ObjectInputStream fluxentree = new ObjectInputStream(connexion.getInputStream());
			System.out.println("7");
			System.out.println(fluxentree.readBoolean());
			
			boolean okOrKo = fluxentree.readBoolean();
			System.out.println("8");
			if(okOrKo==false){
				labelIdentifiant.setText("KO");
				labelPassword.setText("KO");
			}else{
				labelIdentifiant.setText("OK");
				labelPassword.setText("OK");
			}	
		}
		catch (Exception sql)
		{
			System.out.println("erreur "+sql);
		}
		return null;
		
	}

}
