package magazyn;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean
@ViewScoped
public class AddView implements Serializable {
	
	 
	@EJB private ManageDAO manage; 

	private String name;
	private int amount;
	private int categoryId;
	private String description;
	private BigDecimal price;
	private List<CategoryEntity> categories;
	
	
	public AddView() {}
	
	
	public void add(ActionEvent event) {
		
		String msg;
		FacesMessage faceMsg = new FacesMessage();
		ItemEntity item = new ItemEntity(name, amount, description, price);
		
		try {
			item = manage.addItem(item, categoryId);
			msg = "New item "+item.getName()+" added to category " + item.getCategory().getName();
			faceMsg.setSummary(msg);
			faceMsg.setSeverity(FacesMessage.SEVERITY_INFO);
		} catch (IllegalArgumentException e) {
			System.out.println("Failed to save item to category of ID " + categoryId);
			msg = "Failed to add item";
			faceMsg.setSummary(msg);
			faceMsg.setSeverity(FacesMessage.SEVERITY_ERROR);
		}
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
	}

	
	

	
	// GETTERS / SETTERS


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public ManageDAO getManage() {
		return manage;
	}
	
	public void setManage(ManageDAO manage) {
		this.manage = manage;
	}


	public List<CategoryEntity> getCategories() {
		
		if(categories == null)
			categories = manage.getAllCategories();
		
		return categories;
	}

}
