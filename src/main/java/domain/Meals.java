package domain;

/**
 * This class contains all the constants related to meals.
 * 
 * @author Félix Miguel Sanjuán Segovia <felsanseg@alum.us.es>
 * 
 */
public enum Meals {

	BREAKFAST("Breakfast"), MID_MORNING("Mid-Morning"), LUNCH("Lunch"), TEA_TIME(
			"Tea Time"), DINNER("Dinner");

	public final String mealName;

	private Meals(String mealName) {
		this.mealName = mealName;
	}

	public String getMealName() {
		return mealName;
	}

}
