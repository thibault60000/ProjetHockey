package gardien;

import java.util.Collection;

import javax.ejb.Remote;

@Remote
public interface GardienManagerRemote {

	Collection<Gardien> listerGardien();

	Gardien getInfoGardien(int idGardien);

}
