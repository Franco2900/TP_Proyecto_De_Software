package com.UNLaLibrary.TP_Proyecto_De_Software.controllers;

import org.springframework.stereotype.Controller; //Para indicar que esta clase es un Controller
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView; //Para poder usar mezclar las vistas html con los modelos

@Controller
public class ListadoDepartamentosController {
	
	
	@GetMapping("/listadoDepartamentos") //Para ver los departamentos
	public ModelAndView navegarDepartamentos() {
		ModelAndView model = new ModelAndView("listadoDepartamentos");
		return model;
	}
	
	
}
