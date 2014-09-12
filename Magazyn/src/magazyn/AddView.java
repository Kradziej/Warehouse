package magazyn;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@ManagedBean
@RequestScoped
public class AddView {
	
	
	@EJB private ManageDAO manage; 

	private String name;
	private int amount;
	private String category;
	private String description;
	private BigDecimal price;
	
	
	AddView() {}
	
	
	public void add(ActionEvent event) {
		
		
	}


	
	// GETTERS / SETTERS

	public void setManage(ManageDAO manage) {
		this.manage = manage;
	}


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


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
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
	

}
