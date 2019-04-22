package danix43.api.database.repo;

import java.util.List;

import danix43.api.database.Termometre;

public interface TermometreRepositoryCustom {

	List<Termometre> findStationByMachineName(String machineName);
	
}
