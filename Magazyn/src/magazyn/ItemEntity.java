package magazyn;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the items database table.
 * 
 */
@Entity
@Table(name="items")
@NamedQueries({
	@NamedQuery(name="Item.findAll", query="SELECT i FROM ItemEntity i")
}) 

public class ItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Lob
	private String name;
	private int amount;
	@Lob
	private String category;
	@Lob
	private String description;
	private BigDecimal price;
	

	public ItemEntity() {}
	
	public ItemEntity(String name, int amount, String category, String description, BigDecimal price) {
		
		this.name = name;
		this.amount = amount;
		this.category = category;
		this.description = description;
		this.price = price;
	}
	
	
	//GETTERS / SETTERS
	
	

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}