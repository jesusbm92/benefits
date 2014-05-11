package utilities;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.context.i18n.LocaleContextHolder;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import domain.Amount;
import domain.Day;
import domain.Days;
import domain.Diet;
import domain.Exercise;
import domain.ExerciseGroup;
import domain.Meal;
import domain.Meals;
import domain.Training;
import domain.TrainingDay;

public class PdfUtils {

	/**
	 * Day Constants
	 */
	private static final String SUNDAY2 = "Sunday";
	private static final String SATURDAY2 = "Saturday";
	private static final String FRIDAY2 = "Friday";
	private static final String THURSDAY2 = "Thursday";
	private static final String WEDNESDAY2 = "Wednesday";
	private static final String TUESDAY2 = "Tuesday";
	private static final String MONDAY2 = "Monday";
	private static final String DOMINGO = "Domingo";
	private static final String SUNDAY = "SUNDAY";
	private static final String SÁBADO = "Sábado";
	private static final String JUEVES = "Jueves";
	private static final String VIERNES = "Viernes";
	private static final String SATURDAY = "SATURDAY";
	private static final String FRIDAY = "FRIDAY";
	private static final String THURSDAY = "THURSDAY";
	private static final String WEDNESDAY = "WEDNESDAY";
	private static final String TUESDAY = "TUESDAY";
	private static final String MIÉRCOLES = "Miércoles";
	private static final String MARTES = "Martes";
	private static final String LUNES = "Lunes";
	private static final String MONDAY = "MONDAY";

	/**
	 * Diet Constants
	 */
	private static final String MID_MORNING2 = "Mid Morning";
	private static final String LUNCH2 = "Lunch";
	private static final String DINNER2 = "Dinner";
	private static final String TEA_TIME2 = "Tea Time";
	private static final String BREAKFAST2 = "Breakfast";
	private static final String MEDIA_MAÑANA = "Media Mañana";
	private static final String ALMUERZO = "Almuerzo";
	private static final String CENA = "Cena";
	private static final String MERIENDA = "Merienda";
	private static final String DESAYUNO = "Desayuno";
	private static final String SPANISH = "Spanish";
	private static final String MID_MORNING = "MID_MORNING";
	private static final String LUNCH = "LUNCH";
	private static final String DINNER = "DINNER";
	private static final String TEA_TIME = "TEA_TIME";
	private static final String BREAKFAST = "BREAKFAST";

	/**
	 * Pdf Constants
	 */
	private static Font catFont = new Font(Font.TIMES_ROMAN, 20, Font.BOLD);
	private static Font subFont = new Font(Font.TIMES_ROMAN, 18, Font.BOLD);
	private static Font smallBold = new Font(Font.TIMES_ROMAN, 16, Font.BOLD);
	private static Font text = new Font(Font.TIMES_ROMAN, 12);
	private final static String SANGRIA = "    ";

	public static ByteArrayOutputStream exportDietToPdf(Diet diet) {
		ByteArrayOutputStream file = new ByteArrayOutputStream();
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, file);
			document.open();

			document.add(new Paragraph(diet.getName(), catFont));
			for (Day day : diet.getDays()) {
				document.add(new Paragraph(SANGRIA + getDayName(day.getName()),
						subFont));
				for (Meal meal : day.getMeals()) {
					document.add(new Paragraph(SANGRIA + SANGRIA
							+ getMealName(meal.getName()), smallBold));
					for (Amount amount : meal.getAmounts()) {
						document.add(new Paragraph(SANGRIA + SANGRIA + SANGRIA
								+ "Food Name: " + amount.getFood().getName(),
								text));
						document.add(new Paragraph(SANGRIA + SANGRIA + SANGRIA
								+ SANGRIA + "Food Description: "
								+ amount.getFood().getDescription(), text));
						document.add(new Paragraph(SANGRIA + SANGRIA + SANGRIA
								+ SANGRIA + "Quantity : "
								+ amount.getQuantity(), text));
						document.add(new Paragraph(SANGRIA + SANGRIA + SANGRIA
								+ SANGRIA + "Measure : " + amount.getMeasure(),
								text));
						document.add(new Paragraph(" "));
						document.add(new Paragraph(" "));
					}
				}
			}

