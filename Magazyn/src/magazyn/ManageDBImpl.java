package magazyn;

import java.util.Collections;
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
		
		TypedQuery<ItemEntity> query = em.createNamedQuery("Items.findAll", ItemEntity.class);
		return query.getResultList();
	}

	@Override
	public List<ItemEntity> getItemsByName(String queryString) {
		TypedQuery<ItemEntity> query = 
				em.createNamedQuery("Items.searchByName", ItemEntity.class).setParameter("name", queryString);
		return sortItems(query.getResultList(), ItemEntity.SortMode.COMPARE_BY_NAME);
	}

	@Override
	public List<ItemEntity> getItemsByCategory(String queryString) {
		TypedQuery<ItemEntity> query = 
				em.createNamedQuery("Items.searchByCat", ItemEntity.class).setParameter("cat", queryString);
		return sortItems(query.getResultList(), ItemEntity.SortMode.COMPARE_BY_NAME);
	}

	//Sorted by relevance on DB level
	@Override
	public List<ItemEntity> getItemsByDesc(String queryString) {
		TypedQuery<ItemEntity> query = 
				em.createNamedQuery("Items.searchByDesc", ItemEntity.class).setParameter("desc", queryString);
		return query.getResultList();
	}

	@Override
	public List<ItemEntity> sortItems(List<ItemEntity> items, ItemEntity.SortMode mode) {
		
		ItemEntity.setMode(mode);
		Collections.sort(items);
		return items;
	}
	
	
}
