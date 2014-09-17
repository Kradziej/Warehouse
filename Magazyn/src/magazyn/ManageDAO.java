package magazyn;

import java.util.List;

public interface ManageDAO {
	
	ItemEntity addItem(ItemEntity item, int categoryId);
	void deleteItem(int id);
	void updateItem(ItemEntity item);
	List<ItemEntity> getAllItems();
	List<CategoryEntity> getAllCategories();
	List<ItemEntity> getItemsByName(String queryString);
	List<ItemEntity> getItemsByCategory(String queryString);
	List<ItemEntity> getItemsByDesc(String queryString);
	List<ItemEntity> sortItems(List<ItemEntity> items, Object mode);

}
