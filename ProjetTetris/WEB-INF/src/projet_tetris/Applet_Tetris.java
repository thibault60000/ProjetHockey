package projet_tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 * <p>Titre : Applet_Tetris</p>
 * <p>Description : Classe de l'Applet</p>
 * <p>Copyright : Copyright (c) 2004</p>
 * <p>Soci�t� : </p>
 * @author C�dric Montillot et Djilali Maghraoui
 * @version 1.0
 */

public class Applet_Tetris extends JApplet implements Runnable{
  // Panneau qui recouvre tout l'applet
  JPanel FenetrePrincipale = new JPanel();
  // Etiquette destin�e � afficher "Score" sur l'interface
  JLabel LabelScore = new JLabel();
  // Etiquette destin�e � afficher "Lignes" sur l'interface
  JLabel LabelLigne = new JLabel();
  // Layout du panneau principal
  GridBagLayout LayoutPanneauPrincipal = new GridBagLayout();
  // Etiquette destin�e � afficher le score du joueur
  JLabel TextScore = new JLabel();
  // Etiquette destin�e � afficher le nombre de lignes faites par le joueur
  JLabel TextLignes = new JLabel();
  // Etiquette destin�e � afficher le titre du jeu: "Tetris"
  JLabel LabelTitre = new JLabel();
  // Bouton permettant de commencer une nouvelle partie
  JButton BtnNouvPartie = new JButton();
  // Bouton permettant de configurer le jeu
  JButton BtnOptions = new JButton();
  // Bouton permettant de mettre en pause la partie
  JButton BtnPause = new JButton();
  // Bouton permettant de cousulter les meilleurs scores
  JButton BtnScore = new JButton();
  // Etiquette destin� � afficher "Prochaine Pi�ce"
  JLabel LabelNextPiece = new JLabel();
  // La cuve qui contient les pi�ces qui descendent
  PanneauTetris PT = new PanneauTetris();
  // La cuve qui contient la prochaine pi�ce � utiliser
  PanneauProchainePiece PPP = new PanneauProchainePiece();
  // La pi�ce qui va descendre dans le PanneauTetris
  Piece PCourant;
  // La prochaine pi�ce qui appara�tra dans le PanneauTetris
  Piece NextPiece;
  // Bordure autour de la cuve contenant les pi�ces qui descendent
  Border BordurePanneauTetris;
  // Bordure autour de la cuve contenant la prochaine pi�ce � utiliser
  Border BordurePanneauProchainePiece;
  // Processus de gestion de descente des pi�ces
  Thread GestionJeu=null;
  // Panneau des meilleurs scores
  PanneauScore PS = new PanneauScore();



