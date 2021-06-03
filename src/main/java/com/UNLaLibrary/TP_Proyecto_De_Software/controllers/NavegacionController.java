package com.UNLaLibrary.TP_Proyecto_De_Software.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; //Para indicar que esta clase es un Controller
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping; //Para indicar la ruta (también llamado URL) por la cual se va a llamar a este controller y sus metodos. EJ: http:/wikipedia/inicio
import org.springframework.web.servlet.ModelAndView; //Para poder usar mezclar las vistas html con los modelos

import com.UNLaLibrary.TP_Proyecto_De_Software.models.DocumentoModel;
import com.UNLaLibrary.TP_Proyecto_De_Software.services.IDocumentoService;

import java.io.IOException;
import java.io.InputStream;
import org.springframework.web.bind.annotation.RequestParam;//Para pedir parametros y trabajar con ellos en los ModelAndView

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

@Controller
public class NavegacionController {
	
	@Autowired
	private IDocumentoService documentoService;
	
	
	@GetMapping("/listadoDepartamentos/Desarrollo Productivo y Tecnológico") //Para ver los documentos del departamento Desarrollo Productivo y Tecnológico
	public ModelAndView Productivo_Tecnologico() {
		ModelAndView model = new ModelAndView("listadoDocumentos");
		
		List<DocumentoModel> listaDocumentosFiltrados = documentoService.traerDocumentosPorDepartamento();
		
		model.addObject("ListaDocumentos", listaDocumentosFiltrados);
		model.addObject("departamento", "Desarrollo Productivo y Tecnológico");
		return model;
	}
	
	
	
	@GetMapping("/listadoDepartamentos/Desarrollo Productivo y Tecnológico/materias") //Para ver las materias del departamento Desarrollo Productivo y Tecnológico
	public ModelAndView Productivo_TecnologicoFiltroMaterias() {
		ModelAndView model = new ModelAndView("listadoMaterias");
		List<DocumentoModel> listaMaterias = new ArrayList<DocumentoModel>();
		
		for(DocumentoModel documento: documentoService.traerDocumentos() ) { //Recorro la lista de documentos
			
			if(listaMaterias.isEmpty() && documento.getDepartamento().equals("Desarrollo Productivo y Tecnológico") ) listaMaterias.add(documento); //En la primera vuelta listaMaterias siempre va a estar vacio así que le añado una materia
			boolean bandera = false; 								   //bandera para saber si una materia se repite o no
			
			for(DocumentoModel doc: listaMaterias) { 				  //Recorro la listaMaterias para chequear si ya esta la materia o no en la lista
				if(documento.getMateria().equals(doc.getMateria()) ){ //Si ya esta la materia en listaMaterias dejo de chequear 
					bandera = true;
					break;
				}
			}
			
			if(bandera == false && documento.getDepartamento().equals("Desarrollo Productivo y Tecnológico")) listaMaterias.add(documento);
		}

		
		model.addObject("listaMaterias", listaMaterias);
		model.addObject("departamento", "Desarrollo Productivo y Tecnológico");
		return model;
	}
	
/**************************************************************************************************************************/	
	
	@GetMapping("/listadoDepartamentos/Humanidades y Artes") //Para ver los documentos del departamento Humanidades y Artes
	public ModelAndView Humanidades_Artes() {
		ModelAndView model = new ModelAndView("listadoDocumentos");
		
		List<DocumentoModel> listaDocumentosFiltrados = new ArrayList<DocumentoModel>();
		
		for(DocumentoModel documento: documentoService.traerDocumentos() ) {
			if(documento.getDepartamento().equals("Humanidades y Artes") ) {
				listaDocumentosFiltrados.add(documento);
			}
		}
		
		model.addObject("ListaDocumentos", listaDocumentosFiltrados);
		model.addObject("departamento", "Humanidades y Artes");
		return model;
	}

	
	@GetMapping("/listadoDepartamentos/Humanidades y Artes/materias") //Para ver las materias del departamento Humanidades y Artes
	public ModelAndView HumanidadesFiltroMaterias() {
		ModelAndView model = new ModelAndView("listadoMaterias");
		List<DocumentoModel> listaMaterias = new ArrayList<DocumentoModel>();
		
		for(DocumentoModel documento: documentoService.traerDocumentos() ) { //Recorro la lista de documentos
			
			if(listaMaterias.isEmpty() && documento.getDepartamento().equals("Humanidades y Artes") ) listaMaterias.add(documento); //En la primera vuelta listaMaterias siempre va a estar vacio así que le añado una materia
			boolean bandera = false; 								   //bandera para saber si una materia se repite o no
			
			for(DocumentoModel doc: listaMaterias) { 				  //Recorro la listaMaterias para chequear si ya esta la materia o no en la lista
				if(documento.getMateria().equals(doc.getMateria()) ){ //Si ya esta la materia en listaMaterias dejo de chequear 
					bandera = true;
					break;
				}
			}
			
			if(bandera == false && documento.getDepartamento().equals("Humanidades y Artes")) listaMaterias.add(documento);
		}

		
		model.addObject("listaMaterias", listaMaterias);
		model.addObject("departamento", "Humanidades y Artes");
		return model;
	}
	
	
/**************************************************************************************************************************/	

