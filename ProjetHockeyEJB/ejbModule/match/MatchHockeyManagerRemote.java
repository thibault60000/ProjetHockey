package match;

import java.util.Collection;

import javax.ejb.Remote;

@Remote
public interface MatchHockeyManagerRemote {

	MatchHockey ajouterMatchHockey(MatchHockey matchHockey);

	Collection<MatchHockey> listMatchHockey();

	MatchHockey getInfoMatchHockey(int idMatchHockey);

	Collection<MatchHockey> listMatchHockey(int idGardien);

}