  // Initialisation de l'applet
  public void init() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }



  // Initialiser le composant
  private void jbInit() throws Exception {
    // Taille de l'applet
    this.setSize(new Dimension(500, 500));

    // Int�gration du panel principal dans l'applet
    FenetrePrincipale.setLayout(LayoutPanneauPrincipal);
    FenetrePrincipale.setMinimumSize(new Dimension(500, 500));
    FenetrePrincipale.setMaximumSize(new Dimension(500, 500));
    LabelLigne.setText("Lignes");
    LabelLigne.setHorizontalTextPosition(SwingConstants.CENTER);
    LabelLigne.setHorizontalAlignment(SwingConstants.CENTER);
    LabelLigne.setFont(new java.awt.Font("Dialog", 1, 12));
    LabelLigne.setToolTipText("");
    TextLignes.setVerticalTextPosition(SwingConstants.BOTTOM);
    TextLignes.setVerticalAlignment(SwingConstants.CENTER);
    TextLignes.setText("0");
    TextLignes.setIconTextGap(4);
    TextLignes.setHorizontalTextPosition(SwingConstants.CENTER);
    TextLignes.setHorizontalAlignment(SwingConstants.CENTER);
    TextLignes.setPreferredSize(new Dimension(105, 25));
    TextLignes.setMinimumSize(new Dimension(110, 25));
    TextLignes.setMaximumSize(new Dimension(110, 25));
    TextLignes.setDoubleBuffered(false);
    TextLignes.setBorder(BorderFactory.createEtchedBorder());
    TextLignes.setAlignmentY((float) 0.0);
    TextLignes.setFont(new java.awt.Font("Dialog", 0, 12));
    BtnNouvPartie.addActionListener(new Applet_Tetris_BtnNouvPartie_actionAdapter(this));
    BtnPause.addActionListener(new Applet_Tetris_BtnPause_actionAdapter(this));

    // Int�gration du PanneauTetris dans l'applet
    BordurePanneauTetris = BorderFactory.createLineBorder(Color.red,2);
    PT.setBorder(BordurePanneauTetris);
    PT.setBackground(Color.black);
    PT.requestFocus();

    // Int�gration du PanneauProchainePiece dans l'applet
   BordurePanneauProchainePiece = BorderFactory.createLineBorder(Color.blue,2);
   PPP.setBorder(BordurePanneauProchainePiece);
   PPP.setBackground(Color.black);
   BtnOptions.addActionListener(new Applet_Tetris_BtnOptions_actionAdapter(this));
    BtnScore.addActionListener(new Applet_Tetris_BtnScore_actionAdapter(this));
    FenetrePrincipale.add(PT, new GridBagConstraints(0, 1, 1, 10, 0.0, 0.0
            ,GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(27, 48, 38, 28), 9, 5));
    FenetrePrincipale.add(PPP,      new GridBagConstraints(1, 10, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 44, 0), 9, 11));
    FenetrePrincipale.add(BtnOptions, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    FenetrePrincipale.add(BtnPause, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    FenetrePrincipale.add(BtnScore, new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    FenetrePrincipale.add(LabelScore, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 25, 0, 25), 3, 10));
    FenetrePrincipale.add(LabelTitre, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 7, 0));
    FenetrePrincipale.add(LabelLigne, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    FenetrePrincipale.add(BtnNouvPartie, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0
            ,GridBagConstraints.SOUTH, GridBagConstraints.NONE, new Insets(16, 4, 6, 6), 0, 0));
    FenetrePrincipale.add(LabelNextPiece, new GridBagConstraints(1, 9, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(20, 0, 0, 0), 0, 7));
    FenetrePrincipale.add(TextLignes, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0
            ,GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(1, 0, 10, 0), 6, 5));
    FenetrePrincipale.add(TextScore, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(-5, 44, 18, 46), 4, 5));

   // Int�gration des autres composants dans l'applet
   LabelScore.setFont(new java.awt.Font("Dialog", 1, 12));
   LabelScore.setHorizontalAlignment(SwingConstants.LEFT);
   LabelScore.setHorizontalTextPosition(SwingConstants.CENTER);
   LabelScore.setText("Score");
   TextScore.setFont(new java.awt.Font("Dialog", 0, 12));
   TextScore.setAlignmentY((float) 0.0);
   TextScore.setBorder(BorderFactory.createEtchedBorder());
   TextScore.setDoubleBuffered(false);
   TextScore.setMaximumSize(new Dimension(110, 25));
   TextScore.setMinimumSize(new Dimension(110, 25));
   TextScore.setPreferredSize(new Dimension(105, 25));
   TextScore.setHorizontalAlignment(SwingConstants.CENTER);
   TextScore.setHorizontalTextPosition(SwingConstants.CENTER);
   TextScore.setIconTextGap(4);
   TextScore.setText("0");
   TextScore.setVerticalAlignment(SwingConstants.CENTER);
   TextScore.setVerticalTextPosition(SwingConstants.BOTTOM);
   LabelTitre.setFont(new java.awt.Font("Dialog", 1, 15));
   LabelTitre.setForeground(Color.red);
   LabelTitre.setText("SUPER TETRIS");
   BtnNouvPartie.setMaximumSize(new Dimension(115, 25));
   BtnNouvPartie.setMinimumSize(new Dimension(115, 25));
   BtnNouvPartie.setPreferredSize(new Dimension(115, 25));
   BtnNouvPartie.setText("Nouvelle Partie");
   BtnOptions.setMaximumSize(new Dimension(115, 25));
   BtnOptions.setMinimumSize(new Dimension(115, 25));
   BtnOptions.setPreferredSize(new Dimension(115, 25));
   BtnOptions.setText("Options");
   BtnPause.setMaximumSize(new Dimension(115, 25));
   BtnPause.setMinimumSize(new Dimension(115, 25));
   BtnPause.setPreferredSize(new Dimension(115, 25));
   BtnPause.setText("Pause");
   BtnScore.setPreferredSize(new Dimension(115, 25));
   BtnScore.setText("Meilleurs Scores");
   LabelNextPiece.setFont(new java.awt.Font("Dialog", 1, 12));
   LabelNextPiece.setText("Prochaine Pi�ce");
   this.getContentPane().add(FenetrePrincipale, BorderLayout.NORTH);
   PS.setLocation(250,200);
   BtnPause.setEnabled(false);
   BtnNouvPartie.setEnabled(false);

   // Cr�ation et affichage de la pi�ce qui sera la 1�re � descendre
   // dans le PanneauTetris quand le joueur cliquera sur le bouton
   // Nouvelle Partie
   NextPiece = new Piece();
   PPP.AfficheNextPiece(NextPiece);
  }



  // M�thode start de l'applet
  public void start()
  {
    // Affichage de "l'animation" d'introduction du Super Tetris
   PT.AnimIntroduction();
   BtnNouvPartie.setEnabled(true);
  }



  // M�thode stop de l'applet
  public void stop()
  {
    GestionJeu = null;
  }



  // M�thode d�crivant le d�roulement d'une partie de t�tris
  public void run()
  {
    while(GestionJeu!=null)
    {
      try
      {
        // Mise � jour dans le PanneauTetris de la pi�ce qui descend
        PT.setPiece(NextPiece);
        // Initialisation de la ligne courante de la pi�ce
        PT.setLigneCourante(-1);
        // Initialisation de la courante de la pi�ce
        PT.setColonneCourante(Math.round(PT.Colonnes/2) - 1);
        // Cr�ation de la prochaine pi�ce � descendre
        NextPiece = new Piece();
        // Affichage de cette prochaine pi�ce dans le PanneauProchainePiece
        PPP.AfficheNextPiece(NextPiece);
        // Initialisation de la vitesse courante de descente des pi�ces
        PT.setVitesseCourante(PT.getVitesseNormale());
        // Tant que la pi�ce qui descend n'est pas bloqu�e et tant que le
        // joueur n'a pas perdu
        while(!PT.EstBloque() && !PT.EstPerdu())
        {
          // On fait descendre la pi�ce
          PT.Descendre();
          PT.requestFocus();
          // On temporise selon vitesse courante de descente
          Thread.sleep(PT.getVitesseCourante());
          // Permet de mettre le jeu en mode pause tant que le joueur n'a
          // pas cliqu� sur le bouton pour reprendre la partie
          synchronized(this) {
            while(BtnPause.getText() != "Pause")
            {
              // Affichage du mode Pause
              PT.AffichePause();
              BtnPause.setEnabled(true);
              BtnNouvPartie.setEnabled(true);
              wait();
            }
          }
        }
      }
      catch (InterruptedException e) {}
      // Si le joueur a perdu la partie
      if(PT.EstPerdu())
      {
        // Les boutons Pause et Nouvelle Partie sont gris�s
        BtnPause.setEnabled(false);
        BtnNouvPartie.setEnabled(false);
        // Affichage Game Over
        PT.AfficheGameOver();
        // Gestion des Scores
        PS.GestionScore(PT.getNiveau(),PT.getScore());
        // On d�grise le bouton Nouvelle Partie
        BtnNouvPartie.setEnabled(true);
        // On stop le processus
        GestionJeu.stop();
        // Initialisation du PanneauTetris
        PT.InitPanneau();
      }
      else
      {
        // on enl�ve les lignes qui sont compl�tes
        PT.EnleverLignesPleines();
        // Mise � jour du nombre de lignes compl�t�es sur l'interface
        TextLignes.setText(Integer.toString(PT.getNbLignesComplet()));
        // Mise � jour du score du joueur sur l'interface
        TextScore.setText(Integer.toString(PT.getScore()));
      }
    }
  }



  // M�thode ex�cut�e lorsque le joueur clique sur le bouton
  // "Nouvelle Partie"
  void BtnNouvPartie_actionPerformed(ActionEvent e) {
    // Si une partie (ou processus) est en cours
    if(GestionJeu != null)
       // Arr�t de la partie
       GestionJeu.stop();
    // Cr�ation d'une nouvelle partie
    GestionJeu = new Thread(this);
    // R�initialisation de l'interface et des panneaux
    PT.InitPanneau();
    PPP.VidePanneau();
    // Initialisation du bouton Pause
    BtnPause.setText("Pause");
    BtnPause.setEnabled(true);
    // Si NextPiece n'existe pas, on va la cr�er
    if(NextPiece == null)
       NextPiece = new Piece();
    // Initialisation de nombre de lignes compl�t�es et du score
    TextLignes.setText("0");
    TextScore.setText("0");
    // D�but de la partie
    GestionJeu.start();
  }



  // M�thode ex�cut�e lorsque le joueur clique sur le bouton
  // "Pause"
  public synchronized void BtnPause_actionPerformed(ActionEvent e) {
    // Si une partie (ou processus) est en cours
    if(GestionJeu != null)
    {
      // Si le click a �t� fait pour mettre en pause la partie
      if(BtnPause.getText() == "Pause")
      {
         // On grise les boutons Pause et Nouvelle Partie tant que la
         // petite animation du mode Pause n'est pas termin�
         BtnPause.setEnabled(false);
         BtnNouvPartie.setEnabled(false);
         // On colore en Noir le PanneauTetris
         PT.ColoreNoir();
         // Le bouton affiche d�sormais "Reprendre"
         BtnPause.setText("Reprendre");
      }
      // Sinon c'est que le click a �t� fait pour reprendre la partie
      else
      {
         // On efface l'affichage du mode Pause
         PT.EffaceTexte();
         // On met � jour le PanneauTetris avec les pi�ces contenues dedans
         // avant la pause
         PT.MAJ_PanneauTetris();
         // On sort du mode pause et on reprend le cours de la partie
         notify();
         // Le bouton affiche d�sormais "Pause"
         BtnPause.setText("Pause");
      }
    }
  }



  // M�thode ex�cut�e lorsque le joueur clique sur le bouton
  // "Options"
  void BtnOptions_actionPerformed(ActionEvent e) {
  JOptionPane.showMessageDialog(this, "Aucune option pour le moment",
                                "Information",
                                JOptionPane.INFORMATION_MESSAGE);
  }



  // M�thode ex�cut�e lorsque le joueur clique sur le bouton
  // "Meilleurs Scores"
  void BtnScore_actionPerformed(ActionEvent e) {
     PS.setModal(true);
     PS.pack();
     PS.show();
     PS.MAJ_Panneau();
  }
}


class Applet_Tetris_BtnNouvPartie_actionAdapter implements java.awt.event.ActionListener {
  Applet_Tetris adaptee;

  Applet_Tetris_BtnNouvPartie_actionAdapter(Applet_Tetris adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.BtnNouvPartie_actionPerformed(e);
  }
}

class Applet_Tetris_BtnPause_actionAdapter implements java.awt.event.ActionListener {
  Applet_Tetris adaptee;

  Applet_Tetris_BtnPause_actionAdapter(Applet_Tetris adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.BtnPause_actionPerformed(e);
  }
}

class Applet_Tetris_BtnOptions_actionAdapter implements java.awt.event.ActionListener {
  Applet_Tetris adaptee;

  Applet_Tetris_BtnOptions_actionAdapter(Applet_Tetris adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.BtnOptions_actionPerformed(e);
  }
}

class Applet_Tetris_BtnScore_actionAdapter implements java.awt.event.ActionListener {
  Applet_Tetris adaptee;

  Applet_Tetris_BtnScore_actionAdapter(Applet_Tetris adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.BtnScore_actionPerformed(e);
  }
}
