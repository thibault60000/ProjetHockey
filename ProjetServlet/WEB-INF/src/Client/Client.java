package Client;

import javax.swing.JApplet;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JList;

import SResultSet.SerializedResultSet;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;

public class Client extends JApplet {
	JList listeResultat = null;
	private JTextField textNom;

	/**
	 * Create the applet.
	 */
	public Client() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNomDeLa = new JLabel("Nom de la personne recherch\u00E9e :");
		GridBagConstraints gbc_lblNomDeLa = new GridBagConstraints();
		gbc_lblNomDeLa.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomDeLa.anchor = GridBagConstraints.EAST;
		gbc_lblNomDeLa.gridx = 0;
		gbc_lblNomDeLa.gridy = 0;
		getContentPane().add(lblNomDeLa, gbc_lblNomDeLa);
		
		textNom = new JTextField();
		GridBagConstraints gbc_textNom = new GridBagConstraints();
		gbc_textNom.insets = new Insets(0, 0, 5, 0);
		gbc_textNom.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNom.gridx = 1;
		gbc_textNom.gridy = 0;
		getContentPane().add(textNom, gbc_textNom);
		textNom.setColumns(10);
		
		JButton boutonRechercher = new JButton("Rechercher\r\n");
		boutonRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Rechercher();
			}
		});
		GridBagConstraints gbc_boutonRechercher = new GridBagConstraints();
		gbc_boutonRechercher.anchor = GridBagConstraints.EAST;
		gbc_boutonRechercher.insets = new Insets(0, 0, 5, 5);
		gbc_boutonRechercher.gridx = 0;
		gbc_boutonRechercher.gridy = 1;
		getContentPane().add(boutonRechercher, gbc_boutonRechercher);
		
		listeResultat = new JList();
		GridBagConstraints gbc_listeResultat = new GridBagConstraints();
		gbc_listeResultat.gridwidth = 2;
		gbc_listeResultat.insets = new Insets(0, 0, 0, 5);
		gbc_listeResultat.fill = GridBagConstraints.BOTH;
		gbc_listeResultat.gridx = 0;
		gbc_listeResultat.gridy = 2;
		getContentPane().add(listeResultat, gbc_listeResultat);

	}

	protected void Rechercher() {
		String res;
		String nom=textNom.getText();
		try
		{
			// Connexion à la servlet
			URL url=new URL("http://localhost:8080/ProjetServlet/requete");
			URLConnection connexion=url.openConnection();
			connexion.setDoOutput(true);
			// Récupération du flux de sortie
			ObjectOutputStream fluxsortie = new ObjectOutputStream(connexion.getOutputStream());
			// Envoi du nom à rechercher
			fluxsortie.writeObject(nom);
			// Récupération du flux d’entrée
			ObjectInputStream fluxentree = new ObjectInputStream(connexion.getInputStream());
			// Récupération du résultat de la requête
			SerializedResultSet donnees=(SerializedResultSet) fluxentree.readObject();
			// affichage du résultat
			donnees.first();
			Vector contenu=new Vector();
			contenu.clear();
			listeResultat.setListData(contenu);
			for (int i=0; i<donnees.recordCount();i++)
			{
				res=donnees.getString("nom")+" "+donnees.getString("prenom");
				contenu.addElement(res);
				donnees.next();
			}
			if (donnees.recordCount()==0) 
				{
				res="Pas de personne correspondante";
				contenu.addElement(res);
				}
			listeResultat.setListData(contenu);
			
		}
		catch (Exception sql)
		{
			System.out.println("erreur "+sql);
		}
		
	}

}
