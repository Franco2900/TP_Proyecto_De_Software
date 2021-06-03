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
public class ListadoCarrerasController {

	@Autowired
	private IDocumentoService documentoService;

	@GetMapping("/listadoCarreras") 
	public ModelAndView navegarCarreras(@RequestParam (defaultValue="Todos los departamentos", name="departamento") String departamento) { //Para ver todas las carreras de todas los departamentos
		ModelAndView model = new ModelAndView("listadoCarreras");	
		List<DocumentoModel> listaCarreras = new ArrayList<DocumentoModel>();
		listaCarreras = documentoService.traerDocumentos();
		//Recorro la lista de documentos y añado a la listaCarreras un solo documento por carrera. Despues en la vista uso esos documentos para sacar el nombre de la carrera
		for(DocumentoModel documento: documentoService.traerDocumentos() ) { //Recorro la lista de documentos
			
			if(listaCarreras.isEmpty() ) listaCarreras.add(documento); //En la primera vuelta listaCarreras siempre va a estar vacio así que le añado una carrera
			boolean bandera = false; 								   //bandera para saber si una carrera se repite o no
			
			for(DocumentoModel doc: listaCarreras) { 				  //Recorro la listaCarreras para chequear si ya esta la carrera o no en la lista
				if(documento.getCarrera().equals(doc.getCarrera()) ){ //Si ya esta la carrera en listaCarrera dejo de chequear 
					bandera = true;
					break;
				}
			}
			
			if(bandera == false) listaCarreras.add(documento);
		}
		
		model.addObject("listaCarreras", listaCarreras);
		model.addObject("departamento", departamento);
		return model;
	}
	
	
	@GetMapping("listadoCarreras/carrera") //Para ver todos los documentos de una materia
	public ModelAndView carreraIndividual(@RequestParam String materia) {
		ModelAndView model = new ModelAndView("listadoDocumentos");
		List<DocumentoModel> listaDocumentosDeLaCarrera = new ArrayList<DocumentoModel>();
		
		for(DocumentoModel documento: documentoService.traerDocumentos() ) {
			if(documento.getCarrera().equals(materia) ) listaDocumentosDeLaCarrera.add(documento);
		}
		
		model.addObject("ListaDocumentos", listaDocumentosDeLaCarrera);
		return model;
	}
	
	
}
