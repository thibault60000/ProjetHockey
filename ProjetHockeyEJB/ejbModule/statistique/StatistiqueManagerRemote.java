package statistique;

import java.util.Collection;

import javax.ejb.Remote;

@Remote
public interface StatistiqueManagerRemote {

	Collection<Statistique> listerStat(int idGardien);

	Statistique ajouterStat(Statistique statistique);

	Statistique UpdateOrInsert(int idMatchHockey, int idGardien);

	Statistique miseAJourStat(Statistique statistique);

}
