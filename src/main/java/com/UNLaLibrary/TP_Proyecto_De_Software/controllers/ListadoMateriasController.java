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
		List<DocumentoModel> listaMaterias = new ArrayList<DocumentoModel>();
		
		//Recorro la lista de documentos y añado a la listaMaterias un solo documento por materia. Despues en la vista uso esos documentos para sacar el nombre de la materia
		for(DocumentoModel documento: documentoService.traerDocumentos() ) { //Recorro la lista de documentos
			
			if(listaMaterias.isEmpty() ) listaMaterias.add(documento); //En la primera vuelta listaMaterias siempre va a estar vacio así que le añado una materia
			boolean bandera = false; 								   //bandera para saber si una materia se repite o no
			
			for(DocumentoModel doc: listaMaterias) { 				  //Recorro la listaMaterias para chequear si ya esta la materia o no en la lista
				if(documento.getMateria().equals(doc.getMateria()) ){ //Si ya esta la materia en listaMaterias dejo de chequear 
					bandera = true;
					break;
				}
			}
			
			if(bandera == false) listaMaterias.add(documento);
		}
		
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
