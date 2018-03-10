package projet_tetris;

import java.awt.*;
import javax.swing.*;

/**
 * <p>Titre : PanneauProchainePiece</p>
 * <p>Description : Classe de la cuve où seront afficher
 *                  les prochaines pièces à utiliser par le joueur</p>
 * <p>Copyright : Copyright (c) 2004</p>
 * <p>Société : </p>
 * @author Cédric Montillot et Djilali Maghraoui
 * @version 1.0
 */

public class PanneauProchainePiece extends JPanel{
  // Nombre de colonnes et de lignes
  int Lignes = 5;
  int Colonnes = 5;
  // Taille d'une case dans l'affichage du tableau tetris
  int TailleCarre = 20;
  // Tableau d'étiquettes à ajouter dans le panel pour afficher les pièces
  JLabel Panneau[][] = new JLabel[Lignes][Colonnes];
  // Layout du panel pour afficher les JLabel
  GridBagLayout LayoutPanneauProchainePiece = new GridBagLayout();
  // Tableau des couleurs utilisées pour peindre les cases du Tetris
  Color Couleurs[] = new Color[8];


  // Constructeur de la classe
  public PanneauProchainePiece() {
    try {
      // Appel de la méthode d'initialisation du panneau
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
   }



  // Méthode d'initialisation du panneau
  void jbInit() throws Exception {
     // Initialisation du tableau des couleurs
     Couleurs[0] = Color.BLACK;
     Couleurs[1] = Color.BLUE;
     Couleurs[2] = Color.RED;
     Couleurs[3] = Color.GREEN;
     Couleurs[4] = Color.CYAN;
     Couleurs[5] = Color.YELLOW;
     Couleurs[6] = Color.MAGENTA;
     Couleurs[7] = Color.ORANGE;
     // Initialisation "générale" du panneau
     this.setLayout(LayoutPanneauProchainePiece);
     this.setBackground(Color.black);
     this.setBorder(null);
     this.setSize(TailleCarre * Colonnes,TailleCarre * Lignes);
     this.setMaximumSize(new Dimension(TailleCarre * Colonnes,TailleCarre * Lignes));
     this.setMinimumSize(new Dimension(TailleCarre * Colonnes,TailleCarre * Lignes));
     this.setPreferredSize(new Dimension(TailleCarre * Colonnes,TailleCarre * Lignes));
     // Initialisation de chacune des cases constituant le panneau
     for(int i=0;i<Lignes;i++)
     {
       for(int j=0;j<Colonnes;j++)
       {
         Panneau[i][j] = new JLabel();
         Panneau[i][j].setBorder(BorderFactory.createEtchedBorder());
         Panneau[i][j].setBackground(Color.BLACK);
         Panneau[i][j].setOpaque(true);
         Panneau[i][j].setHorizontalAlignment(SwingConstants.CENTER);
         Panneau[i][j].setSize(TailleCarre,TailleCarre);
         Panneau[i][j].setMaximumSize(new Dimension(TailleCarre, TailleCarre));
         Panneau[i][j].setMinimumSize(new Dimension(TailleCarre, TailleCarre));
         Panneau[i][j].setPreferredSize(new Dimension(TailleCarre, TailleCarre));
         this.add(Panneau[i][j], new GridBagConstraints(j,i,1,1,0.0,0.0,GridBagConstraints.CENTER,
                      GridBagConstraints.NONE,new Insets(0,0,0,0),0,0));
       }
     }
   }



   // Méthode permettant d'effacer le contenu du panneau
   public void VidePanneau()
   {
     // On repaint toutes les cases du panneau en noir
     for(int i=0;i<Lignes;i++)
     {
       for (int j = 0; j < Colonnes; j++)
         Panneau[i][j].setBackground(Color.BLACK);
     }
   }



  // Méthode permettant l'affichage de la prochaine pièce à apparaître
   public void AfficheNextPiece(Piece P)
   {
      // On vide le contenu du panneau
      VidePanneau();
      // Selon le type de la pièce et sa couleur, on paint les cases
      // nécessaires pour représenter la pièce
      switch(P.TypePiece)
      {
        case 1: Panneau[1][2].setBackground(Couleurs[P.getCouleur()]);
                Panneau[2][2].setBackground(Couleurs[P.getCouleur()]);
                Panneau[3][2].setBackground(Couleurs[P.getCouleur()]);
                Panneau[4][2].setBackground(Couleurs[P.getCouleur()]);
                break;
        case 2: Panneau[1][1].setBackground(Couleurs[P.getCouleur()]);
                Panneau[1][2].setBackground(Couleurs[P.getCouleur()]);
                Panneau[2][2].setBackground(Couleurs[P.getCouleur()]);
                Panneau[2][3].setBackground(Couleurs[P.getCouleur()]);
                break;
        case 3: Panneau[1][3].setBackground(Couleurs[P.getCouleur()]);
                Panneau[1][2].setBackground(Couleurs[P.getCouleur()]);
                Panneau[2][2].setBackground(Couleurs[P.getCouleur()]);
                Panneau[2][1].setBackground(Couleurs[P.getCouleur()]);
                break;
        case 4: Panneau[2][1].setBackground(Couleurs[P.getCouleur()]);
                Panneau[2][2].setBackground(Couleurs[P.getCouleur()]);
                Panneau[2][3].setBackground(Couleurs[P.getCouleur()]);
                Panneau[1][2].setBackground(Couleurs[P.getCouleur()]);
                break;
        case 5: Panneau[1][1].setBackground(Couleurs[P.getCouleur()]);
                Panneau[1][2].setBackground(Couleurs[P.getCouleur()]);
                Panneau[2][2].setBackground(Couleurs[P.getCouleur()]);
                Panneau[3][2].setBackground(Couleurs[P.getCouleur()]);
                break;
        case 6: Panneau[1][3].setBackground(Couleurs[P.getCouleur()]);
                Panneau[1][2].setBackground(Couleurs[P.getCouleur()]);
                Panneau[2][2].setBackground(Couleurs[P.getCouleur()]);
                Panneau[3][2].setBackground(Couleurs[P.getCouleur()]);
                break;
        case 7: Panneau[1][1].setBackground(Couleurs[P.getCouleur()]);
                Panneau[1][2].setBackground(Couleurs[P.getCouleur()]);
                Panneau[2][1].setBackground(Couleurs[P.getCouleur()]);
                Panneau[2][2].setBackground(Couleurs[P.getCouleur()]);
                break;
      }
   }



   // Autres méthodes de la classe
   public int getNbLignes(){return Lignes;}
   public int getNbColonnes(){return Colonnes;}
   public int getTailleCarre(){return TailleCarre;}
   public void setNbLignes(int nouv){Lignes=nouv;}
   public void setNbColonnes(int nouv){Colonnes=nouv;}
   public void setTailleCarre(int nouv){TailleCarre=nouv;}
}