package danix43.api.database.repo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import danix43.api.database.Termometre;

public class TermometreRepositoryImpl implements TermometreRepositoryCustom {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Termometre> findStationByMachineName(String machineName) {
		
	    CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<Termometre> cq = cb.createQuery(Termometre.class);
	 
	    Root<Termometre> ter = cq.from(Termometre.class);
	    List<Predicate> predicates = new ArrayList<>();
	     
	    if (machineName != null) {
	        predicates.add(cb.equal(ter.get("machinename"), machineName));
	    }
	    
	    cq.where(predicates.toArray(new Predicate[0]));
	 
	    return em.createQuery(cq).getResultList();
	}

}
