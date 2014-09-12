package magazyn;

import java.util.List;

public interface ManageDAO {
	
	void addItem(ItemEntity item);
	void deleteItem(int id);
	List<ItemEntity> getAllItems();
	List<ItemEntity> getItemsByName(String queryString);
	List<ItemEntity> getItemsByCategory(String queryString);
	List<ItemEntity> getItemsByDesc(String queryString);
	List<ItemEntity> sortItems(List<ItemEntity> items, Object mode);

}
