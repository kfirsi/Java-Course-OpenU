 
/**
 * This class represents a FoodItem object  
 * @version 2020a
 * @author Kfir Sibirsky
 */
public class FoodItem{


	private final int MIN_4_DIGITS_NUM = 1000;
	private final int MAX_4_DIGITS_NUM = 9999;
	private final long DEF_CAT_NUM =9999;
	private final String DEF_ITEM_NAME ="item";
	private final int DEF_QTY = 0;
	private final int DEF_PRICE = 1;
	private final String name;
	private final long catalogueNumber;
	private int quantity;
	private Date productionDate;
	private Date expiryDate;
	private final int minTemperature;
	private final int maxTemperature;
	private int price;

	/**
	 * creates a new FoodItem object
	 * @param name name of food item
	 * @param catalogueNumber catalogue number of food item
	 * @param quantity quantity of food item
	 * @param productionDate production date
	 * @param expiryDate expiry date
	 * @param minTemperature minimum storage temperature
	 * @param maxTemperature maximum storage temperature
	 * @param price unit price
	 */
	public FoodItem(String name, long catalogueNumber, int quantity, Date productionDate, Date expiryDate,
			int minTemperature, int maxTemperature, int price) {
		//if the name is an empty string, this name gets the default value: "name"
		if(name=="")
			this.name=DEF_ITEM_NAME;
		else
			this.name=name;
		//if the catalogue number isn't a positive 4 digit number, this catalogue number gets the default value: 9999
		if(catalogueNumber<MIN_4_DIGITS_NUM || catalogueNumber>MAX_4_DIGITS_NUM)
			this.catalogueNumber=DEF_CAT_NUM;
		else
			this.catalogueNumber=catalogueNumber;
		//if the quantity is a negative number, this quantity gets the default value: 0
		if(quantity < 0)
			this.quantity=DEF_QTY;
		else
			this.quantity=quantity;

		this.productionDate= new Date(productionDate);
		//if the expiry date is before the production date, then this expiry date gets the value of production's date next day.
		if(expiryDate.before(productionDate))
			this.expiryDate= new Date(productionDate.tomorrow());
		else 
			this.expiryDate= new Date(expiryDate);
		//if the minimum temperature is bigger than the maximum temperature, then they switch values.
		if(minTemperature>maxTemperature) {
			this.minTemperature=maxTemperature;
			this.maxTemperature=minTemperature;
		}
		else {
			this.minTemperature=minTemperature;
			this.maxTemperature=maxTemperature;
		}
		//if the price is not a positive number, this price gets the default value: 1
		if(price < 1)
			this.price=DEF_PRICE;
		else
			this.price=price;
	}

	/**
	 * copy constructor
	 * @param other the food item to be copied
	 */
	public FoodItem(FoodItem other) {
		this.name = other.name;
		this.catalogueNumber = other.catalogueNumber;
		this.quantity = other.quantity;
		this.productionDate = other.productionDate;
		this.expiryDate = other.expiryDate;
		this.minTemperature = other.minTemperature;
		this.maxTemperature = other.maxTemperature;
		this.price = other.price;
	}

	/**
	 *  gets the catalogue number
	 * @return the catalogue number
	 */
	public long getCatalogueNumber() {
		return this.catalogueNumber;
	}

	/**
	 * gets the name
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * gets the quantity
	 * @return the quantity
	 */

	public int getQuantity() {
		return this.quantity;
	}

	/**
	 * gets the production date
	 * @return the production date
	 */

	public Date getProductionDate() {
		return new Date(this.productionDate);
	}

	/**
	 * gets the expiry date
	 * @return the expiry date
	 */

	public Date getExpiryDate() {
		return new Date(this.expiryDate);
	}

	/**
	 * gets the minimum storage temperature
	 * @return the minimum storage temperature
	 */

	public int getMinTemperature() {
		return this.minTemperature;
	}

	/**
	 * gets the maximum storage temperature
	 * @return the maximum storage temperature
	 */

	public int getMaxTemperature() {
		return this.maxTemperature;
	}

	/**
	 * gets the unit price
	 * @return the unit price
	 */

	public int getPrice() {
		return this.price;
	}

	/**
	 * set the quantity (only if not negative)
	 * @param n the quantity value to be set
	 */

	public void setQuantity(int quantity) {

		if(quantity>=0)
			this.quantity = quantity;
	}

	/**
	 * set the production date (only if not after expiry date )
	 * @param d production date value to be set
	 */

	public void setProductionDate(Date d) {
		if(d.after(this.expiryDate))
			return;
		this.productionDate = d;
	}

	/**
	 * set the expiry date (only if not before production date )
	 * @param d expiry date value to be set
	 */

	public void setExpiryDate(Date d) {
		if(d.before(this.productionDate))
			return;
		this.expiryDate = d;
	}

	/**
	 * set the price (only if positive)
	 * @param n price value to be set
	 */

	public void setPrice(int price) {
		if(price>0)
			this.price = price;
	}

	/**
	 * check if 2 food items are the same (excluding the quantity values)
	 * @param other the food item to compare this food item to 
	 * @return true if the food items are the same
	 */

	public boolean equals (FoodItem other) {
		return (this.name.equals(other.name) &&
				this.catalogueNumber==other.catalogueNumber &&
				this.productionDate.equals(other.productionDate) &&
				this.expiryDate.equals(other.expiryDate) &&
				this.minTemperature==other.minTemperature &&
				this.maxTemperature==other.maxTemperature &&
				this.price==other.price);
	}

	/** 
	 * check if this food item is fresh on the date d
	 * @param d date to check
	 * @return true if this food item is fresh on the date d
	 */

	public boolean isFresh(Date d) {
		//an item is fresh on a given date if the date is equal to or after the item's production date and,
		//the date is equal to or before the item's expiry date. 
		return((d.equals(this.productionDate)||d.after(this.productionDate))
				&&(d.equals(this.expiryDate)||d.before(this.expiryDate)));
	}

	/**
	 * returns a String that represents this food item
	 * @Override toString in class java.lang.Object
	 * @return String that represents this food item in the following format:
		FoodItem: milk CatalogueNumber: 1234 ProductionDate: 14/12/2019 ExpiryDate: 21/12/2019 Quantity: 3 
	 */

	public String toString() {

		return "FoodItem: " + this.name + "\t"+ "CatalogueNumber: "+this.catalogueNumber +"\t" + "ProductionDate: "
				+ this.productionDate +"\t" + "ExpiryDate: " + this.expiryDate + "\t" + "Quantity: " + this.quantity;
	}

	/**
	 * check if this food item is older than other food item
	 * @param other food item to compare this food item to 
	 * @return true if this food item is older than other date
	 */

	public boolean olderFoodItem(FoodItem other) {
		return other.productionDate.after(this.productionDate);
	}

	/**
	 * returns the number of units of products that can be purchased for a given amount
	 * @param amount amount to purchase
	 * @return the number of units can be purchased
	 */

	public int howManyItems (int amount) {
		//handle cases in which the given amount of money is negative or zero.
		if(amount <= 0)
			return 0;
		//the number of items that can be bought with a given amount of money is calculated
		//if the item's quantity is bigger than or equal to the number of
		//items that can be bought with a given amount of money than that number is returned.
		if(this.quantity>=(amount/this.price))
			return amount/this.price;
		//otherwise return the item's quantity
		return this.quantity;
	}

	/**
	 * check if this food item is cheaper than other food item
	 * @param other food item to compare this food item to
	 * @return true if this food item is cheaper than other date
	 */

	public boolean isCheaper (FoodItem other) { 
		return other.price>this.price;
	}

}
