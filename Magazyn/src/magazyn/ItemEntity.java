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
	@NamedQuery(name="Items.findAll", query="SELECT e FROM ItemEntity e"),
	@NamedQuery(name="Items.searchByName", query="SELECT e FROM ItemEntity e WHERE e.name LIKE '%:name%'"),
	@NamedQuery(name="Items.searchByCat", query="SELECT e FROM ItemEntity e WHERE e.category LIKE '%:cat%'"),
	@NamedQuery(name="Items.searchByDesc", query="SELECT e MATCH(e.description) AGAINST(':desc') AS rel "
			+ "FROM ItemEntity e WHERE MATCH(e.description) AGAINST(':desc')")
}) 
public class ItemEntity implements Serializable, Comparable<ItemEntity> {
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
	
	
	public static enum SortMode { 
		COMPARE_BY_NAME("Name"), COMPARE_BY_CAT("Category"), COMPARE_BY_PRICE("Price");
		
		private String name;
		
		SortMode(String name) {
			
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
	}
	private static SortMode mode;

	public ItemEntity() {}
	
	public ItemEntity(String name, int amount, String category, String description, BigDecimal price) {
		
		this.name = name;
		this.amount = amount;
		this.category = category;
		this.description = description;
		this.price = price;
	}
	
	
	@Override
	public int compareTo(ItemEntity o) {
		
		switch(mode) {
		
		case COMPARE_BY_NAME:
			return String.CASE_INSENSITIVE_ORDER.compare(name, o.getName());
		case COMPARE_BY_CAT:
			return String.CASE_INSENSITIVE_ORDER.compare(category, o.getCategory());
		case COMPARE_BY_PRICE:
			return price.compareTo(o.getPrice());
			
		}
		return 0;
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

	public static void setMode(SortMode mode) {
		ItemEntity.mode = mode;
	}

	public static SortMode getMode() {
		return mode;
	}

}