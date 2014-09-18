package magazyn;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.PartialResponseWriter;
import javax.faces.context.PartialViewContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.context.PrimePartialViewContext;

import com.sun.corba.se.spi.orbutil.fsm.Action;

@ManagedBean
@ViewScoped
//@RequestScoped
public class SearchView implements Serializable {
	
	public static enum SearchMode { 
		
		BY_NAME("by name"), BY_CAT("by category"), BY_DESC("by description"), BY_PRICE("by price"); 
		
		private String name;
		
		SearchMode(String name) {
			
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
	
	}
	
	
	@EJB private ManageDAO manage; 
	private List<ItemEntity> items;
	private String searchQuery;
	private BigDecimal searchPriceTo;
	private BigDecimal searchPriceFrom;
	private boolean searchComplete;
	private SearchMode searchMode;
	//not serializable
	private transient ItemEntityComparator comparator;
	private boolean editMode;
	private boolean rangeMode;
	private List<CategoryEntity> categories;
	private Map<Integer, Boolean> editModes;

	public SearchView() {}
	
	
	public void resetView() {
		
		comparator = null;
		editMode = false;
		rangeMode = false;
	}

	
	public void sortTable(String sortBy) {
		
		if(comparator == null)
			return;
		
		System.out.println(sortBy);
		//String sortBy = (String) event.getComponent().getAttributes().get("value");
		sortBy = ("sort_by_" + sortBy).toUpperCase();
		comparator.setMode(ItemEntityComparator.SortMode.valueOf(sortBy));
		
		Collections.sort(items, comparator);
		comparator.setReverse();
	}
	
	
	public void search(ActionEvent event) {
		
		//Reset edit state
		editMode = false;
		
		switch(searchMode) {
		
		case BY_NAME:
			items = manage.getItemsByName(searchQuery);
			break;
		case BY_CAT:
			items = manage.getItemsByCategory(searchQuery);
			break;
		case BY_DESC:
			items = manage.getItemsByDesc(searchQuery);
			break;
		}
		
		comparator = new ItemEntityComparator(-1);
		searchComplete = true;
		editModes = new HashMap<>();
		for(ItemEntity c : items) {
			
			editModes.put(c.getId(), false);
		}
	}
	
	
	public void edit(ActionEvent event) {
		
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		int id = Integer.parseInt(params.get("itemId"));
		
		editMode = !editModes.get(id);
		editModes.put(id, !editModes.get(id));
		
	}
	
	public void editItem(ItemEntity item) {
		
		System.out.print(item.getCategory().getId());
		manage.updateItem(item);
		editMode = !editModes.get(item.getId());
		editModes.put(item.getId(), !editModes.get(item.getId()));
	}
	
	
	public void selectSearchMode() {
		
		if(searchMode == SearchMode.BY_PRICE)
			rangeMode = true;
		else
			rangeMode = false;
	}
	
	
	public void removeItem() {
		
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		// try catch here for database error ???????
		int id = Integer.parseInt(params.get("removeItemId"));
		manage.deleteItem(id);
		
	}
	

	//GETTERS / SETTERS
	
	
	public void setManage(ManageDAO manage) {
		this.manage = manage;
	}


	public String getSearch() {
		return searchQuery;
	}

	public void setSearch(String searchQuery) {
		this.searchQuery = searchQuery;
	}

	public List<SearchMode> getSearchModes() {
		return Arrays.asList(SearchMode.values());
	}

	public void setSearchMode(SearchMode mode) {
		this.searchMode = mode;
	}
	
	public SearchMode getSearchMode() {
		return searchMode;
	}

	public boolean isSearchResult() {
		
		return searchComplete;
	}

	public List<ItemEntity> getItems() {
		return items;
	}

	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}


	public boolean isEditMode() {
		return editMode;
	}

	public boolean isRangeMode() {
		return rangeMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	public List<CategoryEntity> getCategories() {
		
		if(categories == null) {
			categories = manage.getAllCategories();
		}
		
		return categories;
	}


	public BigDecimal getSearchPriceTo() {
		return searchPriceTo;
	}


	public void setSearchPriceTo(BigDecimal searchPriceTo) {
		this.searchPriceTo = searchPriceTo;
	}


	public BigDecimal getSearchPriceFrom() {
		return searchPriceFrom;
	}


	public void setSearchPriceFrom(BigDecimal searchPriceFrom) {
		this.searchPriceFrom = searchPriceFrom;
	}
}
