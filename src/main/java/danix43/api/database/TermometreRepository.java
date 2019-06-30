package danix43.api.database;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import danix43.api.models.Termometre;

@Repository
public interface TermometreRepository extends JpaRepository<Termometre, Integer> {
	
	Iterable<Termometre> findByUsedsensor(String usedSensor);
	Iterable<Termometre> findByMachinename(String name);
	Iterable<Termometre> findByLocation(String location);
	Termometre findById(int id);
}
