package com.UNLaLibrary.TP_Proyecto_De_Software.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; //Para indicar que esta clase es un Controller
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping; //Para indicar la ruta (también llamado URL) por la cual se va a llamar a este controller y sus metodos. EJ: http:/wikipedia/inicio
import org.springframework.web.servlet.ModelAndView; //Para poder usar mezclar las vistas html con los modelos
import org.springframework.web.bind.annotation.RequestParam;//Para pedir parametros y trabajar con ellos en los ModelAndView

import com.UNLaLibrary.TP_Proyecto_De_Software.models.DocumentoModel;
import com.UNLaLibrary.TP_Proyecto_De_Software.services.IDocumentoService;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
=======
import javax.servlet.http.HttpServletResponse;
>>>>>>> fc1784bccd76b8488e362cba1d05c737a7ce0791

@Controller
public class NavegacionController {
	@Autowired
	private IDocumentoService documentoService;
	
<<<<<<< HEAD
	/*
=======
>>>>>>> fc1784bccd76b8488e362cba1d05c737a7ce0791
	@RequestMapping("/listadoDocumentos") //Para ver todos los documentos disponibles
	public ModelAndView navegar() {
		ModelAndView model = new ModelAndView("listadoDocumentos");
		model.addObject("ListaDocumentos", documentoService.traerDocumentos() );
		return model;
	}
	
	
	@GetMapping("listadoDocumentos/documento") //Para ver cada documento individualmente
	public ModelAndView documentoIndividual(@RequestParam (defaultValue="1", name="id") long id) {
		ModelAndView model = new ModelAndView("documento");
		
		List<DocumentoModel> documentoFiltrado = new ArrayList<DocumentoModel>();
		for(DocumentoModel documento: documentoService.traerDocumentos() ) {
			if(documento.getId() == id) {
				documentoFiltrado.add(documento);
				break;
			}
		}
		
		//Codigo para filtrar por id según el tuto que paso Fede
		//List<DocumentoModel> documentoFiltrado = this.datosDePrueba().stream().filter((documento)->{return documento.getId()==id;}).collect(Collectors.toList());
		
		model.addObject("documento", documentoFiltrado.get(0) );
		return model;
	}
	
	@GetMapping("listadoDocumentos/documento/descarga") // Para descargar un archivo en particular
	public void descargarDocumento(@RequestParam (defaultValue="1", name="id") long id, HttpServletResponse response){
		try{
			response.setContentType("application/pdf");
			InputStream is = documentoService.descargarDocumento(id);
			FileCopyUtils.copy(is, response.getOutputStream());
      		response.flushBuffer();
		}
		catch(IOException ex){
      		throw new RuntimeException("IOError writing file to output stream", ex);
		}
	}
	
	@GetMapping("/listadoDepartamentos") //Para ver los departamentos
	public ModelAndView navegarDepartamentos() {
		ModelAndView model = new ModelAndView("listadoDepartamentos");
		return model;
	}
	
	
	@GetMapping("/listadoDepartamentos/Productivo_Tecnologico") //Para ver los documentos del departamento Desarrollo Productivo y Tecnológico
	public ModelAndView Productivo_Tecnologico() {
		ModelAndView model = new ModelAndView("listadoDocumentos");
		
		List<DocumentoModel> listaDocumentosFiltrados = new ArrayList<DocumentoModel>();
		
		for(DocumentoModel documento: documentoService.traerDocumentos() ) {
			if(documento.getDepartamento().equals("Desarrollo Productivo y Tecnológico") ) {
				listaDocumentosFiltrados.add(documento);
			}
		}
		
		model.addObject("ListaDocumentos", listaDocumentosFiltrados);
		return model;
	}
	
	@GetMapping("/listadoDepartamentos/Humanidades_Artes") //Para ver los documentos del departamento Humanidades y Artes
	public ModelAndView Humanidades_Artes() {
		ModelAndView model = new ModelAndView("listadoDocumentos");
		
		List<DocumentoModel> listaDocumentosFiltrados = new ArrayList<DocumentoModel>();
		
		for(DocumentoModel documento: documentoService.traerDocumentos() ) {
			if(documento.getDepartamento().equals("Humanidades y Artes") ) {
				listaDocumentosFiltrados.add(documento);
			}
		}
		
		model.addObject("ListaDocumentos", listaDocumentosFiltrados);
		return model;
	}
	
	@GetMapping("/listadoDepartamentos/Politicas_Publicas") //Para ver los documentos del departamento Planificación y Políticas Públicas
	public ModelAndView Politicas_Publicas() {
		ModelAndView model = new ModelAndView("listadoDocumentos");
		
		List<DocumentoModel> listaDocumentosFiltrados = new ArrayList<DocumentoModel>();
		
		for(DocumentoModel documento: documentoService.traerDocumentos() ) {
			if(documento.getDepartamento().equals("Planificación y Políticas Públicas") ) {
				listaDocumentosFiltrados.add(documento);
			}
		}
		
		model.addObject("ListaDocumentos", listaDocumentosFiltrados);
		return model;
	}
	
	@GetMapping("/listadoDepartamentos/Salud") //Para ver los documentos del departamento Salud Comunitaria
	public ModelAndView Salud() {
		ModelAndView model = new ModelAndView("listadoDocumentos");
		
		List<DocumentoModel> listaDocumentosFiltrados = new ArrayList<DocumentoModel>();
		
		for(DocumentoModel documento: documentoService.traerDocumentos() ) {
			if(documento.getDepartamento().equals("Salud Comunitaria") ) {
				listaDocumentosFiltrados.add(documento);
			}
		}
		
		model.addObject("ListaDocumentos", listaDocumentosFiltrados);
		return model;
	}
	
	
	@GetMapping("/listadoMaterias") 
	public ModelAndView navegarMaterias() { //Las materias no siempre van a ser las mismas. Debería revisar la base de datos para recibir los documentos
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
	*/
	
}
