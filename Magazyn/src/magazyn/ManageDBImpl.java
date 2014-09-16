package magazyn;

import java.util.ArrayList;
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
	public ItemEntity addItem(ItemEntity item, int categoryId) throws IllegalArgumentException {
		
		CategoryEntity cat = em.find(CategoryEntity.class, categoryId);
		item.setCategory(cat);
		em.persist(item);
		em.flush();
		return item;	
	}
	
	@Override
	public List<ItemEntity> getAllItems() {
		
		TypedQuery<ItemEntity> query = em.createNamedQuery("Items.findAll", ItemEntity.class);
		return query.getResultList();
	}
	
	@Override
	public List<CategoryEntity> getAllCategories() {
		
		TypedQuery<CategoryEntity> query = em.createNamedQuery("Categories.findAll", CategoryEntity.class);
		return query.getResultList();
	}

	@Override
	public List<ItemEntity> getItemsByName(String queryString) {
		TypedQuery<ItemEntity> query = 
				em.createNamedQuery("Items.searchByName", ItemEntity.class).setParameter("name", "%" + queryString + "%");
		return sortItems(query.getResultList(), ItemEntityComparator.SortMode.SORT_BY_NAME);
	}

	@Override
	public List<ItemEntity> getItemsByCategory(String queryString) {
		TypedQuery<ItemEntity> query = 
				em.createNamedQuery("Items.searchByCat", ItemEntity.class).setParameter("cat", "%" + queryString + "%");
		return sortItems(query.getResultList(), ItemEntityComparator.SortMode.SORT_BY_NAME);
	}

	/*
	//Sorted by relevance on DB level
	@Override
	public List<ItemEntity> getItemsByDesc(String queryString) {
		TypedQuery<ItemEntity> query = 
				em.createNamedQuery("Items.searchByDesc", ItemEntity.class).setParameter("desc", queryString);
		return query.getResultList();
	}*/
	
	@Override
	public List<ItemEntity> getItemsByDesc(String queryString) {
		@SuppressWarnings("unchecked")
		List<ItemEntity> itemEn = (List<ItemEntity>) em.createNativeQuery("SELECT *, MATCH (description) AGAINST (?1) "
				+ "AS rel FROM items WHERE MATCH (description) "
				+ "AGAINST (?2)", ItemEntity.class).setParameter(1, queryString).setParameter(2, queryString).getResultList();
		return itemEn;
	}

	@Override
	public List<ItemEntity> sortItems(List<ItemEntity> items, Object mode) {
		ItemEntityComparator.SortMode sortMode = (ItemEntityComparator.SortMode) mode;
		List<ItemEntity> itemsCopy = new ArrayList<>(items);
		Collections.sort(itemsCopy, new ItemEntityComparator(sortMode));
		return itemsCopy;
	}

	@Override
	public void deleteItem(int id) {
		
		ItemEntity item = em.find(ItemEntity.class, id);
		em.remove(item);
		em.flush();
	}
	
	
}
