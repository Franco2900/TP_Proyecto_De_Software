package com.UNLaLibrary.TP_Proyecto_De_Software.controllers;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.UNLaLibrary.TP_Proyecto_De_Software.models.DocumentoModel;
import com.UNLaLibrary.TP_Proyecto_De_Software.services.IDocumentoService;
import com.UNLaLibrary.TP_Proyecto_De_Software.services.IMateriaService;

@Controller
public class ListadoCarrerasController {

	@Autowired
	private IDocumentoService documentoService;
	@Autowired 
	private IMateriaService materiaService;

	@GetMapping("/listadoCarreras") 
	public ModelAndView navegarCarreras() { //Para ver todas las carreras de todos los departamentos
		ModelAndView model = new ModelAndView("listadoCarreras");	
		List<String> listaCarreras = materiaService.traerCarreras();
		List<Integer> materiasPorCarrera = new ArrayList<Integer>();

		for(String carrera:listaCarreras){
			int cantidadMaterias = materiaService.contarMateriasPorCarrera(carrera);
			materiasPorCarrera.add(cantidadMaterias);
		}
		
		model.addObject("listaCarreras", listaCarreras);
		model.addObject("materiasPorCarrera", materiasPorCarrera);
		return model;
	}
	
	
	@GetMapping("listadoCarreras/{carrera}") //Para ver todos los documentos de una carrera
	public ModelAndView carreraIndividual(@PathVariable("carrera") String carrera) {
		ModelAndView model = new ModelAndView("listadoDocumentos");
		List<DocumentoModel> listaDocumentosDeLaCarrera = documentoService.traerDocumentosPorCarrera(carrera);
		
		model.addObject("listaDocumentos", listaDocumentosDeLaCarrera);
		model.addObject("carrera", carrera);
		return model;
	}
}
