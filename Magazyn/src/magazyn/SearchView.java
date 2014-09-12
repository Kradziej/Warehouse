package magazyn;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
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
	private ItemEntityComparator comparator;
	private int removeItemId;
	

	public SearchView() {}
	
	
	public void resetView() {
		
		comparator = null;
	}

	
	public void sortTable(ActionEvent event) {
		
		if(comparator == null)
			return;
		
		String sortBy = (String) event.getComponent().getAttributes().get("value");
		sortBy = ("sort_by_" + sortBy).toUpperCase();
		comparator.setMode(ItemEntityComparator.SortMode.valueOf(sortBy));
		
		Collections.sort(items, comparator);
		comparator.setReverse();
	}
	
	
	public void search(ActionEvent event) {
		
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
		
		System.out.println(items);
		System.out.println(isSearchResult());
		comparator = new ItemEntityComparator();
		searchComplete = true;
	}
	
	
	public void removeItem() {
		
		ExternalContext exc = FacesContext.getCurrentInstance().getExternalContext();
		System.out.println("ffff");
		
		PartialViewContext con = FacesContext.getCurrentInstance().getPartialViewContext();
		PartialResponseWriter writer = con.getPartialResponseWriter();
		
		
		try {
			/*writer.startDocument();
			writer.redirect("<?xml version='1.0' encoding='utf-8'?>"+
							"<partial-response>"+
								"<redirect url='/contextpath/faces/ajax/redirecttarget.xhtml'>"+
								"</redirect>"+
								"</partial-response>");
			writer.endDocument();*/
			writer.write("invalid   ");
		} catch (IOException e) {
			System.out.println("Cannot write partial response error");
		}
		
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


	public int getRemoveItemId() {
		return removeItemId;
	}


	public void setRemoveItemId(int removeItemId) {
		this.removeItemId = removeItemId;
	}
}
