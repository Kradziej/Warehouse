package magazyn;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


@Stateless
@Local(ManageDAO.class)
public class ManageDBImpl implements ManageDAO {

	
	@PersistenceContext
	private EntityManager em;
	
	public ManageDBImpl() {}

	@Override
	public void addItem(ItemEntity item) {
		
		em.persist(item);
		em.flush();
	}
	
	
	public List<ItemEntity> getAllItems() {
		
		TypedQuery<ItemEntity> query = em.createNamedQuery("Item.findAll", ItemEntity.class);
		return query.getResultList();
	}
	
	
}
