 
/**
 * This class represents a Date object.
 * @version 2020a
 * @author Kfir Sibirsky
 */
public class Date {

	private final int JANUARY = 1;
	private final int FEBRUARY = 2;
	private final int MARCH = 3;
	private final int APRIL = 4;
	private final int MAY = 5;
	private final int JUNE = 6;
	private final int JULY = 7;
	private final int AUGUST = 8;
	private final int SEPTEMBER = 9;
	private final int OCTOBER = 10;
	private final int NOVEMBER = 11;
	private final int DECEMBER = 12;

	private final int MAX_DAY_28 = 28; 
	private final int MAX_DAY_29 = 29; 
	private final int MAX_DAY_30 = 30; 
	private final int MAX_DAY_31 = 31; 
	private final int MAX_VAL_YEAR = 9999;
	private final int MAX_VAL_MONTH = 12;

	private final int MIN_VAL_DAY = 1;
	private final int MIN_VAL_MONTH = 1;
	private final int MIN_VAL_YEAR = 1000;
	private final int MIN_TWO_DIGIT_NUM = 10;

	private final int DEF_DAY = 1;
	private final int DEF_MONTH = 1;
	private final int DEF_YEAR = 2000;

	private int _day;
	private int _month;
	private int _year;
	/**
	 * creates a new Date object if the date is valid, otherwise creates the date 1/1/2000
	 * @param day the day in the month (1-31)
	 * @param month the month in the year (1-12)
	 * @param year the year (4 digits)
	 */
	public Date(int day, int month, int year) {

		if(IsDateVaild(day, month, year)) { 
			this._day=day;
			this._month=month;
			this._year=year;
		}
		else {
			this._day=DEF_DAY;
			this._month=DEF_MONTH;
			this._year=DEF_YEAR;
		}
	}
	/**
	 * copy constructor
	 * @param other the date to be copied
	 */
	public Date(Date other) {
		this._day=other._day;
		this._month=other._month;
		this._year=other._year;
	}
	/**
	 * gets the day 
	 * @return the day
	 */
	public int getDay() {
		return this._day;
	}
	/**
	 * gets the month 
	 * @return the month
	 */
	public int getMonth() {
		return this._month;
	}
	/**
	 * gets the year 
	 * @return the year
	 */
	public int getYear() {
		return this._year;
	}
	/**
	 * sets the day (only if date remains valid)
	 * @param dayToSet the day value to be set
	 */
	public void setDay(int dayToSet) {

		if(IsDateVaild(dayToSet, this._month, this._year))
			this._day=dayToSet;
	}
	/**
	 * set the month (only if date remains valid) 
	 * @param monthToSet the month value to be set
	 */
	public void setMonth(int monthToSet) {

		if(IsDateVaild(this._day, monthToSet, this._year))
			this._month=monthToSet;
	}
	/**
	 * sets the year (only if date remains valid) 
	 * @param yearToSet the year value to be set
	 */
	public void setYear(int yearToSet) {

		if(IsDateVaild(this._day, this._month, yearToSet))
			this._year=yearToSet;
	}
	/**
	 * check if 2 dates are the same
	 * @param other the date to compare this date to 
	 * @return true if the dates are the same
	 */
	public boolean equals (Date other) {
		// return whether or not the years and the months and the days match.
		return (this._day == other._day && this._month == other._month && this._year == other._year);
	}
	/**
	 * check if this date is before other date 
	 * @param other date to compare this date to
	 * @return true if this date is before other date
	 */
	public boolean before (Date other) {
		// return whether or not the year is before other's year or,
		// the years matches but the month is before other's month or,
		// the years and the months matches but the day is before other's day.
		return(this._year<other._year||(this._year==other._year&&this._month<other._month)
				||(this._year==other._year&&this._month==other._month&&this._day<other._day));
	}	
	/**
	 * check if this date is after other date 
	 * @param other date to compare this date to
	 * @return true if this date is after other date
	 */
	public boolean after (Date other) {
		//returns if this date is after the other date by calling the before method in reverse order.
		//which means this date is after other date iff other date is before this date.
		return other.before(this);
	}
	/**
	 * calculates the difference in days between two dates
	 * @param other the date to calculate the difference between
	 * @return the number of days between the dates
	 */
	public int difference (Date other) {
		// calculates the difference in days between two dates by calculating the absolute value of subtracting the
		// number of days since the beginning of the Christian counting of years for each one of the dates.
		return Math.abs(calculateDays(this._day, this._month, this._year)-calculateDays(other._day, other._month, other._year));
	}
	/**
	 * returns a String that represents this date 
	 * @Override toString in class java.lang.Object
	 * @return String that represents this date in the following format: day/month/year for example: 02/03/1998
	 */
	public String toString() {
		return (this._day<MIN_TWO_DIGIT_NUM? "0" : "") + this._day + "/" 
				+(this._month<MIN_TWO_DIGIT_NUM? "0" : "")+ this._month + "/" + this._year;
	}
	/**
	 * calculate the date of tomorrow 
	 * @return the date of tomorrow
	 */
	public Date tomorrow() {

		switch (this._month) {
		// in January, March, May, July, August, October, and December, the last day is the 31st.
		// 	if the day is the 31st in those months, the next day is the 1st of the next month.
		// 	unless it December in which case the next day is the 01.01.year+1
		case JANUARY:
		case MARCH:
		case MAY:
		case JULY:
		case AUGUST:
		case OCTOBER:
		case DECEMBER:
			if(this._month == DECEMBER && this._day==MAX_DAY_31)
				return new Date(MIN_VAL_DAY,JANUARY,this._year+1);
			if(this._day==MAX_DAY_31)
				return new Date(MIN_VAL_DAY,this._month+1,this._year);
			break;
			// in April, June, September and November, the last day is the 30th.
			// 	if the day is the 30th in those months, the next day is the 1st of the next month.
		case APRIL:
		case JUNE:
		case SEPTEMBER:
		case NOVEMBER:
			if(this._day==MAX_DAY_30)
				return new Date(MIN_VAL_DAY,this._month+1,this._year);
			break;
			// in February, the last day is the 28th, unless it is a leap year where the last day is the 29th.
			// 	if the day is the 29th or the 28th but the year is not a leap year, the next day is the 1st of the next month.
		case FEBRUARY:
			if(this._day==MAX_DAY_29||(this._day==MAX_DAY_28 && !isLeapYear(this._year))) 
				return new Date(MIN_VAL_DAY,MARCH,this._year);
			break;
		}
		// in all other cases, tomorrow is just the day+1, in the same month and year.
		return new Date(this._day+1,this._month,this._year);
	}
	/**
	 * calculate the day of the week that this date occurs on 0-Saturday 1-Sunday 2-Monday etc. 
	 * @return the day of the week that this date occurs on
	 */
	public int dayInWeek() {
		int DAY;
		int D = this._day;
		int M = this._month;
		int Y = this._year % 100;
		int C = this._year / 100;
		if(M<3) {
			Y--;
			M+=12;
		}
		DAY = Math.floorMod((D + (26*(M+1))/10 + Y + Y/4 + C/4 - 2*C),7);
		return DAY;
	}



