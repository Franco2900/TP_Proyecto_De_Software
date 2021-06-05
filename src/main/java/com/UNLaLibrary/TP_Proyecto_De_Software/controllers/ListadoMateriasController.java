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
public class ListadoMateriasController {

	@Autowired
	private IDocumentoService documentoService;

	@GetMapping("/listadoMaterias") 
	public ModelAndView navegarMaterias() { //Para ver todas las materias de todas los departamentos
		ModelAndView model = new ModelAndView("listadoMateriasUniversal");	
		List<String> listaMaterias = documentoService.traerMaterias();
		
		model.addObject("listaMaterias", listaMaterias);
		return model;
	}
	
	
	@GetMapping("listadoMaterias/{materia}") //Para ver todos los documentos de una materia
	public ModelAndView materiaIndividual(@PathVariable ("materia") String materia) {
		ModelAndView model = new ModelAndView("listadoDocumentos");
		List<DocumentoModel> listaDocumentosDeLaMateria = documentoService.traerDocumentosPorMateria(materia);
		
		model.addObject("listaDocumentos", listaDocumentosDeLaMateria);
		return model;
	}
	
	
	
}
