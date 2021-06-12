package com.UNLaLibrary.TP_Proyecto_De_Software.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; //Para indicar que esta clase es un Controller
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView; //Para poder usar mezclar las vistas html con los modelos

import com.UNLaLibrary.TP_Proyecto_De_Software.models.DocumentoModel;
import com.UNLaLibrary.TP_Proyecto_De_Software.models.MateriaModel;
import com.UNLaLibrary.TP_Proyecto_De_Software.services.IDocumentoService;
import com.UNLaLibrary.TP_Proyecto_De_Software.services.IMateriaService;

import java.util.List;

@Controller
public class NavegacionController {
	@Autowired
	private IDocumentoService documentoService;
	@Autowired
	private IMateriaService materiaService;
	
	//@GetMapping("/listadoDepartamentos/{departamento}/carreras/{carrera}/materias/{materia}/documento/{id}") Otra forma
	
	@GetMapping("/listadoDepartamentos/{departamento}") //Para ver las carreras del departamento
	public ModelAndView mostrarCarreras(@PathVariable("departamento") String departamento) {
		ModelAndView model = new ModelAndView("listadoCarreras");
		List<String> listaCarreras = materiaService.traerCarrerasPorDepartamento(departamento);
		List<Integer> materiasPorCarrera = new ArrayList<Integer>();

		for(String carrera:listaCarreras){
			int cantidadMaterias = materiaService.contarMateriasPorCarrera(carrera);
			materiasPorCarrera.add(cantidadMaterias);
		}

		model.addObject("listaCarreras", listaCarreras);
		model.addObject("materiasPorCarrera", materiasPorCarrera);
		model.addObject("departamento", departamento);
		return model;
	}
	
	@GetMapping("/listadoDepartamentos/{departamento}/{carrera}") //Para ver las materias de la carrera del departamento
	public ModelAndView mostrarMaterias(@PathVariable ("departamento") String departamento, @PathVariable ("carrera") String carrera) {
		ModelAndView model = new ModelAndView("listadoMaterias");
		List<MateriaModel> listaMaterias = materiaService.traerMateriasPorCarreraYDepartamento(carrera, departamento);
		
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
}