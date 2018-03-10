package statistique;

import gardien.Gardien;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import match.MatchHockey;

@Entity
public class Statistique implements Serializable{

	@Id
	@GeneratedValue (strategy=GenerationType.AUTO) 
	private int IdStatistique;
	private int arretZoneArret1;
	private int arretZoneArret2;
	private int arretZoneArret3;
	private int arretZoneArret4;
	private int arretZoneArret5;
	private int arretZoneArret6;
	private int arretZoneArret7;
	private int arretZoneArret8;
	private int arretZoneArret9;
	private int arretZoneDeTir1;
	private int arretZoneDeTir2;
	private int arretZoneDeTir3;
	private int arretZoneDeTir4;
	private int arretZoneDeTir5;
	private int arretZoneDeTir6;
	private int lancerZoneArret1;
	private int lancerZoneArret2;
	private int lancerZoneArret3;
	private int lancerZoneArret4;
	private int lancerZoneArret5;	
	private int lancerZoneArret6;
	private int lancerZoneArret7;
	private int lancerZoneArret8;
	private int lancerZoneArret9;
	private int lancerZoneDeTir1;
	private int lancerZoneDeTir2;
	private int lancerZoneDeTir3;
	private int lancerZoneDeTir4;
	private int lancerZoneDeTir5;
	private int lancerZoneDeTir6;
	
	@ManyToOne
	  @JoinColumn(name="matchHockey_id")
	  private MatchHockey matchHockey;

	@ManyToOne
	  @JoinColumn(name="gardien_id")
	  private Gardien gardien;
	
	public MatchHockey getMatchHockey() {
		return matchHockey;
	}
	public void setMatch(MatchHockey matchHockey) {
		this.matchHockey = matchHockey;
	}
	public Gardien getGardien() {
		return gardien;
	}
	public void setGardien(Gardien gardien) {
		this.gardien = gardien;
	}
	
