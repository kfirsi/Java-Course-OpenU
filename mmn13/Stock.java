
/**
 * This class represents a FoodItem stock object.
 * @version 2020a
 * @author Kfir Sibirsky
 */
public class Stock {
	private final int MAX_STOCK_SIZE=100;
	private FoodItem[] _stock;
	private int _noOfItems;
	/**
	 * creates a new FoodItems stock object
	 */
	public Stock () {

		_stock = new FoodItem[MAX_STOCK_SIZE];
		_noOfItems=0;
	}
	/**
	 * gets the number of items in stock 
	 * @return the number of items in stock
	 */
	public int getNumOfItems () {
		return _noOfItems;
	}
	/**
	 * add an item to the stock
	 * if the given item already exist in stock, just add the quantity to the existing one
	 * if the given item matches an existing item's name or catalogue number, it considered a different item
	 * @param newItem new item to add to stock
	 * @return true if the new item had been added
	 */
	public boolean addItem (FoodItem newItem) {

		for(int i=0; i<_noOfItems;i++) {
			//check if given item matches an existing item's name or catalogue number
			if( (!_stock[i].getProductionDate().equals(newItem.getProductionDate()) ||
					!_stock[i].getExpiryDate().equals(newItem.getExpiryDate())) &&
					_stock[i].getName().equals(newItem.getName())&&
					_stock[i].getCatalogueNumber() == newItem.getCatalogueNumber()){
				//shift right every item to make space for new item
				for (int j = _noOfItems+1; j > i; j--)
					_stock[j]= _stock[j-1];

				_stock[i]= new FoodItem(newItem);
				_noOfItems++;
				sortByCatNum();
				return true;
			}
			//check if given item already exist in stock
			if(_stock[i].equals(newItem)||sameButQuantity(newItem, i)) {
				_stock[i].setQuantity(_stock[i].getQuantity()+newItem.getQuantity());
				sortByCatNum();
				return false;
			}
		}

		//in case given item is completely different from anything in stock
		_stock[_noOfItems] = new FoodItem(newItem);
		_noOfItems++;
		sortByCatNum();
		return true;
	}

