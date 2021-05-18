package controllers;

import org.springframework.stereotype.Controller; //Para indicar que esta clase es un Controller
import org.springframework.web.bind.annotation.RequestMapping; //Para indicar la ruta (también llamado URL) por la cual se va a llamar a este controller. EJ: http:/wikipedia/inicio
import org.springframework.web.bind.annotation.GetMapping; //para indicar que metodo ejecutar cuando se cargue el controller
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@GetMapping("home") 
	public ModelAndView home() { //Metodo que se ejecuta cuando se pone /home
		return new ModelAndView("index");
	}
	
}
