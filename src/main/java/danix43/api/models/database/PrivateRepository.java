package danix43.api.models.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import danix43.api.models.Private;

@Repository
public interface PrivateRepository extends JpaRepository<Private, Integer> {

	Iterable<Private> findByName(String name);
	Iterable<Private> findByMacaddress(String macAddress);
	Iterable<Private> findByLastdateknown(String date);
	
}
