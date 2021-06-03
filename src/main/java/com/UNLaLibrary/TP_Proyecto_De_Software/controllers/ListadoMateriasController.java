package com.UNLaLibrary.TP_Proyecto_De_Software.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.UNLaLibrary.TP_Proyecto_De_Software.models.DocumentoModel;
import com.UNLaLibrary.TP_Proyecto_De_Software.services.IDocumentoService;

@Controller
public class ListadoMateriasController {

	@Autowired
	private IDocumentoService documentoService;

	@GetMapping("/listadoMaterias") 
	public ModelAndView navegarMaterias(@RequestParam (defaultValue="Todos los departamentos", name="departamento") String departamento) { //Para ver todas las materias de todas los departamentos
		ModelAndView model = new ModelAndView("listadoMaterias");	
		List<String> listaMaterias = documentoService.traerMaterias();
		
		model.addObject("listaMaterias", listaMaterias);
		model.addObject("departamento", departamento);
		return model;
	}
	
	
	@GetMapping("listadoMaterias/materia") //Para ver todos los documentos de una materia
	public ModelAndView materiaIndividual(@RequestParam (defaultValue="Historia de los sistemas", name="materia") String materia) {
		ModelAndView model = new ModelAndView("listadoDocumentos");
		List<DocumentoModel> listaDocumentosDeLaMateria = new ArrayList<DocumentoModel>();
		
		for(DocumentoModel documento: documentoService.traerDocumentos() ) {
			if(documento.getMateria().equals(materia) ) listaDocumentosDeLaMateria.add(documento);
		}
		
		model.addObject("ListaDocumentos", listaDocumentosDeLaMateria);
		return model;
	}
	
	
	
}
