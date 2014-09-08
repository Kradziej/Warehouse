package magazyn;

import java.util.List;

public interface ManageDAO {
	
	void addItem(ItemEntity item);
	List<ItemEntity> getAllItems();

}
