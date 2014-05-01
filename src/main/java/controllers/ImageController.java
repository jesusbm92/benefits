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

import services.FoodService;
import domain.Food;

@Controller
@RequestMapping("/image")
public class ImageController {

	@Autowired
	private FoodService foodService;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(HttpServletResponse response,
			@RequestParam(required = false) int foodId) throws IOException {
		Food food = foodService.findOne(foodId);

		response.setContentType("image/jpeg");
		byte[] buffer = food.getImage();
		InputStream input = new ByteArrayInputStream(buffer);
		IOUtils.copy(input, response.getOutputStream());

		return null;
	}

}
