package danix43.api.database.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import danix43.api.database.Private;

@Repository
public interface PrivateRepository extends CrudRepository<Private, Integer> {

	Iterable<Private> findByName(String name);
	Iterable<Private> findByMacaddress(String macAddress);
	Iterable<Private> findByLastdateknown(String date);
	
}
