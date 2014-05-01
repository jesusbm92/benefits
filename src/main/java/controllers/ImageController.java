package controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ExerciseService;
import services.FoodService;
import domain.Exercise;
import domain.Food;

@Controller
@RequestMapping("/image")
public class ImageController {

	@Autowired
	private FoodService foodService;

	@Autowired
	private ExerciseService exerciseService;

	@RequestMapping(value = "/showFood", method = RequestMethod.GET)
	public ModelAndView showImageFood(HttpServletResponse response,
			@RequestParam int foodId) throws IOException {
		Food food = foodService.findOne(foodId);

		response.setContentType("image/jpeg");
		byte[] buffer = food.getImage();
		InputStream input = new ByteArrayInputStream(buffer);
		IOUtils.copy(input, response.getOutputStream());

		return null;
	}

	@RequestMapping(value = "/showExercise", method = RequestMethod.GET)
	public ModelAndView showImageExercise(HttpServletResponse response,
			@RequestParam int exerciseId) throws IOException {
		Exercise exercise = exerciseService.findOne(exerciseId);

		response.setContentType("image/jpeg");
		byte[] buffer = exercise.getImage();
		InputStream input = new ByteArrayInputStream(buffer);
		IOUtils.copy(input, response.getOutputStream());

		return null;
	}

}
