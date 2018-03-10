package projet_tetris;

import java.io.Serializable;

public class Joueur implements Serializable {
  private String nom;
  private int score;
  private int level;
  
  public Joueur(String nom, int score, int level)
  {
    this.nom = nom;
    this.score = score;
    this.level = level;
  }
  
  public int getScore()
  {
    return this.score;
  }
  
  public void setScore(int score)
  {
    this.score = score;
  }
  
  public int getLevel()
  {
    return this.level;
  }
  
  public void setLevel(int level)
  {
    this.level = level;
  }
  
  public String getNom()
  {
    return this.nom;
  }
  
  public void setNom(String nom)
  {
    this.nom = nom;
  }
}
