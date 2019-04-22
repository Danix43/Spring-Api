package danix43.api.database.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import danix43.api.database.Termometre;

@Repository
public interface TermometreRepository extends JpaRepository<Termometre, Integer> {
	
	Iterable<Termometre> findByUsedsensor(String usedSensor);
	Iterable<Termometre> findByMachinename(String name);
	Iterable<Termometre> findByLocation(String location);
	Termometre findById(int id);
}
