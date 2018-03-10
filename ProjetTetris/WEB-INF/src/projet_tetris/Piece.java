package projet_tetris;

import java.util.Random;


/**
 * <p>Titre : Piece</p>
 * <p>Description : Classe des pi�ces qui vont descendre dans le
 *                  panneau tetris</p>
 * <p>Copyright : Copyright (c) 2004</p>
 * <p>Soci�t� : </p>
 * @author C�dric Montillot et Djilali Maghraoui
 * @version 1.0
 */


public class Piece {
  // Nombre de lignes occup�es par la pi�ce
  int NbLignes;
  // Nombre de colonnes occup�es par la pi�ce
  int NbColonnes;
  // Forme de la pi�ce
  int Forme[][];
  // Type de la pi�ce
  int TypePiece;
  // Entier repr�sentant la couleur de la pi�ce
  int Couleur;

  // Constructeur de la classe
  public Piece() {
   // On attribue � TypePiece un nombre al�atoire compris entre 1 et 7
   Random r = new Random();
   TypePiece = r.nextInt(7) + 1;
   // On attribue une couleur al�atoire � la pi�ce
   Couleur = r.nextInt(7) + 1;

   // Selon la valeur de TypePiece, on va d�finir la pi�ce
   switch(TypePiece)
   {
     // Pi�ce de type 1
     case 1: NbLignes = 4;
             NbColonnes = 1;
             Forme = new int[NbLignes][NbColonnes];
             Forme[0][0] = 1;
             Forme[1][0] = 1;
             Forme[2][0] = 1;
             Forme[3][0] = 1;
             break;

     // Pi�ce de type 2
     case 2: NbLignes = 2;
             NbColonnes = 3;
             Forme = new int[NbLignes][NbColonnes];
             Forme[0][0] = 1;
             Forme[1][0] = 0;
             Forme[0][1] = 1;
             Forme[1][1] = 1;
             Forme[0][2] = 0;
             Forme[1][2] = 1;
             break;

      // Pi�ce de type 3
      case 3: NbLignes = 2;
              NbColonnes = 3;
              Forme = new int[NbLignes][NbColonnes];
              Forme[0][0] = 0;
              Forme[1][0] = 1;
              Forme[0][1] = 1;
              Forme[1][1] = 1;
              Forme[0][2] = 1;
              Forme[1][2] = 0;
              break;

      // Pi�ce de type 4
      case 4: NbLignes = 2;
              NbColonnes = 3;
              Forme = new int[NbLignes][NbColonnes];
              Forme[0][0] = 0;
              Forme[1][0] = 1;
              Forme[0][1] = 1;
              Forme[1][1] = 1;
              Forme[0][2] = 0;
              Forme[1][2] = 1;
              break;

      // Pi�ce de type 5
      case 5: NbLignes = 3;
              NbColonnes = 2;
              Forme = new int[NbLignes][NbColonnes];
              Forme[0][0] = 1;
              Forme[1][0] = 0;
              Forme[2][0] = 0;
              Forme[0][1] = 1;
              Forme[1][1] = 1;
              Forme[2][1] = 1;
              break;

      // Pi�ce de type 6
      case 6: NbLignes = 3;
              NbColonnes = 2;
              Forme = new int[NbLignes][NbColonnes];
              Forme[0][0] = 1;
              Forme[1][0] = 1;
              Forme[2][0] = 1;
              Forme[0][1] = 1;
              Forme[1][1] = 0;
              Forme[2][1] = 0;
              break;

      // Pi�ce de type 7
      case 7: NbLignes = 2;
              NbColonnes = 2;
              Forme = new int[NbLignes][NbColonnes];
              Forme[0][0] = 1;
              Forme[1][0] = 1;
              Forme[0][1] = 1;
              Forme[1][1] = 1;
              break;
   }
  }



  // M�thode effectuant la rotation d'une pi�ce
  public void Rotation()
  {
   // On inverse le nombre de lignes et de colonnes
   int l = NbLignes;
   NbLignes = NbColonnes;
   NbColonnes = l;
   // Cr�ation du nouveau tableau qui contiendra la nouvelle forme de la
   // pi�ce du fait de la rotation
   int [][] Forme1 = new int[NbLignes][NbColonnes];
   // Remplissage du tableau
   for(int i=0;i<NbLignes;i++)
     for(int j=0;j<NbColonnes;j++)
       Forme1[i][j] = Forme[NbColonnes-1-j][i];
   Forme = Forme1;
  }



  // Autres m�thode de la classe
  public int getNbLignes(){return NbLignes;}
  public int getNbColonnes(){return NbColonnes;}
  public int getTypePiece(){return TypePiece;}
  public int getCouleur(){return Couleur;}
  public void setNbLignes(int nouv){NbLignes=nouv;}
  public void setNbColonnes(int nouv){NbColonnes=nouv;}
  public void setTypePiece(int nouv){TypePiece=nouv;}
  public void setCouleur(int nouv){Couleur=nouv;}
}