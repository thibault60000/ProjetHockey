package projet_tetris;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;


/**
 * <p>Titre : PanneauTetris</p>
 * <p>Description : Classe de la cuve ou vont descendre les pi�ces</p>
 * <p>Copyright : Copyright (c) 2004</p>
 * <p>Soci�t� : </p>
 * @author C�dric Montillot et Djilali Maghraoui
 * @version 1.0
 */


public class PanneauTetris extends JPanel implements KeyListener {
  // Nombre de colonnes et de lignes
  int Lignes = 20;
  int Colonnes = 12;
  // Piece qui descend dans le PanneauTetris
  Piece P;
  // Le coin gauche de la pi�ce qui descend
  int LigneCourante;
  int ColonneCourante;
  // Tableau du tetris gr�ce auquel on v�rifie la place des pi�ces
  int Tab[][] = new int[Lignes][Colonnes];
  // Taille d'une case dans l'affichage du tableau tetris
  int TailleCarre = 20;
  // Tableau d'�tiquettes � ajouter dans le panel pour visualiser les pi�ces
  JLabel Panneau[][] = new JLabel[Lignes][Colonnes];
  // Layout du panel pour afficher les JLabel
  GridBagLayout LayoutPanneauTetris = new GridBagLayout();
  // Tableau des couleurs utilis�es pour peindre les cases du Tetris
  Color Couleurs[] = new Color[8];
  // Vitesse de descente de pi�ces dans le Panneau
  int VitesseCourante;
  // Vitesse normale de descente des pi�ces dans le Panneau
  int VitesseNormale;
  // Vitesse acc�l�r�e de descente des pi�ces quand le joueur utilise la
  // fl�che bas du clavier
  int VitesseAcceleree = 10;
  // Nombre de lignes compl�t�es par le joueur
  int NbLignesComplet;
  // Score du joueur
  int Score;
  // Niveau atteint par le joueur, � chaque niveau atteint par le joueur,
  // la vitesse de descente des pi�ces s'acc�l�re mais le joueur gagne
  // plus de points en fonction des combinaisons r�alis�es
  int Niveau;


