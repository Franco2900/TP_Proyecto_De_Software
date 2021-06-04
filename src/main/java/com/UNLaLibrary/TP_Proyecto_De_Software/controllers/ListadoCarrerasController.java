package com.UNLaLibrary.TP_Proyecto_De_Software.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.UNLaLibrary.TP_Proyecto_De_Software.models.DocumentoModel;
import com.UNLaLibrary.TP_Proyecto_De_Software.services.IDocumentoService;

@Controller
public class ListadoCarrerasController {

	@Autowired
	private IDocumentoService documentoService;

	@GetMapping("/listadoCarreras") 
	public ModelAndView navegarCarreras() { //Para ver todas las carreras de todas los departamentos
		ModelAndView model = new ModelAndView("listadoCarreras");	
		List<String> listaCarreras = documentoService.traerCarreras();
		
		model.addObject("listaCarreras", listaCarreras);
		model.addObject("departamento", "Todos los departamentos");
		return model;
	}
	
	
	@GetMapping("listadoCarreras/{carrera}") //Para ver todos los documentos de una materia
	public ModelAndView carreraIndividual(@PathVariable("carrera") String carrera) {
		ModelAndView model = new ModelAndView("listadoDocumentos");
		List<DocumentoModel> listaDocumentosDeLaCarrera = documentoService.traerDocumentosPorCarrera(carrera);
		
		model.addObject("listaDocumentos", listaDocumentosDeLaCarrera);
		return model;
	}
	
	
}
