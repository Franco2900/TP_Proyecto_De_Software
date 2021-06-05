package com.UNLaLibrary.TP_Proyecto_De_Software.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; //Para indicar que esta clase es un Controller
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView; //Para poder usar mezclar las vistas html con los modelos

import com.UNLaLibrary.TP_Proyecto_De_Software.models.DocumentoModel;
import com.UNLaLibrary.TP_Proyecto_De_Software.services.IDocumentoService;

import java.util.List;

@Controller
public class NavegacionController {
	
	@Autowired
	private IDocumentoService documentoService;
	
	//@GetMapping("/listadoDepartamentos/{departamento}/carreras/{carrera}/materias/{materia}/documento/{id}") Otra forma
	
	@GetMapping("/listadoDepartamentos/{departamento}") //Para ver las carreras del departamento
	public ModelAndView mostrarCarreras(@PathVariable("departamento") String departamento) {
		ModelAndView model = new ModelAndView("listadoCarreras");
		List<DocumentoModel> listaCarreras = documentoService.traerDocumentosPorDepartamento(departamento);
		
		model.addObject("listaCarreras", listaCarreras);
		model.addObject("departamento", departamento);
		return model;
	}
	
	@GetMapping("/listadoDepartamentos/{departamento}/{carrera}") //Para ver las materias de la carrera del departamento
	public ModelAndView mostrarMaterias(@PathVariable ("departamento") String departamento, @PathVariable ("carrera") String carrera) {
		ModelAndView model = new ModelAndView("listadoMaterias");
		List<DocumentoModel> listaMaterias = documentoService.traerDepartamentoXCarrera(departamento, carrera);
		
		model.addObject("listaMaterias", listaMaterias);
		model.addObject("departamento", departamento);
		model.addObject("carrera", carrera);
		return model;
	}
	
	
	@GetMapping("/listadoDepartamentos/{departamento}/{carrera}/{materia}") //Para ver los documentos de una materia de una carrera de un departamento
	public ModelAndView mostrarDocumentos(@PathVariable ("departamento") String departamento, @PathVariable ("carrera") String carrera, @PathVariable ("materia") String materia) {
		ModelAndView model = new ModelAndView("listadoDocumentos");
		List<DocumentoModel> listadoDocumentos = documentoService.traerDepartamentoXCarreraXMateria(departamento, carrera, materia);
		
		model.addObject("listaDocumentos", listadoDocumentos);
		model.addObject("departamento", departamento);
		model.addObject("carrera", carrera);
		model.addObject("materia", materia);
		return model;
	}
	
	
	@GetMapping("/listadoDepartamentos/{departamento}/{carrera}/{materia}/documento") //Para ver el documento seleccionado después de haber pasado por todos los filtros
	public ModelAndView mostrarDocumentoIndividual(@PathVariable ("departamento") String departamento, @PathVariable ("carrera") String carrera, @PathVariable ("materia") String materia, @RequestParam("id") long id) {
		ModelAndView model = new ModelAndView("documento");
		DocumentoModel documento = documentoService.traerDocumento(id);
		
		model.addObject("documento", documento);
		return model;
	}
	
	
}