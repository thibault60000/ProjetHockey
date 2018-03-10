package projet_tetris;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;


/**
 * <p>Titre : PanneauTetris</p>
 * <p>Description : Classe de la cuve ou vont descendre les pièces</p>
 * <p>Copyright : Copyright (c) 2004</p>
 * <p>Société : </p>
 * @author Cédric Montillot et Djilali Maghraoui
 * @version 1.0
 */


public class PanneauTetris extends JPanel implements KeyListener {
  // Nombre de colonnes et de lignes
  int Lignes = 20;
  int Colonnes = 12;
  // Piece qui descend dans le PanneauTetris
  Piece P;
  // Le coin gauche de la pièce qui descend
  int LigneCourante;
  int ColonneCourante;
  // Tableau du tetris grâce auquel on vérifie la place des pièces
  int Tab[][] = new int[Lignes][Colonnes];
  // Taille d'une case dans l'affichage du tableau tetris
  int TailleCarre = 20;
  // Tableau d'étiquettes à ajouter dans le panel pour visualiser les pièces
  JLabel Panneau[][] = new JLabel[Lignes][Colonnes];
  // Layout du panel pour afficher les JLabel
  GridBagLayout LayoutPanneauTetris = new GridBagLayout();
  // Tableau des couleurs utilisées pour peindre les cases du Tetris
  Color Couleurs[] = new Color[8];
  // Vitesse de descente de pièces dans le Panneau
  int VitesseCourante;
  // Vitesse normale de descente des pièces dans le Panneau
  int VitesseNormale;
  // Vitesse accélérée de descente des pièces quand le joueur utilise la
  // flèche bas du clavier
  int VitesseAcceleree = 10;
  // Nombre de lignes complétées par le joueur
  int NbLignesComplet;
  // Score du joueur
  int Score;
  // Niveau atteint par le joueur, à chaque niveau atteint par le joueur,
  // la vitesse de descente des pièces s'accélère mais le joueur gagne
  // plus de points en fonction des combinaisons réalisées
  int Niveau;


