package magazyn;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class StockView {
	
	
	@EJB private ManageDAO manage; 
	private List<ItemEntity> items;
	
	public StockView() {}
	
	@PostConstruct
	void init() {
		
		ItemEntity item = new ItemEntity("hehe", 20, "rózne", "takkie rozne produkty", new BigDecimal(120.23));
		ItemEntity item2 = new ItemEntity("hehed", 20, "ródzne", "takdddkie rozne produkty", new BigDecimal(120.23));
		manage.addItem(item2);
		manage.addItem(item);
	}
	
	
	
	public List<ItemEntity> getItems() {
		
		items = manage.getAllItems();
		return items;
	}
	
	
	//GETTERS / SETTERS
	
	
	public void setManage(ManageDAO manage) {
		this.manage = manage;
	}
	
}