			document.close();
			file.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return file;
	}

	public static ByteArrayOutputStream exportTrainingToPdf(Training training) {
		Document document = new Document();
		ByteArrayOutputStream file = new ByteArrayOutputStream();
		try {

			PdfWriter.getInstance(document, file);
			document.open();

			document.add(new Paragraph(training.getName(), catFont));
			for (TrainingDay day : training.getTrainingDays()) {
				document.add(new Paragraph(SANGRIA + getDayName(day.getName()),
						subFont));
				for (ExerciseGroup exerciseGroup : day.getExerciseGroups()) {
					document.add(new Paragraph(SANGRIA + SANGRIA
							+ exerciseGroup.getName(), smallBold));
					for (Exercise exercise : exerciseGroup.getExercises()) {
						document.add(new Paragraph(SANGRIA + SANGRIA + SANGRIA
								+ "Exercise : " + exercise.getName(), text));
						document.add(new Paragraph(SANGRIA + SANGRIA + SANGRIA
								+ SANGRIA + "Cycles : " + exercise.getCycles(),
								text));
						document.add(new Paragraph(SANGRIA + SANGRIA + SANGRIA
								+ SANGRIA + "Repetitions : "
								+ exercise.getRepetitions(), text));
						document.add(new Paragraph(SANGRIA + SANGRIA + SANGRIA
								+ SANGRIA + "Muscle : "
								+ exercise.getMuscle().getName(), text));
						document.add(new Paragraph(" "));
						document.add(new Paragraph(" "));
					}
				}
			}

			document.close();
			file.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}

	public static String getMealName(Meals meals) {
		String enumeratedMeal = meals.toString();
		String language = LocaleContextHolder.getLocale().getDisplayLanguage();
		if (language.equals(SPANISH)) {
			if (enumeratedMeal.equals(BREAKFAST)) {
				return DESAYUNO;
			} else if (enumeratedMeal.equals(TEA_TIME)) {
				return MERIENDA;
			} else if (enumeratedMeal.equals(DINNER)) {
				return CENA;
			} else if (enumeratedMeal.equals(LUNCH)) {
				return ALMUERZO;
			} else if (enumeratedMeal.equals(MID_MORNING)) {
				return MEDIA_MAÑANA;
			}
		} else {
			if (enumeratedMeal.equals(BREAKFAST)) {
				return BREAKFAST2;
			} else if (enumeratedMeal.equals(TEA_TIME)) {
				return TEA_TIME2;
			} else if (enumeratedMeal.equals(DINNER)) {
				return DINNER2;
			} else if (enumeratedMeal.equals(LUNCH)) {
				return LUNCH2;
			} else if (enumeratedMeal.equals(MID_MORNING)) {
				return MID_MORNING2;
			}
		}
		return null;
	}

	public static String getDayName(Days days) {
		String enumeratedDay = days.toString();
		String language = LocaleContextHolder.getLocale().getDisplayLanguage();
		if (language.equals(SPANISH)) {
			if (enumeratedDay.equals(MONDAY)) {
				return LUNES;
			} else if (enumeratedDay.equals(TUESDAY)) {
				return MARTES;
			} else if (enumeratedDay.equals(WEDNESDAY)) {
				return MIÉRCOLES;
			} else if (enumeratedDay.equals(THURSDAY)) {
				return JUEVES;
			} else if (enumeratedDay.equals(FRIDAY)) {
				return VIERNES;
			} else if (enumeratedDay.equals(SATURDAY)) {
				return SÁBADO;
			} else if (enumeratedDay.equals(SUNDAY)) {
				return DOMINGO;
			}
		} else {
			if (enumeratedDay.equals(MONDAY)) {
				return MONDAY2;
			} else if (enumeratedDay.equals(TUESDAY)) {
				return TUESDAY2;
			} else if (enumeratedDay.equals(WEDNESDAY)) {
				return WEDNESDAY2;
			} else if (enumeratedDay.equals(THURSDAY)) {
				return THURSDAY2;
			} else if (enumeratedDay.equals(FRIDAY)) {
				return FRIDAY2;
			} else if (enumeratedDay.equals(SATURDAY)) {
				return SATURDAY2;
			} else if (enumeratedDay.equals(SUNDAY)) {
				return SUNDAY2;
			}
		}
		return null;
	}

}