	/**
	 * update the stock according to a given items list
	 * @param itemsList list of items needed to update their quantities
	 * @return the number of items that can survive in a given temperature
	 */
	public void updateStock(String[] itemsList) {

		sortByName(itemsList);//sort alphabetically itemsList
		sortByName(_stock);//sort alphabetically _stock
		int countItems=0;//counts how many items left after update
		for (int i = 0; i < itemsList.length; i++)
			updateQty(itemsList[i]);//decrease quantity by 1 if needed

		sortByCatNum();
		//eliminating "holes" in stock array by coping stock's items to a new stock
		FoodItem[] newStock = new FoodItem[MAX_STOCK_SIZE];
		int j=0;// index for the new stock
		for (int i = 0; i < _noOfItems; i++) {
			if(_stock[i].getQuantity()>0) {
				countItems++;
				newStock[j] = new FoodItem(_stock[i]);
				j++;
			}
		}
		_stock = newStock;
		this._noOfItems = countItems;//update the number of items
		sortByCatNum();
	}
	/**
	 * gets the list of items that has a lower quantity than that of a given amount
	 * @param amount amount to compare to the stock's items
	 * @return the list of items that has a lower quantity than that of a given amount
	 */
	public String order( int amount) {
		String toOrder = "";
		String[] itemsNames = new String[_noOfItems];
		sortByCatNum();

		/* in the next few lines of code we check for each item if there are other items
		   with the same name and catalogue number as of it's, if so we compare their total quantity
		   to the given amount. if compatible, hold the name of the item in a array of names */

		for (int i = 0; i < _noOfItems; i++) {
			if(totalQuantity(_stock[i])<amount) {
				if(notExist(itemsNames, _stock[i].getName()))
					itemsNames[i] = _stock[i].getName();
			}
		}
		//copy the names from itemsNames to the string toOrder
		for (int i = 0; i < itemsNames.length; i++) {
			if(itemsNames[i]!= null)
				toOrder += itemsNames[i] +", ";
		}
		//remove the , at the end of toOrder
		if(toOrder!="")
			toOrder= toOrder.substring(0,toOrder.length()-2);
		return toOrder;
	}
	/**
	 * gets the number of items that can survive in a given temperature (of a refrigerator)
	 * if stock is empty, return 0
	 * @param temp temperature of the refrigerator 
	 * @return the number of items that can survive in a given temperature
	 */
	public int howMany(int temp) {
		if(_noOfItems==0)
			return 0;
		int total=0;//sum the quantity of compatible items
		String[] itemsNames = new String[_noOfItems];
		for (int i = 0; i < _noOfItems; i++) {
			//check whether or not an item can survive in a given temperature
			if(temp >=_stock[i].getMinTemperature() && temp <=_stock[i].getMaxTemperature()) {

				/* in the next few lines of code we check for each item if there are other items
				   with the same name and catalogue number as of it's, if so we add their total quantity
				   to total and hold the name of the item in a array of names in order to not add more
				   than once the total quantity of those items, we do so by using notExist method */

				if(totalQuantity(_stock[i]) != _stock[i].getQuantity()) {
					if(notExist(itemsNames, _stock[i].getName())) {
						itemsNames[i] = new String(_stock[i].getName());
						total += totalQuantity(_stock[i]);
					}
				}
				else 
					total += _stock[i].getQuantity();
			}
		}
		return total;
	}
	/**
	 * gets the number of items in stock. if stock is empty, return 0
	 * @return the number of items in stock
	 */
	public int howManyPieces() {
		if(_noOfItems==0)
			return 0;
		int total = 0;//sum the quantity of all items in stock
		for (int i = 0; i < _noOfItems; i++) {
			total += _stock[i].getQuantity();
		}
		return total;
	}
	/**
	 * remove from stock every item that is expired at a given date
	 * @param d date to compare the stock's items's expiration dates with
	 */
	public void removeAfterDate (Date d) {
		boolean hasExpired = false;//flag for indicating removal
		int countItems = 0;//counts how many items left after removal
		for (int i = 0; i < _noOfItems; i++) {
			if(_stock[i].getExpiryDate().before(d)) {
				hasExpired=true;

				_stock[i]=null;//removing the item from stock
			}
		}
		if(hasExpired) {//if an item had been removed
			//eliminating "holes" in stock array by coping stock's items to a new stock
			FoodItem[] newStock = new FoodItem[MAX_STOCK_SIZE];
			int j=0;// index for the new stock
			for (int i = 0; i < _noOfItems; i++) {
				if(_stock[i]!=null) {
					countItems++;
					newStock[j] = new FoodItem(_stock[i]);
					j++;
				}
			}
			_stock = newStock;
			this._noOfItems = countItems;//update the number of items
			sortByCatNum();
		}
	}
	/**
	 * gets the most expensive item in stock
	 * if the stock is empty, return null
	 * @return the most expensive item in stock
	 */
	public FoodItem mostExpensive() {
		if(_noOfItems==0)
			return null;
		//finding the most expensive item in stock
		FoodItem max = new FoodItem(_stock[0]);
		for (int i = 1; i < _noOfItems; i++) {
			if(_stock[i].getPrice() > max.getPrice())
				max = new FoodItem(_stock[i]);
		}
		return new FoodItem(max);
	}
	/**
	 * gets the minimal temperature of the range of temperatures 
	 * in which all items in stock can survive.
	 * if the stock is empty or there is no such range,
	 * the method return Integer.MAX_VALUE
	 * @return the minimal temperature of stock in which all items in stock can survive.
	 */

	public int getTempOfStock() {

		if(_noOfItems == 0)
			return Integer.MAX_VALUE;

		int tempOfStock = Integer.MIN_VALUE , startOfRange, endOfRange;

		for (int i = 0; i < _noOfItems-1; i++) 
		{ 
			//calculates and hold the max of the two low ends
			startOfRange = Math.max(_stock[i].getMinTemperature(),_stock[i+1].getMinTemperature());
			//calculates and hold the min of the two high ends
			endOfRange = Math.min(_stock[i].getMaxTemperature(),_stock[i+1].getMaxTemperature());
			if(startOfRange <= endOfRange) {//check if range (still) exist
				if(tempOfStock <= startOfRange)//check if need to update the start of range 
					tempOfStock = startOfRange;
			}
			else //there is no such range
				return Integer.MAX_VALUE;
		}
		return tempOfStock;
	}
	/**
	 * returns a String that represents this FoodItems stock 
	 * @Override toString in class java.lang.Object
	 * @return String that represents this FoodItems stock in the following format:
	 * name:___ Catalogue Number:___ Production Date:___ Expiry Date:___ Quantity:___ for example:
	 * FoodItem: Milk	CatalogueNumber: 1111	ProductionDate: 01/01/2000	ExpiryDate: 01/01/2001	Quantity: 12
	 */
	public String toString() {
		sortByCatNum();
		String stk = "";
		for (int i = 0; i < _noOfItems; i++)
			stk += "\n"+_stock[i];
		return stk;
	}

