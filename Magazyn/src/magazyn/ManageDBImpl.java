package magazyn;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
@Local(ManageDAO.class)
public class ManageDBImpl implements ManageDAO {

	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void addItem(ItemEntity item) {
		
		em.persist(item);
		em.flush();
	}

	
	
	
}
