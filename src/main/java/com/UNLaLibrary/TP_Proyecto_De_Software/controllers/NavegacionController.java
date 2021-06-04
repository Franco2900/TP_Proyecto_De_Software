package com.UNLaLibrary.TP_Proyecto_De_Software.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; //Para indicar que esta clase es un Controller
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView; //Para poder usar mezclar las vistas html con los modelos

import com.UNLaLibrary.TP_Proyecto_De_Software.models.DocumentoModel;
import com.UNLaLibrary.TP_Proyecto_De_Software.services.IDocumentoService;

import java.util.List;

@Controller
public class NavegacionController {
	
	@Autowired
	private IDocumentoService documentoService;
	
	@GetMapping("/listadoDepartamentos/{departamento}") //Para ver los documentos del departamento
	public ModelAndView mostrarDepartamento(@PathVariable("departamento") String departamento) {
		ModelAndView model = new ModelAndView("listadoDocumentos");
		List<DocumentoModel> listaDocumentosPorDepartamento = documentoService.traerDocumentosPorDepartamento(departamento);
		
		model.addObject("listaDocumentos", listaDocumentosPorDepartamento);
		model.addObject("departamento", departamento);
		return model;
	}
	
	
	@GetMapping("/listadoDepartamentos/{departamento}/materias") //Para ver las materias del departamento
	public ModelAndView mostrarMateriasDelDepartamento(@PathVariable("departamento") String departamento) {
		ModelAndView model = new ModelAndView("listadoDocumentos");
		List<DocumentoModel> listaMateriasPorDepartamento = documentoService.traerDocumentosPorDepartamento(departamento);
		
		model.addObject("listaDocumentos", listaMateriasPorDepartamento);
		model.addObject("departamento", departamento);
		return model;
	}

	
	@GetMapping("/listadoDepartamentos/{departamento}/carreras") //Para ver las carreras del departamento
	public ModelAndView mostrarCarrerasDelDepartamento(@PathVariable("departamento") String departamento) {
		ModelAndView model = new ModelAndView("listadoDocumentos");
		List<DocumentoModel> listaCarrerasPorDepartamento = documentoService.traerDocumentosPorDepartamento(departamento);
		
		model.addObject("listaDocumentos", listaCarrerasPorDepartamento);
		model.addObject("departamento", departamento);
		return model;
	}
	
}