package com.UNLaLibrary.TP_Proyecto_De_Software.controllers;

import org.springframework.stereotype.Controller; //Para indicar que esta clase es un Controller
import org.springframework.web.bind.annotation.RequestMapping; //Para indicar la ruta (también llamado URL) por la cual se va a llamar a este controller. EJ: http:/wikipedia/inicio
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping("/")
	public ModelAndView home() {
		return new ModelAndView ("index");
	}
	
}