	/*
	 * checks whether or not a given array of names contains a given name
	 * return true if so and false otherwise
	 */
	private boolean notExist(String[] itemsNames, String name) {
		for (int i = 0; i < itemsNames.length; i++) {
			if(name.equals(itemsNames[i]))
				return false;
		}
		return true;
	}
	/*
	 * gets the total quantity of all items that has the same 
	 * name and catalogue number as of a given food item
	 */
	private int totalQuantity(FoodItem f) {
		int total = 0;
		for (int i = 0; i < _noOfItems; i++) {
			if(f.getName().equals(_stock[i].getName()) &&
					f.getCatalogueNumber() == _stock[i].getCatalogueNumber()) {
				//add the quantity of _stock[i] to total if the name and catalogue
				//number of the given food item match those of _stock[i]
				total += _stock[i].getQuantity();
			}
		}
		return total;
	}
	/*
	 * sort by catalogue number the stock 
	 */
	private void sortByCatNum() {
		FoodItem temp;
		for (int i = 0; i < _noOfItems; i++) {
			for (int j = 0; j < _noOfItems-i-1; j++) { //every cell after _noOfItems-i-1 had already been sorted
				if (_stock[j].getCatalogueNumber()>_stock[j+1].getCatalogueNumber()) {
					//the values of a cell and it's following cell are swapped
					temp = _stock[j]; 
					_stock[j] = _stock[j+1]; 
					_stock[j+1] = temp; 
				} 
			}
		}
	}
	/*
	 * sort alphabetically a given FoodItem array
	 */
	private void sortByName(FoodItem[] f) {
		FoodItem temp;
		for (int i = 0; i < _noOfItems; i++) {
			for (int j = 0; j < _noOfItems-i-1; j++) { //every cell after _noOfItems-i-1 had already been sorted
				if (f[j].getName().compareTo(f[j+1].getName())>0) {
					//the values of a cell and it's following cell are swapped
					temp = _stock[j]; 
					_stock[j] = _stock[j+1]; 
					_stock[j+1] = temp; 
				} 
			}
		}
	}
	/*
	 * sort alphabetically a given String array
	 */
	private String[] sortByName(String[] s) {
		String temp;
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s.length-i-1; j++) {//every cell after s.length-i-1 had already been sorted
				if (s[j].compareTo(s[j+1])>0) {
					//the values of a cell and it's following cell are swapped
					temp = s[j]; 
					s[j] = s[j+1]; 
					s[j+1] = temp; 
				} 
			} 
		}
		return s;
	}
	/*
	 * gets the boolean value of whether or not an item in a given location in the stock 
	 * array has the same properties values except for the quantity as of a given item 
	 */
	private boolean sameButQuantity (FoodItem newItem, int loc) {

		if(_stock[loc].getProductionDate().equals(newItem.getProductionDate()) &&
				_stock[loc].getName().equals(newItem.getName())&&
				_stock[loc].getCatalogueNumber() == newItem.getCatalogueNumber()&&
				_stock[loc].getExpiryDate().equals(newItem.getExpiryDate()) &&
				_stock[loc].getMaxTemperature() == newItem.getMaxTemperature() &&
				_stock[loc].getMinTemperature() == newItem.getMinTemperature()&&
				_stock[loc].getPrice() == newItem.getPrice()&&
				_stock[loc].getQuantity() != newItem.getQuantity()) {
			return true;
		}
		return false;	
	}

	/*
	 * check whether or not a given item name matches an item name from the stock.
	 * if so, decrease the quantity of the matched item by 1.
	 * return the boolean value of whether or not a change in the quantity had been made
	 */
	private boolean updateQty(String name) {
		for (int i = 0; i < _noOfItems; i++) {
			if(_stock[i].getName().equals(name)) {
				if(_stock[i].getQuantity()>0) {
					_stock[i].setQuantity(_stock[i].getQuantity()-1);
					return true;
				}
			}
		}
		return false;
	}

}
