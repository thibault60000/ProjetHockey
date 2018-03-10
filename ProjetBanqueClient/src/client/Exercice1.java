package client;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JApplet;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.security.Security;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;

import org.jboss.sasl.JBossSaslProvider;

import am.CalculRemote;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Exercice1 extends JApplet {
	static {
        Security.addProvider(new JBossSaslProvider());
    }
	JList listeResultat = null;

	/**
	 * Create the applet.
	 */
	public Exercice1() {
		getContentPane().setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 175, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Somme emprunt\u00E9e : ");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		final JTextField somme = new JTextField();
		GridBagConstraints gbc_somme = new GridBagConstraints();
		gbc_somme.insets = new Insets(0, 0, 5, 5);
		gbc_somme.fill = GridBagConstraints.HORIZONTAL;
		gbc_somme.gridx = 2;
		gbc_somme.gridy = 1;
		getContentPane().add(somme, gbc_somme);
		somme.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u20AC");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 3;
		gbc_lblNewLabel_1.gridy = 1;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblDateDeDbut = new JLabel("Date de d\u00E9but de remboursement :");
		GridBagConstraints gbc_lblDateDeDbut = new GridBagConstraints();
		gbc_lblDateDeDbut.anchor = GridBagConstraints.EAST;
		gbc_lblDateDeDbut.insets = new Insets(0, 0, 5, 5);
		gbc_lblDateDeDbut.gridx = 1;
		gbc_lblDateDeDbut.gridy = 2;
		getContentPane().add(lblDateDeDbut, gbc_lblDateDeDbut);
		
		final JComboBox mois = new JComboBox();
		mois.setModel(new DefaultComboBoxModel(new String[] {"Janvier", "F\u00E9vrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "D\u00E9cembre"}));
		GridBagConstraints gbc_mois = new GridBagConstraints();
		gbc_mois.insets = new Insets(0, 0, 5, 5);
		gbc_mois.fill = GridBagConstraints.HORIZONTAL;
		gbc_mois.gridx = 2;
		gbc_mois.gridy = 2;
		getContentPane().add(mois, gbc_mois);
		
		final JComboBox annee = new JComboBox();
		annee.setModel(new DefaultComboBoxModel(new String[] {"2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050"}));
		GridBagConstraints gbc_annee = new GridBagConstraints();
		gbc_annee.insets = new Insets(0, 0, 5, 5);
		gbc_annee.fill = GridBagConstraints.HORIZONTAL;
		gbc_annee.gridx = 2;
		gbc_annee.gridy = 3;
		getContentPane().add(annee, gbc_annee);
		
		JLabel lblDureDuPrt = new JLabel("Dur\u00E9e du pr\u00EAt :");
		GridBagConstraints gbc_lblDureDuPrt = new GridBagConstraints();
		gbc_lblDureDuPrt.insets = new Insets(0, 0, 5, 5);
		gbc_lblDureDuPrt.anchor = GridBagConstraints.EAST;
		gbc_lblDureDuPrt.gridx = 1;
		gbc_lblDureDuPrt.gridy = 4;
		getContentPane().add(lblDureDuPrt, gbc_lblDureDuPrt);
		
		final JComboBox duree = new JComboBox();
		duree.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50"}));
		GridBagConstraints gbc_duree = new GridBagConstraints();
		gbc_duree.insets = new Insets(0, 0, 5, 5);
		gbc_duree.fill = GridBagConstraints.HORIZONTAL;
		gbc_duree.gridx = 2;
		gbc_duree.gridy = 4;
		getContentPane().add(duree, gbc_duree);
		
		JLabel lblAns = new JLabel(" ans");
		GridBagConstraints gbc_lblAns = new GridBagConstraints();
		gbc_lblAns.insets = new Insets(0, 0, 5, 5);
		gbc_lblAns.gridx = 3;
		gbc_lblAns.gridy = 4;
		getContentPane().add(lblAns, gbc_lblAns);
		
		JLabel lblTauxAnnuel = new JLabel("Taux annuel :");
		GridBagConstraints gbc_lblTauxAnnuel = new GridBagConstraints();
		gbc_lblTauxAnnuel.insets = new Insets(0, 0, 5, 5);
		gbc_lblTauxAnnuel.anchor = GridBagConstraints.EAST;
		gbc_lblTauxAnnuel.gridx = 1;
		gbc_lblTauxAnnuel.gridy = 5;
		getContentPane().add(lblTauxAnnuel, gbc_lblTauxAnnuel);
		
		final JTextField taux = new JTextField();
		GridBagConstraints gbc_taux = new GridBagConstraints();
		gbc_taux.anchor = GridBagConstraints.NORTH;
		gbc_taux.insets = new Insets(0, 0, 5, 5);
		gbc_taux.fill = GridBagConstraints.HORIZONTAL;
		gbc_taux.gridx = 2;
		gbc_taux.gridy = 5;
		getContentPane().add(taux, gbc_taux);
		taux.setColumns(10);
		
		JLabel label = new JLabel("%");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 3;
		gbc_label.gridy = 5;
		getContentPane().add(label, gbc_label);
		
		JButton calculer = new JButton("Calculer");
		calculer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<String> emprunt = new ArrayList<String>();
				CalculRemote calculBean = null;
				try {
					calculBean = lookupRemoteStatelessCalcul();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				listeResultat.clearSelection();
				emprunt = calculBean.calculer(Double.parseDouble(somme.getText()), mois.getSelectedItem().toString(), Integer.parseInt(annee.getSelectedItem().toString()), Integer.parseInt(duree.getSelectedItem().toString()), Double.parseDouble(taux.getText()));
				Vector<String> contenu=new Vector<String>();
				contenu.clear();
				for(String str : emprunt){
					contenu.addElement(str);
				}
				listeResultat.setListData(contenu);
			}
		});
		GridBagConstraints gbc_calculer = new GridBagConstraints();
		gbc_calculer.insets = new Insets(0, 0, 5, 5);
		gbc_calculer.anchor = GridBagConstraints.EAST;
		gbc_calculer.gridx = 1;
		gbc_calculer.gridy = 6;
		getContentPane().add(calculer, gbc_calculer);
		
		listeResultat = new JList();
		GridBagConstraints gbc_listeResultat = new GridBagConstraints();
		gbc_listeResultat.gridheight = 2;
		gbc_listeResultat.gridwidth = 5;
		gbc_listeResultat.insets = new Insets(0, 0, 5, 0);
		gbc_listeResultat.fill = GridBagConstraints.BOTH;
		gbc_listeResultat.gridx = 0;
		gbc_listeResultat.gridy = 7;
		getContentPane().add(listeResultat, gbc_listeResultat);
	}
	
	// Connexion au serveur et lookup du bean
    private static CalculRemote lookupRemoteStatelessCalcul() throws NamingException {
  	CalculRemote remote=null;
      try {
          Properties jndiProps = new Properties();
          jndiProps.setProperty(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
          InitialContext ctx = new InitialContext(jndiProps); 
          remote = (CalculRemote) ctx.lookup("ejb:/ProjetBanque/Calcul!am.CalculRemote");
      	} catch (Exception e) {
          e.printStackTrace();
      		}    	
      return remote;
  }

}
