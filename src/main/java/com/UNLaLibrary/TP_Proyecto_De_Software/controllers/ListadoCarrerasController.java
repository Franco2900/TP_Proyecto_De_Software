package com.UNLaLibrary.TP_Proyecto_De_Software.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.UNLaLibrary.TP_Proyecto_De_Software.models.DocumentoModel;
import com.UNLaLibrary.TP_Proyecto_De_Software.services.IDocumentoService;

@Controller
public class ListadoCarrerasController {

	@Autowired
	private IDocumentoService documentoService;

	@GetMapping("/listadoCarreras") 
	public ModelAndView navegarCarreras() { //Para ver todas las carreras de todos los departamentos
		ModelAndView model = new ModelAndView("listadoCarrerasUniversal");	
		List<String> listaCarreras = documentoService.traerCarreras();
		
		model.addObject("listaCarreras", listaCarreras);
		return model;
	}
	
	
	@GetMapping("listadoCarreras/{carrera}") //Para ver todos los documentos de una carrera
	public ModelAndView carreraIndividual(@PathVariable("carrera") String carrera) {
		ModelAndView model = new ModelAndView("listadoCarrerasUniversalXDocumentos");
		List<DocumentoModel> listaDocumentosDeLaCarrera = documentoService.traerDocumentosPorCarrera(carrera);
		
		model.addObject("listaDocumentos", listaDocumentosDeLaCarrera);
		model.addObject("carrera", carrera);
		return model;
	}
	
	@GetMapping("listadoCarreras/{carrera}/documento") //Para ver un documento en especifico
	public ModelAndView documentoIndividual(@PathVariable("carrera") String carrera, @RequestParam("id") long id) {
		ModelAndView model = new ModelAndView("documento");
		DocumentoModel documento = documentoService.traerDocumento(id);
		
		model.addObject("documento", documento);
		return model;
	}
	
}
