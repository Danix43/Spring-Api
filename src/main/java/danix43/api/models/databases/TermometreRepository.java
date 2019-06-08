package danix43.api.models.databases;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import danix43.api.models.Termometre;

@Repository
public interface TermometreRepository extends JpaRepository<Termometre, Integer> {
	
	Termometre findByUsedsensor(String usedSensor);
	Termometre findByMachinename(String name);
	Termometre findByLocation(String location);
	Termometre findById(int id);
}
