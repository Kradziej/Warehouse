package magazyn;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

@ManagedBean
//@ViewScoped
@RequestScoped
public class SearchView {
	
	public static enum SearchMode { 
		
		BY_NAME("by name"), BY_CAT("by category"), BY_DESC("by description"); 
		
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
	private boolean searchComplete;
	private SearchMode searchMode;
	

	public SearchView() {}


	
	public void search(ActionEvent event) {
		
		System.out.println("dddd");
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
		
		searchComplete = true;
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
	
	public List<ItemEntity.SortMode> getModes() {
		
		return Arrays.asList(ItemEntity.SortMode.values());
	}
	
	public void setSortMode(ItemEntity.SortMode mode) {
		
		ItemEntity.setMode(mode);
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
}
