package com.UNLaLibrary.TP_Proyecto_De_Software.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.UNLaLibrary.TP_Proyecto_De_Software.models.DocumentoModel;
import com.UNLaLibrary.TP_Proyecto_De_Software.models.MateriaModel;
import com.UNLaLibrary.TP_Proyecto_De_Software.services.IDocumentoService;
import com.UNLaLibrary.TP_Proyecto_De_Software.services.IMateriaService;

@Controller
public class ListadoMateriasController {

	@Autowired
	private IDocumentoService documentoService;
	@Autowired 
	private IMateriaService materiaService;

	@GetMapping("/listadoMaterias") 
	public ModelAndView navegarMaterias() { //Para ver todas las materias de todas los departamentos
		ModelAndView model = new ModelAndView("listadoMateriasUniversal");	
		List<MateriaModel> listaMaterias = materiaService.traerMaterias();
		
		model.addObject("listaMaterias", listaMaterias);
		return model;
	}
	
	@GetMapping("/misCursos")
	public ModelAndView misCursos(){
		ModelAndView model = new ModelAndView("misCursos");
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		List<MateriaModel> listaMaterias = materiaService.traerMateriasPorUser(userDetails.getUsername());

		model.addObject("listaMaterias", listaMaterias);
		return model;
	}
	
	@GetMapping("listadoMaterias/{materia}") //Para ver todos los documentos de una materia
	public ModelAndView materiaIndividual(@PathVariable ("materia") String materia) {
		ModelAndView model = new ModelAndView("listadoMateriasUniversalXDocumentos");
		List<DocumentoModel> listaDocumentosDeLaMateria = documentoService.traerDocumentosPorMateria(materia);
		
		model.addObject("listaDocumentos", listaDocumentosDeLaMateria);
		model.addObject("materia", materia);
		return model;
	}

	@GetMapping("misCursos/materia")
	public ModelAndView materiaProfesor(@RequestParam("id") long idMateria){
		ModelAndView model = new ModelAndView("listadoMateriasProfesor");
		MateriaModel materiaModel = materiaService.traerMateria(idMateria);
		List<DocumentoModel> listaDocumentos = documentoService.traerDocumentosPorMateria(materiaModel.getNombre());

		model.addObject("listaDocumentos", listaDocumentos);
		model.addObject("materia", materiaModel);
		return model;
	}
	
	@GetMapping("listadoMaterias/{materia}/documento") //Para ver un documento en especifico
	public ModelAndView documentoIndividual(@PathVariable ("materia") String materia, @RequestParam ("id") long id) {
		ModelAndView model = new ModelAndView("documento");
		DocumentoModel documento = documentoService.traerDocumento(id);
		
		model.addObject("documento", documento);
		return model;
	}
	
}
