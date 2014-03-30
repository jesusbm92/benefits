package domain;

/**
 * This class constains all the constants for the days of the week.
 * 
 * @author F�lix Miguel Sanju�n Segovia <felsanseg@alum.us.es>
 * 
 */
public enum Days {

	MONDAY("Monday"), TUESDAY("Tuesday"), WEDNESDAY("Wednesday"), THURSDAY(
			"Thursday"), FRIDAY("Friday"), SATURDAY("Saturday"), SUNDAY(
			"Sunday");

	public final String dayName;

	private Days(String dayName) {
		this.dayName = dayName;
	}

	public String getDayName() {
		return dayName;
	}

}