	public int getIdStatistique() {
		return IdStatistique;
	}
	public void setIdStatistique(int idStatistique) {
		IdStatistique = idStatistique;
	}
	public int getArretZoneArret1() {
		return arretZoneArret1;
	}
	public void setArretZoneArret1(int arretZoneArret1) {
		this.arretZoneArret1 = arretZoneArret1;
	}
	public int getArretZoneArret2() {
		return arretZoneArret2;
	}
	public void setArretZoneArret2(int arretZoneArret2) {
		this.arretZoneArret2 = arretZoneArret2;
	}
	public int getArretZoneArret3() {
		return arretZoneArret3;
	}
	public void setArretZoneArret3(int arretZoneArret3) {
		this.arretZoneArret3 = arretZoneArret3;
	}
	public int getArretZoneArret4() {
		return arretZoneArret4;
	}
	public void setArretZoneArret4(int arretZoneArret4) {
		this.arretZoneArret4 = arretZoneArret4;
	}
	public int getArretZoneArret5() {
		return arretZoneArret5;
	}
	public void setArretZoneArret5(int arretZoneArret5) {
		this.arretZoneArret5 = arretZoneArret5;
	}
	public int getArretZoneArret6() {
		return arretZoneArret6;
	}
	public void setArretZoneArret6(int arretZoneArret6) {
		this.arretZoneArret6 = arretZoneArret6;
	}
	public int getArretZoneArret7() {
		return arretZoneArret7;
	}
	public void setArretZoneArret7(int arretZoneArret7) {
		this.arretZoneArret7 = arretZoneArret7;
	}
	public int getArretZoneArret8() {
		return arretZoneArret8;
	}
	public void setArretZoneArret8(int arretZoneArret8) {
		this.arretZoneArret8 = arretZoneArret8;
	}
	public int getArretZoneArret9() {
		return arretZoneArret9;
	}
	public void setArretZoneArret9(int arretZoneArret9) {
		this.arretZoneArret9 = arretZoneArret9;
	}
	public int getArretZoneDeTir1() {
		return arretZoneDeTir1;
	}
	public void setArretZoneDeTir1(int arretZoneDeTir1) {
		this.arretZoneDeTir1 = arretZoneDeTir1;
	}
	public int getArretZoneDeTir2() {
		return arretZoneDeTir2;
	}
	public void setArretZoneDeTir2(int arretZoneDeTir2) {
		this.arretZoneDeTir2 = arretZoneDeTir2;
	}
	public int getArretZoneDeTir3() {
		return arretZoneDeTir3;
	}
	public void setArretZoneDeTir3(int arretZoneDeTir3) {
		this.arretZoneDeTir3 = arretZoneDeTir3;
	}
	public int getArretZoneDeTir4() {
		return arretZoneDeTir4;
	}
	public void setArretZoneDeTir4(int arretZoneDeTir4) {
		this.arretZoneDeTir4 = arretZoneDeTir4;
	}
	public int getArretZoneDeTir5() {
		return arretZoneDeTir5;
	}
	public void setArretZoneDeTir5(int arretZoneDeTir5) {
		this.arretZoneDeTir5 = arretZoneDeTir5;
	}
	public int getArretZoneDeTir6() {
		return arretZoneDeTir6;
	}
	public void setArretZoneDeTir6(int arretZoneDeTir6) {
		this.arretZoneDeTir6 = arretZoneDeTir6;
	}
	public int getLancerZoneArret1() {
		return lancerZoneArret1;
	}
	public void setLancerZoneArret1(int lancerZoneArret1) {
		this.lancerZoneArret1 = lancerZoneArret1;
	}
	public int getLancerZoneArret2() {
		return lancerZoneArret2;
	}
	public void setLancerZoneArret2(int lancerZoneArret2) {
		this.lancerZoneArret2 = lancerZoneArret2;
	}
	public int getLancerZoneArret3() {
		return lancerZoneArret3;
	}
	public void setLancerZoneArret3(int lancerZoneArret3) {
		this.lancerZoneArret3 = lancerZoneArret3;
	}
	public int getLancerZoneArret4() {
		return lancerZoneArret4;
	}
	public void setLancerZoneArret4(int lancerZoneArret4) {
		this.lancerZoneArret4 = lancerZoneArret4;
	}
	public int getLancerZoneArret5() {
		return lancerZoneArret5;
	}
	public void setLancerZoneArret5(int lancerZoneArret5) {
		this.lancerZoneArret5 = lancerZoneArret5;
	}
	public int getLancerZoneArret6() {
		return lancerZoneArret6;
	}
	public void setLancerZoneArret6(int lancerZoneArret6) {
		this.lancerZoneArret6 = lancerZoneArret6;
	}
	public int getLancerZoneArret7() {
		return lancerZoneArret7;
	}
	public void setLancerZoneArret7(int lancerZoneArret7) {
		this.lancerZoneArret7 = lancerZoneArret7;
	}
	public int getLancerZoneArret8() {
		return lancerZoneArret8;
	}
	public void setLancerZoneArret8(int lancerZoneArret8) {
		this.lancerZoneArret8 = lancerZoneArret8;
	}
	public int getLancerZoneArret9() {
		return lancerZoneArret9;
	}
	public void setLancerZoneArret9(int lancerZoneArret9) {
		this.lancerZoneArret9 = lancerZoneArret9;
	}
	public int getLancerZoneDeTir1() {
		return lancerZoneDeTir1;
	}
	public void setLancerZoneDeTir1(int lancerZoneDeTir1) {
		this.lancerZoneDeTir1 = lancerZoneDeTir1;
	}
	public int getLancerZoneDeTir2() {
		return lancerZoneDeTir2;
	}
	public void setLancerZoneDeTir2(int lancerZoneDeTir2) {
		this.lancerZoneDeTir2 = lancerZoneDeTir2;
	}
	public int getLancerZoneDeTir3() {
		return lancerZoneDeTir3;
	}
	public void setLancerZoneDeTir3(int lancerZoneDeTir3) {
		this.lancerZoneDeTir3 = lancerZoneDeTir3;
	}
	public int getLancerZoneDeTir4() {
		return lancerZoneDeTir4;
	}
	public void setLancerZoneDeTir4(int lancerZoneDeTir4) {
		this.lancerZoneDeTir4 = lancerZoneDeTir4;
	}
	public int getLancerZoneDeTir5() {
		return lancerZoneDeTir5;
	}
	public void setLancerZoneDeTir5(int lancerZoneDeTir5) {
		this.lancerZoneDeTir5 = lancerZoneDeTir5;
	}
	public int getLancerZoneDeTir6() {
		return lancerZoneDeTir6;
	}
	public void setLancerZoneDeTir6(int lancerZoneDeTir6) {
		this.lancerZoneDeTir6 = lancerZoneDeTir6;
	}

	
}
