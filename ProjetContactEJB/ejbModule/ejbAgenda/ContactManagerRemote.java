package ejbAgenda;

import java.util.Collection;

import javax.ejb.Remote;

@Remote
public interface ContactManagerRemote {

	Contact ajouterContact(Contact contact);

	Collection<Contact> listerContact();

}