	@GetMapping("/listadoDepartamentos/Planificación y Políticas Públicas") //Para ver los documentos del departamento Planificación y Políticas Públicas
	public ModelAndView Politicas_Publicas() {
		ModelAndView model = new ModelAndView("listadoDocumentos");
		
		List<DocumentoModel> listaDocumentosFiltrados = new ArrayList<DocumentoModel>();
		
		for(DocumentoModel documento: documentoService.traerDocumentos() ) {
			if(documento.getDepartamento().equals("Planificación y Políticas Públicas") ) {
				listaDocumentosFiltrados.add(documento);
			}
		}
		
		model.addObject("ListaDocumentos", listaDocumentosFiltrados);
		model.addObject("departamento", "Planificación y Políticas Públicas");
		return model;
	}

	
	@GetMapping("/listadoDepartamentos/Planificación y Políticas Públicas/materias") //Para ver las materias del departamento Planificación y Políticas Públicas
	public ModelAndView PoliticasFiltroMaterias() {
		ModelAndView model = new ModelAndView("listadoMaterias");
		List<DocumentoModel> listaMaterias = new ArrayList<DocumentoModel>();
		
		for(DocumentoModel documento: documentoService.traerDocumentos() ) { //Recorro la lista de documentos
			
			if(listaMaterias.isEmpty() && documento.getDepartamento().equals("Planificación y Políticas Públicas") ) listaMaterias.add(documento); //En la primera vuelta listaMaterias siempre va a estar vacio así que le añado una materia
			boolean bandera = false; 								   //bandera para saber si una materia se repite o no
			
			for(DocumentoModel doc: listaMaterias) { 				  //Recorro la listaMaterias para chequear si ya esta la materia o no en la lista
				if(documento.getMateria().equals(doc.getMateria()) ){ //Si ya esta la materia en listaMaterias dejo de chequear 
					bandera = true;
					break;
				}
			}
			
			if(bandera == false && documento.getDepartamento().equals("Planificación y Políticas Públicas")) listaMaterias.add(documento);
		}

		
		model.addObject("listaMaterias", listaMaterias);
		model.addObject("departamento", "Planificación y Políticas Públicas");
		return model;
	}
	
	
/**************************************************************************************************************************/	
	
	@GetMapping("/listadoDepartamentos/Salud Comunitaria") //Para ver los documentos del departamento Salud Comunitaria
	public ModelAndView Salud() {
		ModelAndView model = new ModelAndView("listadoDocumentos");
		
		List<DocumentoModel> listaDocumentosFiltrados = new ArrayList<DocumentoModel>();
		
		for(DocumentoModel documento: documentoService.traerDocumentos() ) {
			if(documento.getDepartamento().equals("Salud Comunitaria") ) {
				listaDocumentosFiltrados.add(documento);
			}
		}
		
		model.addObject("ListaDocumentos", listaDocumentosFiltrados);
		model.addObject("departamento", "Salud Comunitaria");
		return model;
	}
	
	
	@GetMapping("/listadoDepartamentos/Salud Comunitaria/materias") //Para ver las materias del departamento Salud Comunitaria
	public ModelAndView SaludFiltroMaterias() {
		ModelAndView model = new ModelAndView("listadoMaterias");
		List<DocumentoModel> listaMaterias = new ArrayList<DocumentoModel>();
		
		for(DocumentoModel documento: documentoService.traerDocumentos() ) { //Recorro la lista de documentos
			
			if(listaMaterias.isEmpty() && documento.getDepartamento().equals("Salud Comunitaria") ) listaMaterias.add(documento); //En la primera vuelta listaMaterias siempre va a estar vacio así que le añado una materia
			boolean bandera = false; 								   //bandera para saber si una materia se repite o no
			
			for(DocumentoModel doc: listaMaterias) { 				  //Recorro la listaMaterias para chequear si ya esta la materia o no en la lista
				if(documento.getMateria().equals(doc.getMateria()) ){ //Si ya esta la materia en listaMaterias dejo de chequear 
					bandera = true;
					break;
				}
			}
			
			if(bandera == false && documento.getDepartamento().equals("Salud Comunitaria")) listaMaterias.add(documento);
		}

		
		model.addObject("listaMaterias", listaMaterias);
		model.addObject("departamento", "Salud Comunitaria");
		return model;
	}
	
	
}
