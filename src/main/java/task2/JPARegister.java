package task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Singleton
@WebService
public class JPARegister {
	@Inject
	private static EntityManager em;

	
	//private List<Person> persons = new ArrayList<Person>();

	public void getCount() {
//		return persons.size();
	}

	public void getPerson(int index) {
//		return persons.get(index);
	}

	public void addPerson(Person person) {
	//	persons.add(person);
	//	Collections.sort(persons);
	//	em.getTransaction().begin();
		System.out.println(person);
	    em.persist(person);
	 //   em.getTransaction().commit();
	}

	public void findPersonByName(String name) {
		// for (int i = 0; i < persons.size(); i++) {
		// if (name.equals(persons.get(i).getName())) {
		// return getPerson(i);
		// }
		// }
		// return null;
//		return (Person) persons.stream().filter(s -> s.getName().equals(name)).findFirst().orElse(null);
	}

	public void findPersonByPhoneNumber(String phoneNumber) {
		// for (int i = 0; i < persons.size(); i++) {
		// if (phoneNumber.equals(persons.get(i).getPhoneNumber())) {
		// return getPerson(i);
		// }
		// }
		// return null;
	//	return (Person) persons.stream().filter(s -> s.getPhoneNumber().equals(phoneNumber)).findFirst().orElse(null);
	}

	public void removePerson(Person person) {
	//	persons.remove(person);

	}

	public void getSize() {
//		return persons.size();
	}

}
