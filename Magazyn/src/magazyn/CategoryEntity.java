package magazyn;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="categories")

@NamedQueries({
	@NamedQuery(name="Categories.findAll", query="SELECT e FROM CategoryEntity e")
}) 
public class CategoryEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id 
	private int id;
	@Column(length = 200)
	private String name;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object o) {
		return o instanceof CategoryEntity && id == ((CategoryEntity) o).getId();
	}
	
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + id;
		return result;
	}

	
}
