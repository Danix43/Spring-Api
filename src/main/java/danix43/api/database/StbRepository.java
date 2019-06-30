package danix43.api.database.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import danix43.api.database.Stb;

@Repository
public interface StbRepository extends CrudRepository<Stb, Integer> {
	
	List<Stb> findByStatia(String statie);
	List<Stb> findByLinia(String linie);
	
}