  // Constructeur de la classe
  public PanneauTetris() {
    try {
      // Appel de la méthode d'initialisation du panneau
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
    // Initialisation "générale" du panneau
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



  // Méthode permettant de colorer les cases du PanneauTetris en fonction
  // du tableau indiquant la position et la couleur des pièces
  public void MAJ_PanneauTetris()
  {
     // Pour chaque case du PanneauTetris
     for(int i=0;i<Lignes;i++)
       for(int j=0;j<Colonnes;j++)
          // On colorie la case selon la couleur représenté par l'entier
          // contenu dans Tab[i][j]
          Panneau[i][j].setBackground(Couleurs[Tab[i][j]]);
  }



  // Méthode permettant de colorer en noire toutes les cases du
  // PanneauTetris quand le joueur choisit de clicker sur "Pause"
  public void ColoreNoir()
  {
    // Pour chaque case du PanneauTetris
    for(int i=0;i<Lignes;i++)
      for(int j=0;j<Colonnes;j++)
        // On colorie la case en noir
        Panneau[i][j].setBackground(Color.BLACK);
  }



  // Méthode permettant la réinitialisation du PanneauTetris
  public void InitPanneau()
  {
     // On efface le mode pause au cas où le joueur clique sur "Nouvelle Partie"
     // pendant que sa partie actuelle est en mode pause
     EffaceTexte();
     // On colore en noir la PanneauTetris
     ColoreNoir();
     // On réinitialise le tableau Tab indiquant la position des pièces
     for(int i=0;i<Lignes;i++)
       for(int j=0;j<Colonnes;j++)
         // On colorie la case en noir
         Tab[i][j] = 0;
     // Initialisation de la vitesse de chute des pièces
     VitesseNormale = 1000;
     // Initialisation du nombre de lignes complétées par le joueur
     NbLignesComplet = 0;
     // Initialisation du score
     Score = 0;
     // Initialisation du niveau de difficulté du jeu
     Niveau = 1;
  }



  // Méthode permettant d'afficher l'animation d'introduction du Super Tetris
  // dans le PanneauTetris
  public void AnimIntroduction()
  {
    int x,y;
    // On définit une couleur aléatoire
    Random r = new Random();
    for(int i=0;i<10;i++)
    {
      // On définit un numéro de ligne et de colonne aléatoire
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
      // On définit un numéro de ligne et de colonne alétoire
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
      // On définit un numéro de ligne et de colonne alétoire
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
      // On définit un numéro de ligne et de colonne alétoire
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
      // On définit un numéro de ligne et de colonne alétoire
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
      // On définit un numéro de ligne et de colonne alétoire
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
      // On définit un numéro de ligne et de colonne alétoire
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
      // On définit un numéro de ligne et de colonne alétoire
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
      // On définit un numéro de ligne et de colonne alétoire
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
      // On définit un numéro de ligne et de colonne alétoire
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
      // On définit un numéro de ligne et de colonne alétoire
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



  // Méthode permettant d'afficher sur le PanneauTetris que la partie est
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



 // Méthode permettant d'afficher sur le PanneauTetris que la partie est
 // terminée et que le joueur a perdu
 public void AfficheGameOver()
 {
     // Remplissage des lignes petit à petit
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



  // Méthode permettant de faire rebondir le mot PAUSE dans le
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



  // Méthode permettant de faire rebondir le mot Game Over dans le
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



  // Méthode permettant de faire chuter le mot PAUSE dans le
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



  // Méthode permettant de faire chuter le mot Game Over dans le
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



  // Méthode permettant d'effacer du PanneauTetris le message indiquant
  // que la partie est en pause ou terminée
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



  // Méthode permettant de retirer du panneau les lignes pleines,
  // de mettre à jour le tableau indiquant la position des pièces et
  // le PanneauTetris
  public void EnleverLignesPleines()
  {
    // Variable utilisée pour compter les cases pleines d'une ligne
    int CasePleine;
    // Variable utilisée pour comptabiliser le nombre de lignes complétées
    // par le joueur
    int LignesRemplies = 0;
    // Parcourt du tableau indiquant la position des pièces
    for(int i=0;i<Lignes;i++)
    {
      // Initialisation de CasePleine
      CasePleine = 0;
      for (int j = 0; j < Colonnes; j++)
          // Si la case est occupée, on incrémente CasePleine
          if(Tab[i][j] > 0) CasePleine++;
      // Si le nombre de cases pleines est égale au nombre de colonnes
      // cela signifie que la ligne i est pleine
      if(CasePleine == Colonnes)
      {
        // Décalage des lignes à partir de la ligne i
        DecalleLigne(i);
        // On incrémente le nombre de lignes complétées par le joueur
        LignesRemplies++;
      }
    }
    // Si le joueur a complété au moins une ligne
    if(LignesRemplies > 0)
    {
      // Mise à jour du nombre total de lignes complétées dans la partie
      NbLignesComplet = NbLignesComplet + LignesRemplies;
      // Mise à jour du niveau de difficulté atteint par le joueur
      // à savoir que toutes les 20 lignes complétées par le joueur,
      // on passe au niveau supérieur
      Niveau = NbLignesComplet / 20 + 1;
      // Mise à jour de la vitesse de descente des pièces
      if (Niveau < 6)
        VitesseNormale = 1200 - Niveau * 200;
      else
        VitesseNormale = VitesseNormale / Niveau;
        // Mise à jour du score en fonction de la combinaison réalisée
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
      // Mise à jour du PanneauTetris
      MAJ_PanneauTetris();
    }
  }



    // Méthode permettant de décaler d'une ligne vers le bas la ligne
    // dont le numéro est reçu en argument ainsi que les lignes qui sont
    // situées au dessus de cette ligne
    public void DecalleLigne(int NumLigne)
    {
      for(int i=NumLigne;i>0;i--)
        for(int j=0;j<Colonnes;j++)
          Tab[i][j] = Tab[i-1][j];
      // Cas de la 1ère ligne, la plus haute dans le PanneauTetris
      for(int j=0;j<Colonnes;j++)
        Tab[0][j] = 0;
    }



  // Méthode permettant permettant d'effacer la position actuelle de
  // la pièce dans le PanneauTetris avant de la faire descendre d'une
  // ligne ou de la déplacer à gauche ou à droite
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



   // Méthode qui s'occupe de la descente de la pièce dans le
   // PanneauTetris
   public void Descendre()
  {
    // On efface la pièce
    EffacePiece();
    // On incrémente d'une unité la LigneCourante de la pièce
    LigneCourante++;
    // Mise à jour du tableau de position des pièces pour mémoriser la
    // descente d'une ligne de la pièce
    try{
      for (int i=LigneCourante;i<LigneCourante + P.getNbLignes(); i++)
        for (int j=ColonneCourante;j<ColonneCourante + P.getNbColonnes();j++)
          if (P.Forme[i - LigneCourante][j - ColonneCourante] == 1)
            Tab[i][j] = P.getCouleur();
    }catch(Exception e){e.printStackTrace();}
    // Mise à jour du PanneauTetris
    MAJ_PanneauTetris();
  }



  // Méthode permettant de savoir si la pièce qui descend dans le
  // PanneauTetris est bloquée ou non
  public boolean EstBloque()
  {
    // Variable contenant la valeur de retour de cette méthode
    boolean reponse = false;
    // Si la pièce est arrivé au bas du PanneauTetris
    if(LigneCourante + P.getNbLignes() == Lignes)
      // elle est bloquée
      reponse = true;
    // sinon
    else
    {
      try{
        // Pour chaque colonne constituant la forme de la pièce,
        // on cherche la ligne i correspondante à la partie de cette pièce
        // la plus basse. Si la ligne qui se situe en dessous sur la même
        // colonne est occupée, alors la pièce est bloquée
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



  // Méthode permettant de savoir si la pièce qui descend dans le
  // PanneauTetris est bloquée ou non
  public boolean EstPerdu()
  {
    // Variable contenant la valeur de retour de cette méthode
    boolean reponse = false;
    // Si la pièce n'est pas encore apparu dans le PanneauTetris,
    // on va regarder s'il y a la place dans le PanneauTetris pour qu'elle
    // soit affiché. Si ce n'est pas le cas, c'est que le joueur a perdu
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



  // Gestion des évènements clavier
  public void keyPressed(KeyEvent e)
  {
    // On récupère le code de la touche pressée par le joueur
    int touche = e.getKeyCode();
    switch(touche)
    {
      // Touche bas enfoncée
      case(KeyEvent.VK_DOWN):
        // On passe en vitesse accélérée de descente des pièces
        this.setVitesseCourante(VitesseAcceleree);
        break;


      // Touche haut enfoncée
      case(KeyEvent.VK_UP):
        try{
          // Variable utilisée pour savoir si la rotation de la pièce est
          // possible ou non
          boolean rotationpossible = true;
          // Si la rotation ne fait pas dépasser la pièce du côté droit
          // ou du bas du PanneauTétris
          if (ColonneCourante + P.getNbLignes() - 1 < Colonnes && LigneCourante + P.getNbColonnes() - 1 < Lignes)
          {
            // Création d'un tableau TabMem utilisée pour manipuler ici une
            // copie du tableau de position des pièces dans le PanneauTetris
            int TabMem[][] = new int[Lignes][Colonnes];
            // Copie du tableau de position des pièces
            for(int i=0;i<Lignes;i++)
              for(int j=0;j<Colonnes;j++)
                TabMem[i][j] = Tab[i][j];
            // On effectue une simulation de la rotation pour voir si les
            // autres pièces du PanneauTetris n'empêche pas cette rotation
            // Création d'une nouvelle pièce
            Piece PSim = new Piece();
            // On donne à cette nouvelle pièce les caractèristiques de la
            // pièce courante qui descend dans le PanneauTetris
            PSim.setTypePiece(P.getTypePiece());
            PSim.setNbLignes(P.getNbLignes());
            PSim.setNbColonnes(P.getNbColonnes());
            PSim.Forme = P.Forme;
            // On réalise la rotation de cette nouvelle pièce
            PSim.Rotation();
            // Avant de regarder si la rotation est possible, on fait en
            // sorte de ne pas tenir compte lors de la vérification, des
            // cases remplies par la position de la pièce avant rotation
            for(int i = 0;i<P.getNbLignes();i++)
              for(int j = 0;j<P.getNbColonnes();j++)
                if(P.Forme[i][j] == 1) TabMem[i + LigneCourante][j + ColonneCourante] = 0;
            // On regarde si aucune case du PanneauTétris empêche la rotation
            // de la pièce du fait
            for(int i = 0;i<PSim.getNbLignes();i++)
              for(int j = 0;j<PSim.getNbColonnes();j++)
                if(TabMem[i + LigneCourante][j + ColonneCourante] > 0 && PSim.Forme[i][j] == 1) rotationpossible = false;
            // Si la rotation de la pièce est possible
            if(rotationpossible == true)
            {
              // On efface l'ancienne position de la pièce
              EffacePiece();
              // On effectue la rotation de la pièce
              P.Rotation();
              // Mise à jour du tableau de position des pièces
              for (int i = LigneCourante; i < LigneCourante + P.getNbLignes();
                   i++)
                for (int j = ColonneCourante;
                     j < ColonneCourante + P.getNbColonnes(); j++)
                  if (P.Forme[i - LigneCourante][j - ColonneCourante] == 1)
                    Tab[i][j] = P.getCouleur();
              // On met à jour le PanneauTetris
              MAJ_PanneauTetris();
            }
          }
        }catch(Exception er){er.printStackTrace();}
        break;


      // Touche gauche enfoncée
      case(KeyEvent.VK_LEFT):
        try{
          // Si la pièce n'est encore collée au côté gauche du PanneauTetris
          if (ColonneCourante > 0)
          {
            // Variable qui va indiquer si la pièce peut être décallée
            // d'une colonne sur la gauche
            boolean decallagepossible = true;
            // Dans les lignes constituant la forme de la pièce,
            // on cherche la colonne j correspondante à la partie de
            // cette pièce la plus à gauche. Si la colonne qui se situe
            // à gauche sur la même ligne est occupée, alors la pièce est
            // bloquée et on met decallagepossoble à false
            int i = 0;
            while(i < P.getNbLignes() && decallagepossible == true)
            {
              int j = 0;
              while (j<Colonnes && P.Forme[i][j] == 0) j++;
              if (j < Colonnes && Tab[i + LigneCourante][j - 1 + ColonneCourante] > 0)
                decallagepossible = false;
              i++;
            }
            // Si le décallage de la pièce sur la gauche est possible
            if (decallagepossible == true) {
              // On efface l'ancienne position de la pièce
              EffacePiece();
              // On met à jour ColonneCourante pour effectuer le décallage
              ColonneCourante--;
              // Mise à jour du tableau de position des pièces
              for (i = LigneCourante; i < LigneCourante + P.getNbLignes();i++)
                for (int j = ColonneCourante;j < ColonneCourante + P.getNbColonnes(); j++)
                  if (P.Forme[i - LigneCourante][j - ColonneCourante] == 1)
                    Tab[i][j] = P.getCouleur();
              // On met à jour le PanneauTetris
              MAJ_PanneauTetris();
            }
          }
        }catch(Exception et){et.printStackTrace();}
        break;

      // Touche droite enfoncée
      case(KeyEvent.VK_RIGHT):
        try{
          // Si la pièce n'est encore collée au côté droit du PanneauTetris
          if (ColonneCourante < Colonnes - P.getNbColonnes())
          {
            // Variable qui va indiquer si la pièce peut être décallée
            // d'une colonne sur la droite
            boolean decallagepossible = true;
            // Dans les lignes constituant la forme de la pièce,
            // on cherche la colonne j correspondante à la partie de
            // cette pièce la plus à droite. Si la colonne qui se situe
            // à droite sur la même ligne est occupée, alors la pièce est
            // bloquée et on met decallagepossoble à false
            int i = 0;
            while(i < P.getNbLignes() && decallagepossible == true)
            {
              int j = P.getNbColonnes() - 1;
              while (j >= 0 && P.Forme[i][j] == 0) j--;
              if (j >= 0 && Tab[i + LigneCourante][j + 1 + ColonneCourante] > 0)
                decallagepossible = false;
              i++;
            }
            // Si le décallage de la pièce sur la droite est possible
            if (decallagepossible == true) {
              // On efface l'ancienne position de la pièce
              EffacePiece();
              // On met à jour ColonneCourante pour effectuer le décallage
              ColonneCourante++;
              // Mise à jour du tableau de position des pièces
              for (i = LigneCourante; i < LigneCourante + P.getNbLignes();
                   i++)
                for (int j = ColonneCourante;
                     j < ColonneCourante + P.getNbColonnes(); j++)
                  if (P.Forme[i - LigneCourante][j - ColonneCourante] == 1)
                    Tab[i][j] = P.getCouleur();
             // On met à jour le PanneauTetris
             MAJ_PanneauTetris();
            }
          }
        }catch(Exception ex){ex.printStackTrace();}
        break;
    }
  }



  // Méthode exécutée lorsqu'une touche du clavier est relâchée
  public void keyReleased(KeyEvent e)
  {
    // Si Touche bas relâchée
    if(e.getKeyCode() == KeyEvent.VK_DOWN)
      // On repasse à la vitesse normale de descente des pièces
      this.setVitesseCourante(VitesseNormale);
  }



  public void keyTyped(KeyEvent e)
  {
    this.requestFocus();
  }



  // Autres méthodes de la classe
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