package magazyn;

import java.util.Comparator;

public class ItemEntityComparator implements Comparator<ItemEntity> {

	
	private int reverse;
	private SortMode mode;
	 
	public ItemEntityComparator() {
		reverse = 1;
	}
	
	public ItemEntityComparator(SortMode mode) {
		
		this.mode = mode;
		reverse = 1;
	}
	
	public ItemEntityComparator(int reverseMode) {

		this.reverse = reverseMode;
	}
	
	
	public ItemEntityComparator(SortMode mode, int reverseMode) {
		
		this.mode = mode;
		this.reverse = reverseMode;
	}
	
	public static enum SortMode { 
		
		SORT_BY_NAME("Name"), SORT_BY_PRICE("Price"), 
		SORT_BY_AMOUNT("Amount"), SORT_BY_CATEGORY("Category");
		
		private String name;
		
		SortMode(String name) {
			
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
	}
	
	@Override
	public int compare(ItemEntity o1, ItemEntity o2) {
		
		
		switch(mode) {
		
			case SORT_BY_NAME:
				return reverse * String.CASE_INSENSITIVE_ORDER.compare(o1.getName(), o2.getName());
			case SORT_BY_CATEGORY:
				return reverse * String.CASE_INSENSITIVE_ORDER.compare(o1.getCategory().getName(), o2.getCategory().getName());
			case SORT_BY_PRICE:
				return reverse * o1.getPrice().compareTo(o2.getPrice());
			case SORT_BY_AMOUNT:
				return reverse * Integer.compare(o1.getAmount(), o2.getAmount());
			
		}
		return 0;
	}

	public int getReverse() {
		return reverse;
	}
	
	public void setReverse() {
		this.reverse *= -1;
	}

	public SortMode getMode() {
		return mode;
	}

	public void setMode(SortMode mode) {
		this.mode = mode;
	}
	
	
}
