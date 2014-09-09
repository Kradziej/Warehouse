package magazyn;

import java.util.List;

public interface ManageDAO {
	
	void addItem(ItemEntity item);
	List<ItemEntity> getAllItems();
	List<ItemEntity> getItemsByName(String queryString);
	List<ItemEntity> getItemsByCategory(String queryString);
	List<ItemEntity> getItemsByDesc(String queryString);
	List<ItemEntity> sortItems(List<ItemEntity> items, ItemEntity.SortMode mode);

}