	private boolean isLeapYear(int year) {
		//this method checks whether or not a given year is a leap year.
		//(divisible by 4 and not by 100, or divisible by 400).
		return ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0));
	}

	// computes the day number since the beginning of the Christian counting of years
	private int calculateDays(int day, int month, int year) {

		if(month<3) {
			year--;
			month+=12;
		}
		return 365 * year + year/4 - year/100 + year/400 + ((month+1) * 306)/10 +(day-62);
	}

	private boolean IsDateVaild(int day, int month, int year) {

		// checks if day, month, and year are not within the bounds, 
		// and return false if so.
		// day(1-31),month(1-12),year(4 digits).
		if (day < MIN_VAL_DAY || day > MAX_DAY_31 ||
				month < MIN_VAL_MONTH || month > MAX_VAL_MONTH ||
				year < MIN_VAL_YEAR || year > MAX_VAL_YEAR) 
			return false;

		// checks if the day does not fit the month and year, and return false if so.
		switch (month) {
		// in February (last day is the 28th, unless it is a leap year where the last day is the 29th).
		case FEBRUARY:
			if(day>MAX_DAY_29 || (day == MAX_DAY_29 && !isLeapYear(year)))
				return false;
			break;
			// in April, June, September and November (last day is the 30th).
		case APRIL:
		case JUNE:
		case SEPTEMBER:
		case NOVEMBER:
			if(day==MAX_DAY_31)
				return false;
			break;
		}
		// in all other cases, the date is valid, therefore returns true.
		return true;
	}
}
