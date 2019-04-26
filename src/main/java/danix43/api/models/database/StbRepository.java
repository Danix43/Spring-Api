package danix43.api.models.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import danix43.api.models.Stb;

@Repository
public interface StbRepository extends JpaRepository<Stb, Integer> {
	
	List<Stb> findByStatia(String statie);
	List<Stb> findByLinia(String linie);
	
}