  // Constructeur de la classe
  public PanneauTetris() {
    try {
      // Appel de la m�thode d'initialisation du panneau
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }



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
    // Initialisation "g�n�rale" du panneau
    this.setLayout(LayoutPanneauTetris);
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
        Tab[i][j] = 0;
        Panneau[i][j] = new JLabel();
        Panneau[i][j].setBorder(BorderFactory.createEtchedBorder());
        Panneau[i][j].setBackground(Couleurs[0]);
        Panneau[i][j].setOpaque(true);
        Panneau[i][j].setHorizontalAlignment(SwingConstants.CENTER);
        Panneau[i][j].setSize(TailleCarre,TailleCarre);
        Panneau[i][j].setFont(new java.awt.Font("Dialog", 1, 13));
        Panneau[i][j].setForeground(Color.BLUE);
        Panneau[i][j].setMaximumSize(new Dimension(TailleCarre, TailleCarre));
        Panneau[i][j].setMinimumSize(new Dimension(TailleCarre, TailleCarre));
        Panneau[i][j].setPreferredSize(new Dimension(TailleCarre, TailleCarre));
        this.add(Panneau[i][j], new GridBagConstraints(j,i,1,1,0.0,0.0,GridBagConstraints.CENTER,
                     GridBagConstraints.NONE,new Insets(0,0,0,0),0,0));
      }
    }
    addKeyListener(this);
    requestFocus();
  }



  // M�thode permettant de colorer les cases du PanneauTetris en fonction
  // du tableau indiquant la position et la couleur des pi�ces
  public void MAJ_PanneauTetris()
  {
     // Pour chaque case du PanneauTetris
     for(int i=0;i<Lignes;i++)
       for(int j=0;j<Colonnes;j++)
          // On colorie la case selon la couleur repr�sent� par l'entier
          // contenu dans Tab[i][j]
          Panneau[i][j].setBackground(Couleurs[Tab[i][j]]);
  }



  // M�thode permettant de colorer en noire toutes les cases du
  // PanneauTetris quand le joueur choisit de clicker sur "Pause"
  public void ColoreNoir()
  {
    // Pour chaque case du PanneauTetris
    for(int i=0;i<Lignes;i++)
      for(int j=0;j<Colonnes;j++)
        // On colorie la case en noir
        Panneau[i][j].setBackground(Color.BLACK);
  }



  // M�thode permettant la r�initialisation du PanneauTetris
  public void InitPanneau()
  {
     // On efface le mode pause au cas o� le joueur clique sur "Nouvelle Partie"
     // pendant que sa partie actuelle est en mode pause
     EffaceTexte();
     // On colore en noir la PanneauTetris
     ColoreNoir();
     // On r�initialise le tableau Tab indiquant la position des pi�ces
     for(int i=0;i<Lignes;i++)
       for(int j=0;j<Colonnes;j++)
         // On colorie la case en noir
         Tab[i][j] = 0;
     // Initialisation de la vitesse de chute des pi�ces
     VitesseNormale = 1000;
     // Initialisation du nombre de lignes compl�t�es par le joueur
     NbLignesComplet = 0;
     // Initialisation du score
     Score = 0;
     // Initialisation du niveau de difficult� du jeu
     Niveau = 1;
  }



  // M�thode permettant d'afficher l'animation d'introduction du Super Tetris
  // dans le PanneauTetris
  public void AnimIntroduction()
  {
    int x,y;
    // On d�finit une couleur al�atoire
    Random r = new Random();
    for(int i=0;i<10;i++)
    {
      // On d�finit un num�ro de ligne et de colonne al�atoire
      x = r.nextInt(Lignes);
      y = r.nextInt(Colonnes);
      Panneau[x][y].setForeground(Color.WHITE);
      Panneau[x][y].setText("S");
      try{Thread.sleep(100);}catch(Exception e){e.printStackTrace();};
      Panneau[x][y].setText("");
      Panneau[x][y].setForeground(Color.BLACK);
    }
    Panneau[9][0].setText("S");
    Panneau[9][0].setForeground(Color.WHITE);

    for(int i=0;i<10;i++)
    {
      // On d�finit un num�ro de ligne et de colonne al�toire
      do{
        x = r.nextInt(Lignes);
        y = r.nextInt(Colonnes);
      }while(x== 9 && y == 0);
      Panneau[x][y].setForeground(Color.WHITE);
      Panneau[x][y].setText("U");
      try{Thread.sleep(100);}catch(Exception e){e.printStackTrace();};
      Panneau[x][y].setText("");
      Panneau[x][y].setForeground(Color.BLACK);
    }
    Panneau[9][1].setText("U");
    Panneau[9][1].setForeground(Color.WHITE);

    for(int i=0;i<10;i++)
    {
      // On d�finit un num�ro de ligne et de colonne al�toire
      do{
        x = r.nextInt(Lignes);
        y = r.nextInt(Colonnes);
      }while(x== 9 && y <=1 );
      Panneau[x][y].setForeground(Color.WHITE);
      Panneau[x][y].setText("P");
      try{Thread.sleep(100);}catch(Exception e){e.printStackTrace();};
      Panneau[x][y].setText("");
      Panneau[x][y].setForeground(Color.BLACK);
    }
    Panneau[9][2].setText("P");
    Panneau[9][2].setForeground(Color.WHITE);

    for(int i=0;i<10;i++)
    {
      // On d�finit un num�ro de ligne et de colonne al�toire
      do{
        x = r.nextInt(Lignes);
        y = r.nextInt(Colonnes);
      }while(x== 9 && y < 3);
      Panneau[x][y].setForeground(Color.WHITE);
      Panneau[x][y].setText("E");
      try{Thread.sleep(100);}catch(Exception e){e.printStackTrace();};
      Panneau[x][y].setText("");
      Panneau[x][y].setForeground(Color.BLACK);
    }
    Panneau[9][3].setText("E");
    Panneau[9][3].setForeground(Color.WHITE);

    for(int i=0;i<10;i++)
    {
      // On d�finit un num�ro de ligne et de colonne al�toire
      do{
        x = r.nextInt(Lignes);
        y = r.nextInt(Colonnes);
      }while(x== 9 && y < 4);
      Panneau[x][y].setForeground(Color.WHITE);
      Panneau[x][y].setText("R");
      try{Thread.sleep(100);}catch(Exception e){e.printStackTrace();};
      Panneau[x][y].setText("");
      Panneau[x][y].setForeground(Color.BLACK);
    }
    Panneau[9][4].setText("R");
    Panneau[9][4].setForeground(Color.WHITE);

    for(int i=0;i<10;i++)
    {
      // On d�finit un num�ro de ligne et de colonne al�toire
      do{
        x = r.nextInt(Lignes);
        y = r.nextInt(Colonnes);
      }while(x== 9 && y < 6);
      Panneau[x][y].setForeground(Color.WHITE);
      Panneau[x][y].setText("T");
      try{Thread.sleep(100);}catch(Exception e){e.printStackTrace();};
      Panneau[x][y].setText("");
      Panneau[x][y].setForeground(Color.BLACK);
    }
    Panneau[9][6].setText("T");
    Panneau[9][6].setForeground(Color.WHITE);

    for(int i=0;i<10;i++)
    {
      // On d�finit un num�ro de ligne et de colonne al�toire
      do{
        x = r.nextInt(Lignes);
        y = r.nextInt(Colonnes);
      }while(x== 9 && y < 8);
      Panneau[x][y].setForeground(Color.WHITE);
      Panneau[x][y].setText("E");
      try{Thread.sleep(100);}catch(Exception e){e.printStackTrace();};
      Panneau[x][y].setText("");
      Panneau[x][y].setForeground(Color.BLACK);
    }
    Panneau[9][7].setText("E");
    Panneau[9][7].setForeground(Color.WHITE);

    for(int i=0;i<10;i++)
    {
      // On d�finit un num�ro de ligne et de colonne al�toire
      do{
        x = r.nextInt(Lignes);
        y = r.nextInt(Colonnes);
      }while(x== 9 && y < 9);
      Panneau[x][y].setForeground(Color.WHITE);
      Panneau[x][y].setText("T");
      try{Thread.sleep(100);}catch(Exception e){e.printStackTrace();};
      Panneau[x][y].setText("");
      Panneau[x][y].setForeground(Color.BLACK);
    }
    Panneau[9][8].setText("T");
    Panneau[9][8].setForeground(Color.WHITE);

    for(int i=0;i<10;i++)
    {
      // On d�finit un num�ro de ligne et de colonne al�toire
      do{
        x = r.nextInt(Lignes);
        y = r.nextInt(Colonnes);
      }while(x== 9 && y < 10);
      Panneau[x][y].setForeground(Color.WHITE);
      Panneau[x][y].setText("R");
      try{Thread.sleep(100);}catch(Exception e){e.printStackTrace();};
      Panneau[x][y].setText("");
      Panneau[x][y].setForeground(Color.BLACK);
    }
    Panneau[9][9].setText("R");
    Panneau[9][9].setForeground(Color.WHITE);

    for(int i=0;i<10;i++)
    {
      // On d�finit un num�ro de ligne et de colonne al�toire
      do{
        x = r.nextInt(Lignes);
        y = r.nextInt(Colonnes);
      }while(x== 9 && y < 11);
      Panneau[x][y].setForeground(Color.WHITE);
      Panneau[x][y].setText("I");
      try{Thread.sleep(100);}catch(Exception e){e.printStackTrace();};
      Panneau[x][y].setText("");
      Panneau[x][y].setForeground(Color.BLACK);
    }
    Panneau[9][10].setText("I");
    Panneau[9][10].setForeground(Color.WHITE);

    for(int i=0;i<10;i++)
    {
      // On d�finit un num�ro de ligne et de colonne al�toire
      do{
        x = r.nextInt(Lignes);
        y = r.nextInt(Colonnes);
      }while(x== 9 && y < 12);
      Panneau[x][y].setForeground(Color.WHITE);
      Panneau[x][y].setText("S");
      try{Thread.sleep(100);}catch(Exception e){e.printStackTrace();};
      Panneau[x][y].setText("");
      Panneau[x][y].setForeground(Color.BLACK);
    }
    Panneau[9][11].setText("S");
    Panneau[9][11].setForeground(Color.WHITE);
  }



  // M�thode permettant d'afficher sur le PanneauTetris que la partie est
  // en mode pause
  public void AffichePause()
  {
      // Chute du mot PAUSE
      ChutePause(0,Math.round(Lignes/2));
      // Rebond du mot PAUSE
      RebondPause(Math.round(Lignes/2 - 2),Math.round(Lignes/2 - 7));
      // Rechute du mot PAUSE
      ChutePause(Math.round(Lignes/2 - 6),Math.round(Lignes/2));
      // Encore un rebond
      RebondPause(Math.round(Lignes/2 - 2),Math.round(Lignes/2 - 5));
      // etc....
      ChutePause(Math.round(Lignes/2 - 4),Math.round(Lignes/2));
      RebondPause(Math.round(Lignes/2 - 2),Math.round(Lignes/2 - 4));
      ChutePause(Math.round(Lignes/2 - 3),Math.round(Lignes/2));
      RebondPause(Math.round(Lignes/2 - 2),Math.round(Lignes/2 - 3));
      ChutePause(Math.round(Lignes/2 - 2),Math.round(Lignes/2));
      // Affichage fixe du mot PAUSE
      Panneau[Math.round(Lignes/2 - 1)][Math.round(Colonnes/2 - 3)].setText("P");
      Panneau[Math.round(Lignes/2 - 1)][Math.round(Colonnes/2 - 2)].setText("A");
      Panneau[Math.round(Lignes/2 - 1)][Math.round(Colonnes/2 - 1)].setText("U");
      Panneau[Math.round(Lignes/2 - 1)][Math.round(Colonnes/2)].setText("S");
      Panneau[Math.round(Lignes/2 - 1)][Math.round(Colonnes/2 + 1)].setText("E");
 }



 // M�thode permettant d'afficher sur le PanneauTetris que la partie est
 // termin�e et que le joueur a perdu
 public void AfficheGameOver()
 {
     // Remplissage des lignes petit � petit
     for(int i=Lignes-1;i>=0;i--)
     {
       for (int j = 0; j < Colonnes; j++)
         Panneau[i][j].setBackground(Color.LIGHT_GRAY);
       try{Thread.sleep(200);}catch(Exception e){e.printStackTrace();};
     }
     // On colores en noir le PanneauTetris
     ColoreNoir();
     // Chute du mot Game Over
     ChuteGameOver(0,Math.round(Lignes/2));
     // Rebond du mot Game Over
     RebondGameOver(Math.round(Lignes/2 - 2),Math.round(Lignes/2 - 7));
     // Rechute du mot Game Over
     ChuteGameOver(Math.round(Lignes/2 - 6),Math.round(Lignes/2));
     // Encore un rebond
     RebondGameOver(Math.round(Lignes/2 - 2),Math.round(Lignes/2 - 5));
     // etc....
     ChuteGameOver(Math.round(Lignes/2 - 4),Math.round(Lignes/2));
     RebondGameOver(Math.round(Lignes/2 - 2),Math.round(Lignes/2 - 4));
     ChuteGameOver(Math.round(Lignes/2 - 3),Math.round(Lignes/2));
     RebondGameOver(Math.round(Lignes/2 - 2),Math.round(Lignes/2 - 3));
     ChuteGameOver(Math.round(Lignes/2 - 2),Math.round(Lignes/2));

     // Affichage fixe du mot Game Over
     Panneau[Math.round(Lignes/2 - 1)][Math.round(Colonnes/2 - 4)].setText("G");
     Panneau[Math.round(Lignes/2 - 1)][Math.round(Colonnes/2 - 3)].setText("A");
     Panneau[Math.round(Lignes/2 - 1)][Math.round(Colonnes/2 - 2)].setText("M");
     Panneau[Math.round(Lignes/2 - 1)][Math.round(Colonnes/2 - 1)].setText("E");
     Panneau[Math.round(Lignes/2 - 1)][Math.round(Colonnes/2 + 1)].setText("O");
     Panneau[Math.round(Lignes/2 - 1)][Math.round(Colonnes/2 + 2)].setText("V");
     Panneau[Math.round(Lignes/2 - 1)][Math.round(Colonnes/2 + 3)].setText("E");
     Panneau[Math.round(Lignes/2 - 1)][Math.round(Colonnes/2 + 4)].setText("R");
 }



  // M�thode permettant de faire rebondir le mot PAUSE dans le
  // PanneauTetris entre deux lignes: ligne1 et ligne2
  public void RebondPause(int ligne1,int ligne2)
  {
    try
    {
      for (int i = ligne1; i > ligne2; i--) {
        Panneau[i][Math.round(Colonnes / 2 - 3)].setText("P");
        Panneau[i][Math.round(Colonnes / 2 - 2)].setText("A");
        Panneau[i][Math.round(Colonnes / 2 - 1)].setText("U");
        Panneau[i][Math.round(Colonnes / 2)].setText("S");
        Panneau[i][Math.round(Colonnes / 2 + 1)].setText("E");
        Thread.sleep(50);
        Panneau[i][Math.round(Colonnes / 2 - 3)].setText("");
        Panneau[i][Math.round(Colonnes / 2 - 2)].setText("");
        Panneau[i][Math.round(Colonnes / 2 - 1)].setText("");
        Panneau[i][Math.round(Colonnes / 2)].setText("");
        Panneau[i][Math.round(Colonnes / 2 + 1)].setText("");
      }
    }catch(Exception e) {e.printStackTrace();}
  }



  // M�thode permettant de faire rebondir le mot Game Over dans le
  // PanneauTetris entre deux lignes: ligne1 et ligne2
  public void RebondGameOver(int ligne1,int ligne2)
  {
    try
    {
      for (int i = ligne1; i > ligne2; i--) {
        Panneau[i][Math.round(Colonnes / 2 - 4)].setText("G");
        Panneau[i][Math.round(Colonnes / 2 - 3)].setText("A");
        Panneau[i][Math.round(Colonnes / 2 - 2)].setText("M");
        Panneau[i][Math.round(Colonnes / 2 - 1)].setText("E");
        Panneau[i][Math.round(Colonnes / 2 + 1)].setText("O");
        Panneau[i][Math.round(Colonnes / 2 + 2)].setText("V");
        Panneau[i][Math.round(Colonnes / 2 + 3)].setText("E");
        Panneau[i][Math.round(Colonnes / 2 + 4)].setText("R");
        Thread.sleep(50);
        Panneau[i][Math.round(Colonnes / 2 - 4)].setText("");
        Panneau[i][Math.round(Colonnes / 2 - 3)].setText("");
        Panneau[i][Math.round(Colonnes / 2 - 2)].setText("");
        Panneau[i][Math.round(Colonnes / 2 - 1)].setText("");
        Panneau[i][Math.round(Colonnes / 2 + 1)].setText("");
        Panneau[i][Math.round(Colonnes / 2 + 2)].setText("");
        Panneau[i][Math.round(Colonnes / 2 + 3)].setText("");
        Panneau[i][Math.round(Colonnes / 2 + 4)].setText("");
      }
    }catch(Exception e) {e.printStackTrace();}
  }



  // M�thode permettant de faire chuter le mot PAUSE dans le
  // PanneauTetris entre deux lignes: ligne1 et ligne2
  public void ChutePause(int ligne1,int ligne2)
  {
    try
    {
      for(int i = ligne1; i < ligne2; i++) {
        Panneau[i][Math.round(Colonnes / 2 - 3)].setText("P");
        Panneau[i][Math.round(Colonnes / 2 - 2)].setText("A");
        Panneau[i][Math.round(Colonnes / 2 - 1)].setText("U");
        Panneau[i][Math.round(Colonnes / 2)].setText("S");
        Panneau[i][Math.round(Colonnes / 2 + 1)].setText("E");
        Thread.sleep(50);
        Panneau[i][Math.round(Colonnes / 2 - 3)].setText("");
        Panneau[i][Math.round(Colonnes / 2 - 2)].setText("");
        Panneau[i][Math.round(Colonnes / 2 - 1)].setText("");
        Panneau[i][Math.round(Colonnes / 2)].setText("");
        Panneau[i][Math.round(Colonnes / 2 + 1)].setText("");
      }
    }catch(Exception e) {e.printStackTrace();}
  }



  // M�thode permettant de faire chuter le mot Game Over dans le
  // PanneauTetris entre deux lignes: ligne1 et ligne2
  public void ChuteGameOver(int ligne1,int ligne2)
  {
    try
    {
      for (int i = ligne1; i < ligne2; i++) {
        Panneau[i][Math.round(Colonnes / 2 - 4)].setText("G");
        Panneau[i][Math.round(Colonnes / 2 - 3)].setText("A");
        Panneau[i][Math.round(Colonnes / 2 - 2)].setText("M");
        Panneau[i][Math.round(Colonnes / 2 - 1)].setText("E");
        Panneau[i][Math.round(Colonnes / 2 + 1)].setText("O");
        Panneau[i][Math.round(Colonnes / 2 + 2)].setText("V");
        Panneau[i][Math.round(Colonnes / 2 + 3)].setText("E");
        Panneau[i][Math.round(Colonnes / 2 + 4)].setText("R");
        Thread.sleep(50);
        Panneau[i][Math.round(Colonnes / 2 - 4)].setText("");
        Panneau[i][Math.round(Colonnes / 2 - 3)].setText("");
        Panneau[i][Math.round(Colonnes / 2 - 2)].setText("");
        Panneau[i][Math.round(Colonnes / 2 - 1)].setText("");
        Panneau[i][Math.round(Colonnes / 2 + 1)].setText("");
        Panneau[i][Math.round(Colonnes / 2 + 2)].setText("");
        Panneau[i][Math.round(Colonnes / 2 + 3)].setText("");
        Panneau[i][Math.round(Colonnes / 2 + 4)].setText("");
      }
    }catch(Exception e) {e.printStackTrace();}
  }



  // M�thode permettant d'effacer du PanneauTetris le message indiquant
  // que la partie est en pause ou termin�e
  public void EffaceTexte()
  {
    // Pour chaque case du PanneauTetris
    for(int i=0;i<Lignes;i++)
      for(int j=0;j<Colonnes;j++)
      {
        // On efface le texte contenu dans la case
        Panneau[i][j].setText("");
        Panneau[i][j].setForeground(Color.BLUE);
      }
  }



  // M�thode permettant de retirer du panneau les lignes pleines,
  // de mettre � jour le tableau indiquant la position des pi�ces et
  // le PanneauTetris
  public void EnleverLignesPleines()
  {
    // Variable utilis�e pour compter les cases pleines d'une ligne
    int CasePleine;
    // Variable utilis�e pour comptabiliser le nombre de lignes compl�t�es
    // par le joueur
    int LignesRemplies = 0;
    // Parcourt du tableau indiquant la position des pi�ces
    for(int i=0;i<Lignes;i++)
    {
      // Initialisation de CasePleine
      CasePleine = 0;
      for (int j = 0; j < Colonnes; j++)
          // Si la case est occup�e, on incr�mente CasePleine
          if(Tab[i][j] > 0) CasePleine++;
      // Si le nombre de cases pleines est �gale au nombre de colonnes
      // cela signifie que la ligne i est pleine
      if(CasePleine == Colonnes)
      {
        // D�calage des lignes � partir de la ligne i
        DecalleLigne(i);
        // On incr�mente le nombre de lignes compl�t�es par le joueur
        LignesRemplies++;
      }
    }
    // Si le joueur a compl�t� au moins une ligne
    if(LignesRemplies > 0)
    {
      // Mise � jour du nombre total de lignes compl�t�es dans la partie
      NbLignesComplet = NbLignesComplet + LignesRemplies;
      // Mise � jour du niveau de difficult� atteint par le joueur
      // � savoir que toutes les 20 lignes compl�t�es par le joueur,
      // on passe au niveau sup�rieur
      Niveau = NbLignesComplet / 20 + 1;
      // Mise � jour de la vitesse de descente des pi�ces
      if (Niveau < 6)
        VitesseNormale = 1200 - Niveau * 200;
      else
        VitesseNormale = VitesseNormale / Niveau;
        // Mise � jour du score en fonction de la combinaison r�alis�e
      switch (LignesRemplies)
      {
        case 1: Score = Score + 50 * Niveau;
                break;
        case 2: Score = Score + 200 * Niveau;
                break;
        case 3: Score = Score + 500 * Niveau;
                break;
        case 4: Score = Score + 800 * Niveau;
                break;
      }
      // Mise � jour du PanneauTetris
      MAJ_PanneauTetris();
    }
  }



    // M�thode permettant de d�caler d'une ligne vers le bas la ligne
    // dont le num�ro est re�u en argument ainsi que les lignes qui sont
    // situ�es au dessus de cette ligne
    public void DecalleLigne(int NumLigne)
    {
      for(int i=NumLigne;i>0;i--)
        for(int j=0;j<Colonnes;j++)
          Tab[i][j] = Tab[i-1][j];
      // Cas de la 1�re ligne, la plus haute dans le PanneauTetris
      for(int j=0;j<Colonnes;j++)
        Tab[0][j] = 0;
    }



  // M�thode permettant permettant d'effacer la position actuelle de
  // la pi�ce dans le PanneauTetris avant de la faire descendre d'une
  // ligne ou de la d�placer � gauche ou � droite
  public void EffacePiece()
  {
    try{
      if (LigneCourante >= 0) {
        for (int i=LigneCourante;i<LigneCourante + P.getNbLignes(); i++)
          for (int j=ColonneCourante;j<ColonneCourante + P.getNbColonnes();j++)
            if (P.Forme[i - LigneCourante][j - ColonneCourante] == 1)
              Tab[i][j] = 0;
      }
    }catch(Exception e){e.printStackTrace();}
  }



   // M�thode qui s'occupe de la descente de la pi�ce dans le
   // PanneauTetris
   public void Descendre()
  {
    // On efface la pi�ce
    EffacePiece();
    // On incr�mente d'une unit� la LigneCourante de la pi�ce
    LigneCourante++;
    // Mise � jour du tableau de position des pi�ces pour m�moriser la
    // descente d'une ligne de la pi�ce
    try{
      for (int i=LigneCourante;i<LigneCourante + P.getNbLignes(); i++)
        for (int j=ColonneCourante;j<ColonneCourante + P.getNbColonnes();j++)
          if (P.Forme[i - LigneCourante][j - ColonneCourante] == 1)
            Tab[i][j] = P.getCouleur();
    }catch(Exception e){e.printStackTrace();}
    // Mise � jour du PanneauTetris
    MAJ_PanneauTetris();
  }



  // M�thode permettant de savoir si la pi�ce qui descend dans le
  // PanneauTetris est bloqu�e ou non
  public boolean EstBloque()
  {
    // Variable contenant la valeur de retour de cette m�thode
    boolean reponse = false;
    // Si la pi�ce est arriv� au bas du PanneauTetris
    if(LigneCourante + P.getNbLignes() == Lignes)
      // elle est bloqu�e
      reponse = true;
    // sinon
    else
    {
      try{
        // Pour chaque colonne constituant la forme de la pi�ce,
        // on cherche la ligne i correspondante � la partie de cette pi�ce
        // la plus basse. Si la ligne qui se situe en dessous sur la m�me
        // colonne est occup�e, alors la pi�ce est bloqu�e
        for (int j = 0; j < P.getNbColonnes(); j++) {
          int i = P.getNbLignes() - 1;
          while (i >= 0 && P.Forme[i][j] == 0)
            i--;
          if (i >= 0 && Tab[i + 1 + LigneCourante][j + ColonneCourante] > 0)
            reponse = true;
        }
      }catch(Exception e){e.printStackTrace();}
    }
    return reponse;
  }



  // M�thode permettant de savoir si la pi�ce qui descend dans le
  // PanneauTetris est bloqu�e ou non
  public boolean EstPerdu()
  {
    // Variable contenant la valeur de retour de cette m�thode
    boolean reponse = false;
    // Si la pi�ce n'est pas encore apparu dans le PanneauTetris,
    // on va regarder s'il y a la place dans le PanneauTetris pour qu'elle
    // soit affich�. Si ce n'est pas le cas, c'est que le joueur a perdu
    // la partie
    try{
      if(LigneCourante == -1)
      {
        for(int i=0;i<P.getNbLignes();i++)
          for(int j=0;j<P.getNbColonnes();j++)
            if(P.Forme[i][j]==1 && Tab[i][j + ColonneCourante] > 0)
              reponse = true;
      }
    }catch(Exception e){e.printStackTrace();}
    return reponse;
  }



  // Gestion des �v�nements clavier
  public void keyPressed(KeyEvent e)
  {
    // On r�cup�re le code de la touche press�e par le joueur
    int touche = e.getKeyCode();
    switch(touche)
    {
      // Touche bas enfonc�e
      case(KeyEvent.VK_DOWN):
        // On passe en vitesse acc�l�r�e de descente des pi�ces
        this.setVitesseCourante(VitesseAcceleree);
        break;


      // Touche haut enfonc�e
      case(KeyEvent.VK_UP):
        try{
          // Variable utilis�e pour savoir si la rotation de la pi�ce est
          // possible ou non
          boolean rotationpossible = true;
          // Si la rotation ne fait pas d�passer la pi�ce du c�t� droit
          // ou du bas du PanneauT�tris
          if (ColonneCourante + P.getNbLignes() - 1 < Colonnes && LigneCourante + P.getNbColonnes() - 1 < Lignes)
          {
            // Cr�ation d'un tableau TabMem utilis�e pour manipuler ici une
            // copie du tableau de position des pi�ces dans le PanneauTetris
            int TabMem[][] = new int[Lignes][Colonnes];
            // Copie du tableau de position des pi�ces
            for(int i=0;i<Lignes;i++)
              for(int j=0;j<Colonnes;j++)
                TabMem[i][j] = Tab[i][j];
            // On effectue une simulation de la rotation pour voir si les
            // autres pi�ces du PanneauTetris n'emp�che pas cette rotation
            // Cr�ation d'une nouvelle pi�ce
            Piece PSim = new Piece();
            // On donne � cette nouvelle pi�ce les caract�ristiques de la
            // pi�ce courante qui descend dans le PanneauTetris
            PSim.setTypePiece(P.getTypePiece());
            PSim.setNbLignes(P.getNbLignes());
            PSim.setNbColonnes(P.getNbColonnes());
            PSim.Forme = P.Forme;
            // On r�alise la rotation de cette nouvelle pi�ce
            PSim.Rotation();
            // Avant de regarder si la rotation est possible, on fait en
            // sorte de ne pas tenir compte lors de la v�rification, des
            // cases remplies par la position de la pi�ce avant rotation
            for(int i = 0;i<P.getNbLignes();i++)
              for(int j = 0;j<P.getNbColonnes();j++)
                if(P.Forme[i][j] == 1) TabMem[i + LigneCourante][j + ColonneCourante] = 0;
            // On regarde si aucune case du PanneauT�tris emp�che la rotation
            // de la pi�ce du fait
            for(int i = 0;i<PSim.getNbLignes();i++)
              for(int j = 0;j<PSim.getNbColonnes();j++)
                if(TabMem[i + LigneCourante][j + ColonneCourante] > 0 && PSim.Forme[i][j] == 1) rotationpossible = false;
            // Si la rotation de la pi�ce est possible
            if(rotationpossible == true)
            {
              // On efface l'ancienne position de la pi�ce
              EffacePiece();
              // On effectue la rotation de la pi�ce
              P.Rotation();
              // Mise � jour du tableau de position des pi�ces
              for (int i = LigneCourante; i < LigneCourante + P.getNbLignes();
                   i++)
                for (int j = ColonneCourante;
                     j < ColonneCourante + P.getNbColonnes(); j++)
                  if (P.Forme[i - LigneCourante][j - ColonneCourante] == 1)
                    Tab[i][j] = P.getCouleur();
              // On met � jour le PanneauTetris
              MAJ_PanneauTetris();
            }
          }
        }catch(Exception er){er.printStackTrace();}
        break;


      // Touche gauche enfonc�e
      case(KeyEvent.VK_LEFT):
        try{
          // Si la pi�ce n'est encore coll�e au c�t� gauche du PanneauTetris
          if (ColonneCourante > 0)
          {
            // Variable qui va indiquer si la pi�ce peut �tre d�call�e
            // d'une colonne sur la gauche
            boolean decallagepossible = true;
            // Dans les lignes constituant la forme de la pi�ce,
            // on cherche la colonne j correspondante � la partie de
            // cette pi�ce la plus � gauche. Si la colonne qui se situe
            // � gauche sur la m�me ligne est occup�e, alors la pi�ce est
            // bloqu�e et on met decallagepossoble � false
            int i = 0;
            while(i < P.getNbLignes() && decallagepossible == true)
            {
              int j = 0;
              while (j<Colonnes && P.Forme[i][j] == 0) j++;
              if (j < Colonnes && Tab[i + LigneCourante][j - 1 + ColonneCourante] > 0)
                decallagepossible = false;
              i++;
            }
            // Si le d�callage de la pi�ce sur la gauche est possible
            if (decallagepossible == true) {
              // On efface l'ancienne position de la pi�ce
              EffacePiece();
              // On met � jour ColonneCourante pour effectuer le d�callage
              ColonneCourante--;
              // Mise � jour du tableau de position des pi�ces
              for (i = LigneCourante; i < LigneCourante + P.getNbLignes();i++)
                for (int j = ColonneCourante;j < ColonneCourante + P.getNbColonnes(); j++)
                  if (P.Forme[i - LigneCourante][j - ColonneCourante] == 1)
                    Tab[i][j] = P.getCouleur();
              // On met � jour le PanneauTetris
              MAJ_PanneauTetris();
            }
          }
        }catch(Exception et){et.printStackTrace();}
        break;

      // Touche droite enfonc�e
      case(KeyEvent.VK_RIGHT):
        try{
          // Si la pi�ce n'est encore coll�e au c�t� droit du PanneauTetris
          if (ColonneCourante < Colonnes - P.getNbColonnes())
          {
            // Variable qui va indiquer si la pi�ce peut �tre d�call�e
            // d'une colonne sur la droite
            boolean decallagepossible = true;
            // Dans les lignes constituant la forme de la pi�ce,
            // on cherche la colonne j correspondante � la partie de
            // cette pi�ce la plus � droite. Si la colonne qui se situe
            // � droite sur la m�me ligne est occup�e, alors la pi�ce est
            // bloqu�e et on met decallagepossoble � false
            int i = 0;
            while(i < P.getNbLignes() && decallagepossible == true)
            {
              int j = P.getNbColonnes() - 1;
              while (j >= 0 && P.Forme[i][j] == 0) j--;
              if (j >= 0 && Tab[i + LigneCourante][j + 1 + ColonneCourante] > 0)
                decallagepossible = false;
              i++;
            }
            // Si le d�callage de la pi�ce sur la droite est possible
            if (decallagepossible == true) {
              // On efface l'ancienne position de la pi�ce
              EffacePiece();
              // On met � jour ColonneCourante pour effectuer le d�callage
              ColonneCourante++;
              // Mise � jour du tableau de position des pi�ces
              for (i = LigneCourante; i < LigneCourante + P.getNbLignes();
                   i++)
                for (int j = ColonneCourante;
                     j < ColonneCourante + P.getNbColonnes(); j++)
                  if (P.Forme[i - LigneCourante][j - ColonneCourante] == 1)
                    Tab[i][j] = P.getCouleur();
             // On met � jour le PanneauTetris
             MAJ_PanneauTetris();
            }
          }
        }catch(Exception ex){ex.printStackTrace();}
        break;
    }
  }



  // M�thode ex�cut�e lorsqu'une touche du clavier est rel�ch�e
  public void keyReleased(KeyEvent e)
  {
    // Si Touche bas rel�ch�e
    if(e.getKeyCode() == KeyEvent.VK_DOWN)
      // On repasse � la vitesse normale de descente des pi�ces
      this.setVitesseCourante(VitesseNormale);
  }



  public void keyTyped(KeyEvent e)
  {
    this.requestFocus();
  }



  // Autres m�thodes de la classe
  public int getNbLignes(){return Lignes;}
  public int getNbColonnes(){return Colonnes;}
  public int getTailleCarre(){return TailleCarre;}
  public int getLigneCourante(){return LigneCourante;}
  public int getColonneCourante(){return ColonneCourante;}
  public int getVitesseCourante(){return VitesseCourante;}
  public int getNbLignesComplet(){return NbLignesComplet;}
  public int getScore(){return Score;}
  public Piece getPiece(){return P;}
  public int getVitesseNormale(){return VitesseNormale;}
  public int getVitesseAcceleree(){return VitesseAcceleree;}
  public int getNiveau(){return Niveau;}
  public void setNbLignes(int nouv){Lignes=nouv;}
  public void setNbColonnes(int nouv){Colonnes=nouv;}
  public void setTailleCarre(int nouv){TailleCarre=nouv;}
  public void setLigneCourante(int nouv){LigneCourante=nouv;}
  public void setColonneCourante(int nouv){ColonneCourante=nouv;}
  public void setVitesseCourante(int nouv){VitesseCourante=nouv;}
  public void setNbLignesComplet(int nouv){NbLignesComplet=nouv;}
  public void setPiece(Piece nouv){P=nouv;}
  public void setVitesseNormale(int nouv){VitesseNormale=nouv;}
  public void setVitesseAcceleree(int nouv){VitesseAcceleree=nouv;}
  public void setScore(int nouv){Score=nouv;}
  public void setNiveau(int nouv){Niveau=nouv;}
}